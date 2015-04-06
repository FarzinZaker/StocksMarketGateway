package stocks.rate.data

import fi.joensuu.joyds1.calendar.JalaliCalendar
import grails.converters.JSON
import org.apache.commons.httpclient.HttpClient
import org.apache.commons.httpclient.HttpMethod
import org.apache.commons.httpclient.methods.GetMethod
import stocks.RandomUserAgent
import stocks.RateHelper
import stocks.rate.Currency
import stocks.rate.event.CurrencyEvent

class CurrencyDataService {
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
        parseData("http://www.tgju.org/call.php")
    }

    private static Currency find(CurrencyEvent object) {
        Currency.withTransaction {
            Currency.findBySymbol(object.symbol)
        }
    }

    private void parseData(String url) {

        try {
            def str = getList(url)
            if(!str)
                return
            def list = JSON.parse(str)

            RateHelper.CURRENCIES.keySet().each { key ->

                def object = list."${RateHelper.CURRENCIES."${key}".source}"
                if(object) {
                    def currencyEvent = new CurrencyEvent()
                    currencyEvent.symbol = key
                    currencyEvent.price = object.p.toString().replaceAll(',', '') as Double
                    currencyEvent.change = (object.dt.toString().equals("low") ? -1 : 1) * (object.d.toString().replaceAll(',', '') as Double)
                    currencyEvent.percent = object.dp.toString().replaceAll(',', '') as Double
                    currencyEvent.low = object.l.toString().replaceAll(',', '') as Double
                    currencyEvent.high = object.h.toString().replaceAll(',', '') as Double
                    currencyEvent.time = parseTime(object.t.toString())

                    currencyEvent.data = find(currencyEvent)
                    rateEventGateway.send(currencyEvent, this.class.name)
                }
            }
            logState([status: 'successful'])
        }
        catch (ex) {
            ex.printStackTrace()
            logState([status: 'failed', message: ex.message, stackTrace: ex.stackTrace])
        }
    }

    private static String getList(String url) {
        try {
            HttpClient client = new HttpClient()
            client.getParams().setParameter("http.protocol.content-charset", "UTF-8")
            HttpMethod method = new GetMethod("${url}?t=" + new Date().getTime())
            method.setRequestHeader("User-Agent", RandomUserAgent.randomUserAgent)
            method.setRequestHeader("Accept-Language", "en-US,en;q=0.5")
            client.executeMethod(method)

            method.getResponseBodyAsString()
        }catch (ignored){
            null
        }
    }


    private static Date parseTime(String time) {
        JalaliCalendar jc = new JalaliCalendar()
        def cal = jc.toJavaUtilGregorianCalendar()
        try {
            def timeParts = time.split(":")
            cal.set(Calendar.HOUR_OF_DAY, timeParts[0] as Integer)
            cal.set(Calendar.MINUTE, timeParts[1] as Integer)
            cal.set(Calendar.SECOND, 0)
        } catch (ignored) {
        }
        cal.time
    }
}
