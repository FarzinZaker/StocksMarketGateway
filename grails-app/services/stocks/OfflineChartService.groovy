package stocks

import org.codehaus.groovy.grails.web.context.ServletContextHolder

class OfflineChartService {

    def heatMap() {
        def processBuilder = new ProcessBuilder("node heatMap.js")
        processBuilder.redirectErrorStream(true)
        processBuilder.directory(new File("/var/node/"))
        def process = processBuilder.start()
        process.inputStream.eachLine {println(it)}
        process.waitFor()

        println('starting copy svg file')

        processBuilder = new ProcessBuilder("cp /var/node/heatMap.svg ${ServletContextHolder.servletContext.getRealPath('/')}/heat-map/heatMap.svg")
        processBuilder.redirectErrorStream(true)
        process = processBuilder.start()
        process.inputStream.eachLine {println(it)}
        process.waitFor()

        println('finished copy svg file')
    }
}
