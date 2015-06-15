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

    void write(Serie serie) {
        def path = "/write"
        serie.databaseName(DBName)
        serie.retentionPolicy('default')
        serie.toPagedCSV(1000).each {
            postCommand(path, it)
        }
    }

    def query(String query) {
        def path = "/query"
        getCommand(path, [db: DBName, q: query])?.results
    }

    private static successfulPosts = 0;
    private static failedPosts = 0;

    private def postCommand(String path, data) {
        def succeed = false
        while (!succeed) {
            try {
                HttpHelper.postText(
                        serverUrl,
                        "${path}?db=${DBName}",
                        data)
                succeed = true
                successfulPosts++
            }
            catch (ex) {
//                println "post command failed with message: ${ex.message}"
                failedPosts++
                Thread.sleep(100)
            }
        }

//        println "${successfulPosts} succeed, ${failedPosts} failed"
    }

    private def getCommand(String path, data) {
        HttpHelper.getText(
                serverUrl,
                "${path}?db=${DBName}",
                data)
    }
}
