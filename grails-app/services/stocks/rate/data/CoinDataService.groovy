package stocks.rate.data

import fi.joensuu.joyds1.calendar.JalaliCalendar
import grails.converters.JSON
import stocks.RandomUserAgent
import stocks.RateHelper
import stocks.rate.Coin
import stocks.rate.event.CoinEvent
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

class CoinDataService {
    static transactional = false
    def rateEventGateway

    static schedules = [
            [
                    method : 'importData',
                    trigger: [
                            type      : 'Simple',
                            parameters: [repeatInterval: 5000l, startDelay: 60000]
                    ]
            ]
    ]

    void importData() {
        parseData("http://www.tgju.org/call.php")
    }

    private static Coin find(CoinEvent object) {
        Coin.withTransaction {
            Coin.findBySymbol(object.symbol)
        }
    }

    private void parseData(String url) {

        try {
            def list = JSON.parse(getList(url))

            RateHelper.COINS.keySet().each { key ->

                def object = list."${RateHelper.COINS."${key}".source}"
                if(object) {
                    def coinEvent = new CoinEvent()
                    coinEvent.symbol = key
                    coinEvent.price = object.p.toString().replaceAll(',', '') as Double
                    coinEvent.change = (object.dt.toString().equals("low") ? -1 : 1) * (object.d.toString().replaceAll(',', '') as Double)
                    coinEvent.percent = object.dp.toString().replaceAll(',', '') as Double
                    coinEvent.low = object.l.toString().replaceAll(',', '') as Double
                    coinEvent.high = object.h.toString().replaceAll(',', '') as Double
                    coinEvent.time = parseTime(object.t.toString())

                    coinEvent.data = find(coinEvent)
                    rateEventGateway.send(coinEvent)
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
        HttpClient client = new HttpClient()
        client.getParams().setParameter("http.protocol.content-charset", "UTF-8")
        HttpMethod method = new GetMethod("${url}?t=" + new Date().getTime())
        method.setRequestHeader("User-Agent", RandomUserAgent.randomUserAgent)
        method.setRequestHeader("Accept-Language", "en-US,en;q=0.5")
        client.executeMethod(method)

        method.getResponseBodyAsString()
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
