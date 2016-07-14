package stocks.timeSeries

import org.influxdb.InfluxDB
import org.influxdb.InfluxDBFactory
import stocks.util.HttpHelper

class TimeSeriesDB9Service {

    static transactional = false

    def grailsApplication
//    def smsService

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

    String getDBUsername() {
        grailsApplication.config.timeSeries.dataSource9.username?.toString()
    }

    String getDBPassword() {
        grailsApplication.config.timeSeries.dataSource9.password?.toString()
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

    void write(Serie serie, String dbName = null) {

        if (grailsApplication.config.timeSeriesDisabled)
            return

        def path = "/write"
        serie.databaseName(dbName ?: DBName)
        serie.retentionPolicy('default')
        def succeed = false
        while (!succeed) {
            serie?.toPagedCSV(windowSize)?.each {
                try {
                    postCommand(path, it, dbName ?: DBName)
                    successfulPosts++
                    succeed = true
                    windowSize = [windowSize + 5, 100].min()
                    failedPosts = 0;
                }
                catch (ignored) {
                    windowSize = [1, Math.round(windowSize / 2)].max()
                    failedPosts++
//                    if(failedPosts == 100)
//                        smsService.sendCustomMessage('09122110811', 'time series db is not responding to write requests')
                    println "window size: ${windowSize}"
                    Thread.sleep([failedPosts, 5 * 60].min() * 1000)
                }
            }
        }
    }

    def query(String query, String dbName = null) {

        if (grailsApplication.config.timeSeriesDisabled)
            return null

        def path = "/query"
        def result = null
        def succeed = false
        while (!succeed) {
            try {
                result = getCommand(path, [db: dbName ?: DBName, q: query])?.results
                succeed = true
                failedQueries = 0
            }
            catch (ignored) {
                failedQueries++
//                if(failedQueries == 100)
//                    smsService.sendCustomMessage('09122110811', 'time series db is not responding to read requests')
                Thread.sleep([failedQueries, 5 * 60].min() * 1000)
            }

        }
        result
    }

    private def postCommand(String path, data, String dbName = null) {
        HttpHelper.postText(
                serverUrl,
                "${path}?db=${dbName ?: DBName}",
                data,
                DBUsername,
                DBPassword
        )
    }

    private def getCommand(String path, data, String dbName = null) {
        HttpHelper.getText(
                serverUrl,
                "${path}?db=${dbName ?: DBName}",
                data,
                DBUsername,
                DBPassword)
    }
}
