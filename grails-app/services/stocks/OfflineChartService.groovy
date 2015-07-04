package stocks

import org.codehaus.groovy.grails.web.context.ServletContextHolder

class OfflineChartService {

    def heatMap() {
//        def processBuilder = new ProcessBuilder("/usr/local/bin/node heatMap.js")
//        processBuilder.redirectErrorStream(true)
//        processBuilder.directory(new File("/var/node/"))
//        def process = processBuilder.start()
//        process.inputStream.eachLine {println(it)}
//        process.waitFor()

//        println('starting copy svg file'
        PrintWriter writer = new PrintWriter("${ServletContextHolder.servletContext.getRealPath('/')}/heat-map/heatMap.svg");
        writer.print("");
        writer.close();
        new File("${ServletContextHolder.servletContext.getRealPath('/')}/heat-map/heatMap.svg") << new File('/home/deploy/node/heatMap.svg').bytes

//        def processBuilder = new ProcessBuilder("cp /home/deploy/node/heatMap.svg ${ServletContextHolder.servletContext.getRealPath('/')}/heat-map/heatMap.svg")
//        processBuilder.redirectErrorStream(true)
//        def process = processBuilder.start()
//        process.inputStream.eachLine {println(it)}
//        process.waitFor()

        println('finished copy svg file')
    }
}
