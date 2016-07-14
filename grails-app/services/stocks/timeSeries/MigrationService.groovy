package stocks.timeSeries

import groovy.time.TimeCategory
import stocks.util.HttpHelper

import static groovyx.gpars.GParsPool.withPool

class MigrationService {

    static transactional = false

    def sourceServerUrl = "http://82.99.217.213:8086"
    def sourceDBName = 'stocks'
    def targetServerUrl = "http://82.99.217.213:8086"
    def targetDBName = 'stocks_'
    def timeSeriesDB9Service

    def migrate() {
        log.error("starting migration")
        def measurements = query(sourceServerUrl, sourceDBName, 'SHOW MEASUREMENTS')
        log.error("measurements received")
        def list = measurements.series.find().find().values
        log.error(list?.size())
        def size = list.size()
        def indexer = 1;

        log.error("entering pool")
        withPool(12) {
            log.error("pool entered")
            list.eachParallel { measurement ->
                log.error("parallel tasks")
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

    def testAuthorization() {
        def serie = new Serie()
        serie.addPoint(new Point("test2")
                .tags([adjustmentType: '1', symbolId: '2'])
                .time(new Date())
                .value(10D))
        timeSeriesDB9Service.write(serie, "test")
    }

    def generateDummyData() {

        def random = new Random()
        (1..7).each { adjustmentType ->
            withPool(50) {
                (0..1000).eachParallel { symbolId ->
                    println("ADJ: ${adjustmentType}\t\t\tSYM: ${symbolId}")
                    def serie = new Serie()
                    (0..(365 * 3)).each { day ->
                        def date = new Date()
                        use(TimeCategory) {
                            date = date - (day).days
                        }
                        serie.addPoint(new Point("test2")
                                .tags([adjustmentType: adjustmentType, symbolId: symbolId])
                                .time(date)
                                .value(random.nextDouble()))
                    }
                    timeSeriesDB9Service.write(serie, "test")
                }
            }
        }
        println("finished")
    }


    def migrateSpecificTwoDimensionalMeasurement(String measurementName) {


        def tagKeys = query(sourceServerUrl, sourceDBName, "SHOW TAG KEYS FROM \"${measurementName}\"").series.find().find().values?.collect {
            it?.find()
        }
        println(tagKeys)
        def tags = [:]
        tagKeys.each { tagKey ->
            def tagValues = query(sourceServerUrl, sourceDBName, "SHOW TAG VALUES FROM \"${measurementName}\" WITH KEY = \"${tagKey}\"").series.find().find().values.collect {
                it?.last()
            }
            tags.put(tagKey, tagValues)
            println(tagValues)
        }
        println(tags)

        def firstTag = tagKeys?.first()
        def secondTag = tagKeys?.last()
        for (def i = 0; i < tags[firstTag].size(); i++) {
            for (def j = 0; j < tags[secondTag].size(); j++) {

                def serie = new Serie()
                def values = query(sourceServerUrl, sourceDBName, "SELECT value FROM ${measurementName} WHERE ${firstTag}='${tags[firstTag][i]}' AND ${secondTag}='${tags[secondTag][j]}'")[0]?.series?.values
                def points = values ? values[0].findAll { it[1] }.collect {
                    [date: Date.parse("yyyy-MM-dd'T'hh:mm:ss'Z'", it[0]), value: it[1] as Double]
                }.findAll { it.value } : []


                def resultTags = [:]
                resultTags.put(firstTag, tags[firstTag][i])
                resultTags.put(secondTag, tags[secondTag][j])

                points.each { point ->

                    def date = point.date
                    use(TimeCategory) {
                        date = (date + 1.day).clearTime()
                    }
                    serie.addPoint(new Point(measurementName)
                            .tags(resultTags)
                            .time(date)
                            .value(point.value))
                }
                write(targetServerUrl, targetDBName, serie)
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
