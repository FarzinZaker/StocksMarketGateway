package stocks.rate.data

import fi.joensuu.joyds1.calendar.JalaliCalendar
import grails.converters.JSON
import groovy.time.TimeCategory
import org.apache.commons.httpclient.HttpClient
import org.apache.commons.httpclient.HttpMethod
import org.apache.commons.httpclient.methods.GetMethod
import stocks.RandomUserAgent
import stocks.RateHelper
import stocks.rate.Metal
import stocks.rate.event.MetalEvent

class MetalMissingDataService {
    static transactional = false
    def rateEventGateway

    static schedules = [
            [
                    method : 'importData',
                    trigger: [
                            type      : 'Simple',
                            parameters: [repeatInterval: 300000l, startDelay: 180000]
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

        def state = getLastState()
        if (!state) {
            state = [
                    time  : new Date().time
            ]
        }
        def date = new Date(state.time as Long)

        def calendar = Calendar.getInstance() as GregorianCalendar
        calendar.setTime(date)
        def jc = new JalaliCalendar(calendar)

        use(TimeCategory) {
            date = date - 1.days
        }

        try {
            def str = getList(url, date)
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
            calendar.setTime(date)
            jc = new JalaliCalendar(calendar)
            logState([
                    status: 'succeed',
                    date  : String.format("%04d/%02d/%02d", jc.getYear(), jc.getMonth(), jc.getDay()),
                    time  : date.time
            ])
        }
        catch (ex) {
            logState([
                    status: 'failed',
                    date  : String.format("%04d/%02d/%02d", jc.getYear(), jc.getMonth(), jc.getDay()),
                    time  : date.time,
                    message: ex.message,
                    stackTrace: ex.stackTrace
            ])
        }
    }

    private static String getList(String url, Date date) {
        try {
            HttpClient client = new HttpClient()
            client.getParams().setParameter("http.protocol.content-charset", "UTF-8")
            HttpMethod method = new GetMethod("${url}?t=" + date.getTime())
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
