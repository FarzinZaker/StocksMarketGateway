package stocks.timeSeries

import stocks.util.HttpHelper

import static groovyx.gpars.GParsPool.withPool

class MigrationService {

    def sourceServerUrl = "http://192.168.64.3:8086"
    def sourceDBName = 'stocks'
    def targetServerUrl = "http://127.0.0.1:8086"
    def targetDBName = 'stocks'

    def migrate() {
        def measurements = query(sourceServerUrl, sourceDBName, 'SHOW MEASUREMENTS')
        def list = measurements.series.find().find().values
        def size = list.size()
        def indexer = 1;

        withPool(12) {
            list.eachParallel { measurement ->
                try {
                    def serie = new Serie()

                    def values = query(sourceServerUrl, sourceDBName, "SELECT value FROM ${measurement.find()}")[0]?.series?.values
                    def points = values ? values[0].findAll { it[1] }.collect {
                        [date: Date.parse("yyyy-MM-dd'T'hh:mm:ss'Z'", it[0]), value: it[1] as Double]
                    }.findAll { it.value } : []

                    points.each { point ->
                        serie.addPoint(new Point(measurement.find())
                                .time(point.date)
                                .value(point.value))
                    }
                    write(targetServerUrl, targetDBName, serie)

//            println("${indexer++}\t${size}")
                    log.error("${indexer++}\t${size}")
                } catch (x) {
                    log.error(x)
                }
            }
        }
    }

    void write(String server, String db, Serie serie) {
        def path = "/write"
        serie.databaseName(db)
        serie.retentionPolicy('default')
        serie.toPagedCSV(100).each {
            postCommand(server, db, path, it)
        }
    }

    def query(String server, String db, String query) {
        def path = "/query"
        getCommand(server, db, path, [db: db, q: query])?.results
    }

    private def postCommand(String server, db, String path, data) {
        HttpHelper.postText(
                server,
                "${path}?db=${db}",
                data)
    }

    private def getCommand(String server, db, String path, data) {
        HttpHelper.getText(
                server,
                "${path}?db=${db}",
                data)
    }
}
