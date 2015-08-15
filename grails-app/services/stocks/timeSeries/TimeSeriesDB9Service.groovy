package stocks.timeSeries

import org.influxdb.InfluxDB
import org.influxdb.InfluxDBFactory
import stocks.util.HttpHelper

class TimeSeriesDB9Service {

    def grailsApplication

    private def conn = null

    InfluxDB getConnection() {
        if (!conn)
            conn = InfluxDBFactory.connect(
                    "http://${grailsApplication.config.timeSeries.dataSource9.host}:${grailsApplication.config.timeSeries.dataSource9.port}",
                    grailsApplication.config.timeSeries.dataSource9.username?.toString(),
                    grailsApplication.config.timeSeries.dataSource9.password?.toString());
        conn
    }

    String getServerUrl() {
        "http://${grailsApplication.config.timeSeries.dataSource9.host}:${grailsApplication.config.timeSeries.dataSource9.port}"
    }

    String getDBName() {
        grailsApplication.config.timeSeries.dataSource9.db?.toString()
    }

    String getAuthenticationString() {
        "u=${grailsApplication.config.timeSeries.dataSource9.username}&p=${grailsApplication.config.timeSeries.dataSource9.password}"
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
    private static failedQueries = 0

    void dropSerie(String serieName) {

        query("DROP SERIES FROM ${serieName}")
    }

    void write(Serie serie) {

        if(grailsApplication.config.timeSeriesDisabled)
            return

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
                failedPosts = 0;
            }
            catch (ignored) {
                windowSize = [1, Math.round(windowSize / 2)].max()
                failedPosts++
                println "window size: ${windowSize}"
                Thread.sleep([failedPosts, 5 * 60].min() * 1000)
            }
        }
    }

    def query(String query) {

        if(grailsApplication.config.timeSeriesDisabled)
            return null

        def path = "/query"
        def result = null
        def succeed = false
        while (!succeed) {
            try {
                result = getCommand(path, [db: DBName, q: query])?.results
                succeed = true
                failedQueries = 0
            }
            catch (ignored) {
                failedQueries++
                Thread.sleep([failedQueries, 5 * 60].min() * 1000)
            }

        }
        result
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
