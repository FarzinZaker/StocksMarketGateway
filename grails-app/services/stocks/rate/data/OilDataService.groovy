package stocks.rate.data

import fi.joensuu.joyds1.calendar.JalaliCalendar
import grails.converters.JSON
import groovy.time.TimeCategory
import groovyx.net.http.HTTPBuilder
import org.apache.commons.httpclient.HttpClient
import org.apache.commons.httpclient.HttpMethod
import org.apache.commons.httpclient.methods.GetMethod
import stocks.RandomUserAgent
import stocks.RateHelper
import stocks.rate.Oil
import stocks.rate.event.OilEvent

class OilDataService {
    static transactional = false
    def rateEventGateway

    static schedules = [
            [
                    method : 'importData',
                    trigger: [
                            type      : 'Simple',
                            parameters: [repeatInterval: 900000l, startDelay: 60000]
                    ]
            ]
    ]

    void importData() {
        RateHelper.OILS.keySet().each { oil ->
            parseData(oil, RateHelper.OILS."${oil}" as Map)
        }
    }

    private static Oil find(OilEvent object) {
        Oil.withTransaction {
            Oil.findBySymbol(object.symbol)
        }
    }

    private void parseData(symbol, Map data) {

        try {
            def http = new HTTPBuilder("http://www.bloomberg.com/quote/${data.source}")
            def html = http.get([:])
            def oilEvent = new OilEvent()
            oilEvent.symbol = symbol
            oilEvent.price = html.'**'.find { it.@class.toString() == 'price' }.text().replace(',', '') as Double
            oilEvent.unit = html.'**'.find { it.@class.toString() == 'currency' }.text()
            html.'**'.find { it.@class.toString() == 'change-container' }.text().split(' ').findAll {
                !['', '\n'].contains(it)
            }.collect { it.trim() }.each { String item ->
                if (item.endsWith('%'))
                    oilEvent.percent = item.replace('%', '').replace(',', '') as Double
                else
                    oilEvent.change = item.replace(',', '') as Double
            }
            oilEvent.time = parseDateTime(html.'**'.find { it.@class.toString() == 'price-datetime' }.text().trim().split('on').collect{it.replace('.', '').replace('ET', '').replace('As of', '').trim()}.reverse().join(' '))
            use(TimeCategory){
                oilEvent.time += 8.hours + 30.minutes
            }
            html.'**'.find { it.@class.toString().contains('data-table_detailed') }.'**'.findAll{it.@class.toString() == 'cell'}.collect{it.text().trim().split('\n').findAll{it.trim()}.collect{it.trim()}}.each(){
                if(it[0] == 'Open')
                    oilEvent.open = it[1].replace(',', '') as Double
                else if(it[0] == 'Day Range'){
                    def dayRangeParts = it[1].split('-').collect{it.trim()}
                    oilEvent.low = dayRangeParts[0].replace(',', '') as Double
                    oilEvent.high = dayRangeParts[1].replace(',', '') as Double
                }
            }
            rateEventGateway.send(oilEvent, this.class.name)
            logState([status: 'successful'])
        }
        catch (ex) {
            throw ex
            ex.printStackTrace()
            logState([status: 'failed', message: ex.message, stackTrace: ex.stackTrace])
        }
    }


    private static Date parseDateTime(String dateTime) {
        def parts = dateTime.split(' ')
        def date = parts[0]
        def time = parts[1]
        def dateParts = date.split("/").collect { it as Integer }
        def cal = Calendar.instance
        cal.set(Calendar.YEAR, dateParts[2])
        cal.set(Calendar.MONTH, dateParts[0] - 1)
        cal.set(Calendar.DAY_OF_MONTH, dateParts[1])
        def timeParts = time.split(":")
        cal.set(Calendar.HOUR_OF_DAY, timeParts[0] as Integer)
        cal.set(Calendar.MINUTE, timeParts[1] as Integer)
        cal.set(Calendar.SECOND, timeParts[2] as Integer)
        cal.time
    }
}
