package stocks.timeSeries

import grails.converters.JSON
import org.influxdb.InfluxDB
import org.influxdb.InfluxDBFactory
import stocks.util.HttpHelper

import java.util.concurrent.TimeUnit

class TimeSeriesDBService {

    def grailsApplication

    private def conn = null

    InfluxDB getConnection() {
        if (!conn)
            conn = InfluxDBFactory.connect(
                    "http://${grailsApplication.config.timeSeries.dataSource.host}:${grailsApplication.config.timeSeries.dataSource.port}",
                    grailsApplication.config.timeSeries.dataSource.username?.toString(),
                    grailsApplication.config.timeSeries.dataSource.password?.toString());
        conn
    }

    String getServerUrl() {
        "http://${grailsApplication.config.timeSeries.dataSource.host}:${grailsApplication.config.timeSeries.dataSource.port}"
    }

    String getDBName() {
        grailsApplication.config.timeSeries.dataSource.db?.toString()
    }

    String getAuthenticationString() {
        "u=${grailsApplication.config.timeSeries.dataSource.username}&p=${grailsApplication.config.timeSeries.dataSource.password}"
    }

    void createDB() {
        connection.createDatabase(DBName)
    }

    void dropDB() {
        connection.deleteDatabase(DBName)
    }

    private static windowSize = 50
    private static successfulPosts = 0
    private static failedPosts = 0

    void dropSerie(String serieName){

        query("DROP SERIES FROM ${serieName}")
    }

    void write(Serie serie) {
        def path = "/write"
        serie.databaseName(DBName)
        serie.retentionPolicy('default')
        def succeed = false
        while (!succeed) {
            try {
                serie.toPagedCSV(windowSize).each {
                    postCommand(path, it)

                    successfulPosts++
//                    println "written ${windowSize} records"
                }
                succeed = true
                windowSize = [windowSize + 5, 100].min()
            }
            catch (ex) {
//                println "post command failed with message: ${ex.message}"
                windowSize = [1, Math.round(windowSize / 2)].max()
                failedPosts++
                println "window size: ${windowSize}"
                Thread.sleep(100)
            }
//            println "${successfulPosts} succeed, ${failedPosts} failed"
        }
    }

    def query(String query) {
        def path = "/query"
        getCommand(path, [db: DBName, q: query])?.results
    }

    private def postCommand(String path, data) {
        HttpHelper.postText(
                serverUrl,
                "${path}?db=${DBName}",
                data)
    }

    private def getCommand(String path, data) {
        HttpHelper.getText(
                serverUrl,
                "${path}?db=${DBName}",
                data)
    }
}
