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
                            parameters: [repeatInterval: 90000l, startDelay: 60000]
                    ]
            ]
    ]

    void importData() {
        parseData("http://www.iranjib.ir/isync.json")
    }

    private static Currency find(CurrencyEvent object) {
        Currency.withTransaction {
            Currency.findBySymbol(object.symbol)
        }
    }

    private void parseData(String url) {

        try {
            def str = getList(url)
            if (!str)
                return
            def list = JSON.parse(str)

            RateHelper.CURRENCIES.keySet().each { key ->

                def source = RateHelper.CURRENCIES."${key}".source
                def name = RateHelper.CURRENCIES."${key}".name

                def currencyEvent = new CurrencyEvent()
                currencyEvent.price = (list."f_${source}_68_pr".toString().replaceAll("<[^>].*?>|&lrm;|&rlm;|\\(|\\)|,| ", "") as Double) * 10
                String cp = list."f_${source}_64".toString().replaceAll("<[^>].*?>|&lrm;|&rlm;|\\(|\\)|,| ", "")
                currencyEvent.change = (cp.split("%")[1] as Double) * 10
                currencyEvent.percent = cp.split("%")[0] as Double
                currencyEvent.low = (list."f_${source}_65".toString().replaceAll("<[^>].*?>|&lrm;|&rlm;|\\(|\\)|,| ", "") as Double) * 10
                currencyEvent.high = (list."f_${source}_66".toString().replaceAll("<[^>].*?>|&lrm;|&rlm;|\\(|\\)|,| ", "") as Double) * 10
                currencyEvent.name = name
                currencyEvent.symbol = key
                currencyEvent.time = new Date()

                currencyEvent.data = find(currencyEvent)
                rateEventGateway.send(currencyEvent)
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
            HttpMethod method = new GetMethod(url)
            method.setRequestHeader("User-Agent", RandomUserAgent.randomUserAgent)
            method.setRequestHeader("Accept-Language", "en-US,en;q=0.5")
            client.executeMethod(method)

            method.getResponseBodyAsString()
        } catch (ignored) {
            null
        }
    }
}
