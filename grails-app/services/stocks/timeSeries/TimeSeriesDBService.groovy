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
        postCommand(path, serie.toJSON())
    }

    def query(String query) {
        def path = "/query"
        getCommand(path, [db: DBName, q: query])?.results
    }

    private def postCommand(String path, data) {
        HttpHelper.postText(
                serverUrl,
                "${path}?${authenticationString}&time_precision=u",
                data)
    }

    private def getCommand(String path, data) {
        HttpHelper.getText(
                serverUrl,
                "${path}?${authenticationString}&time_precision=u",
                data)
    }
}
