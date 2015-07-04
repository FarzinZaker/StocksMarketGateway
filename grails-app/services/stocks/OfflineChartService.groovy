package stocks

import org.codehaus.groovy.grails.web.context.ServletContextHolder

class OfflineChartService{

    def heatMap(){
        def command = "node ${ServletContextHolder.servletContext.getRealPath('/')}heat-map/heatMap.js"
        def proc = command.execute()
        proc.waitFor()

        println "heatMap created in ${ServletContextHolder.servletContext.getRealPath('/')}heat-map/heatMap.svg"
    }
}
