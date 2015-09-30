package stocks.commodity.data

import fi.joensuu.joyds1.calendar.JalaliCalendar
import grails.converters.JSON
import groovy.grape.Grape
import org.apache.http.client.methods.HttpHead
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.BasicResponseHandler
import org.apache.http.impl.client.DefaultHttpClient
import stocks.commodity.CommodityMarketActivity
import stocks.commodity.CommodityMarketHelper
import stocks.commodity.event.CommodityMarketActivityEvent

class CommodityMarketActivityDataService {

    static schedules = [
            [
                    method : 'importData',
                    trigger: [
                            type      : 'Simple',
                            parameters: [repeatInterval: 120000l, startDelay: 10000]
                    ]
            ]
    ]


    def commodityEventGateway

    def importData() {
//        Grape.grab(group:'net.sourceforge.nekohtml', module:'nekohtml', version:'1.9.18')

        def client = new DefaultHttpClient()
        def get = new HttpHead("http://www.ime.co.ir")
        def response = client.execute(get)
        def sessionId = response.headergroup.headers.find{it.name?.toLowerCase() == 'set-cookie'}.buffer.toString().split(';').find().split('=').last()

        log.error "commodity market status, sessionId: ${sessionId}"
        client = new DefaultHttpClient()
        def post = new HttpPost("http://www.ime.co.ir/SubSystems/IME/Services/MarketTotal/MarketTotal.asmx/GetTodayGrid")
        StringEntity se = new StringEntity('{"ObjectId":"MarketTotalToday"}');
        post.setEntity(se);
        post.addHeader("Content-Type", "application/json; charset=utf-8")
        post.addHeader("Cookie", "ASP.NET_SessionId=${sessionId}")
        def responseHandler = new BasicResponseHandler();
        response = client.execute(post, responseHandler)
        def result = JSON.parse(response)

        def date = parseDate(result.d.Title.split(' ').last() as String)
        def parser = new org.cyberneko.html.parsers.SAXParser()
        def html = new XmlSlurper(parser).parseText(result.d.Markup)

        def rows = html?.'**'?.findAll {
            it?.name() == 'TR'
        }
        rows.remove(0)
        rows.remove(0)
        rows.remove(rows.size() -1)
        rows.each { row ->
            def marketActivity = new CommodityMarketActivityEvent()
            marketActivity.date = date
            marketActivity.marketIdentifier = CommodityMarketHelper.marketIdentifier(row.children()[0].text()?.trim())
            marketActivity.internalVolume = ('0' + row.children()[1].text()?.trim()?.replace(',', '')) as Double
            marketActivity.exportVolume = ('0' + row.children()[2].text()?.trim()?.replace(',', '')) as Double
            marketActivity.internalValue = ('0' + row.children()[3].text()?.trim()?.replace(',', '')) as Double
            marketActivity.exportValue = ('0' + row.children()[4].text()?.trim()?.replace(',', '')) as Double
            marketActivity.internalBuyersCount = ('0' + row.children()[5].text()?.trim()?.replace(',', '')) as Integer
            marketActivity.exportBuyersCount = ('0' + row.children()[6].text()?.trim()?.replace(',', '')) as Integer
            marketActivity.internalSellersCount = ('0' + row.children()[7].text()?.trim()?.replace(',', '')) as Integer
            marketActivity.exportSellersCount = ('0' + row.children()[8].text()?.trim()?.replace(',', '')) as Integer
            marketActivity.internalTradeCount = ('0' + row.children()[9].text()?.trim()?.replace(',', '')) as Integer
            marketActivity.exportTradeCount = ('0' + row.children()[10].text()?.trim()?.replace(',', '')) as Integer

            marketActivity.data = CommodityMarketActivity.findByMarketIdentifierAndDate(marketActivity.marketIdentifier, marketActivity.date)
            commodityEventGateway.send(marketActivity)
        }

    }


    private static Date parseDate(String date) {
        date = date?.trim()
        def dateParts = date.split("/").collect { it as Integer }
        JalaliCalendar jc = new JalaliCalendar(dateParts[0], dateParts[1], dateParts[2])
        def cal = jc.toJavaUtilGregorianCalendar()
        cal.time
    }
}
