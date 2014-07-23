package stocks

import groovyx.net.http.HTTPBuilder

import java.text.DateFormat
import java.text.SimpleDateFormat

import static groovyx.net.http.ContentType.URLENC

class ChartController {

    def index() {}

    def save(){

        def http = new HTTPBuilder('http://export.highcharts.com/')
        def postBody = [type: params.type, async: params.async, options: params.options]

        http.post( path: '/', body: postBody,
                requestContentType: URLENC ) { resp1, reader1 ->

            http.get( path: "/${reader1.toString()}") { resp2, reader2 ->

                def chart = new Chart()
                chart.options = params.options
                chart.image = reader2.getBytes()
                if(chart.validate() && chart.save())
                {
                    render chart.id
                    return
                }
            }
        }

        render ''
    }

    def image(){
        def content = Chart.get(params.id)?.image
        if (content) {
            def seconds = 3600 * 24
            DateFormat httpDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
            httpDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            Calendar cal = new GregorianCalendar();
            cal.add(Calendar.SECOND, seconds);
            response.setHeader("Cache-Control", "PUBLIC, max-age=" + seconds + ", must-revalidate");
            response.setHeader("Expires", httpDateFormat.format(cal.getTime()));
            response.contentType = 'image/png'
            response.setStatus(200)
            response.outputStream << content
            response.outputStream.flush()
        } else
            render ""
    }

    def studio(){

    }
}
