package stocks

import com.sun.script.javascript.RhinoScriptEngineFactory
import grails.converters.JSON
import org.codehaus.groovy.grails.io.support.GrailsResourceUtils
import org.ocpsoft.prettytime.PrettyTime
import org.watij.webspec.dsl.WebSpec
import sun.misc.GC

class DashboardController {

    def feedService
    def offlineChartService

    def index() {

    }

    def news() {
        def result = feedService.news()
        render([
                data      : result.data.collect {
                    [
                            id        : it.id,
                            title     : it.title,
                            time      : it.date.time,
                            dateString: new PrettyTime(new Locale('fa')).format(it.date),
                            link      : it.link,
                            category  : it.category,
                            source    : message(code: "newsSource.${it.source}")
                    ]
                },
                categories: result.categoryList.collect {
                    [
                            value: it,
                            text : message(code: "newsCategory.${it}")
                    ]
                }
        ] as JSON)
    }

//    static WebSpec spec

    def heatMap(){

//        def scriptEngine = new RhinoScriptEngineFactory().scriptEngine
//        println scriptEngine.eval("Math.cos(Math.PI)")


//        if(!spec)
//            spec = Class.forName('org.watij.webspec.dsl.WebSpec').newInstance().mozilla()
//
//        try {
//            spec.open("http://localhost:8080/Stocks/report/heatMapForImage")
////        spec.hide()
//            def counter = 0
//            while (!spec.findWithId('loadCompleted').exists()) {
//                Thread.sleep(1000)
//                if (++counter > 60) {
//                    spec = null
//                    GC.collect()
//                    return
//                }
//            }
//            spec.snapAll(GrailsResourceUtils.WEB_APP_DIR + "heatMap.png")
//        }
//        catch(ignored){
//            spec = null
//            GC.collect()
//        }
    }
}
