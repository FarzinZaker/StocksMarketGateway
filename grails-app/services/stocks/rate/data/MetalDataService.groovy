package stocks.rate.data

import fi.joensuu.joyds1.calendar.JalaliCalendar
import grails.converters.JSON
import org.apache.commons.httpclient.HttpClient
import org.apache.commons.httpclient.HttpMethod
import org.apache.commons.httpclient.methods.GetMethod
import stocks.RandomUserAgent
import stocks.RateHelper
import stocks.rate.Metal
import stocks.rate.event.MetalEvent

class MetalDataService {
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

    private static Metal find(MetalEvent object) {
        Metal.withTransaction {
            Metal.findBySymbol(object.symbol)
        }
    }

    private void parseData(String url) {

        try {
            def str = getList(url)
            if(!str)
                return
            def list = JSON.parse(str)

            RateHelper.METALS.keySet().each { key ->

                def object = list."${RateHelper.METALS."${key}".source}"
                if (object) {
                    def metalEvent = new MetalEvent()
                    metalEvent.symbol = key
                    metalEvent.price = object.p.toString().replaceAll(',', '') as Double
                    metalEvent.change = (object.dt.toString().equals("low") ? -1 : 1) * (object.d.toString().replaceAll(',', '') as Double)
                    metalEvent.percent = object.dp.toString().replaceAll(',', '') as Double
                    metalEvent.low = object.l.toString().replaceAll(',', '') as Double
                    metalEvent.high = object.h.toString().replaceAll(',', '') as Double
                    metalEvent.time = parseTime(object.t.toString())

                    metalEvent.data = find(metalEvent)
                    rateEventGateway.send(metalEvent, this.class.name)
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
        }catch(ignored){
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
