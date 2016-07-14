package stocks.timeSeries

import grails.plugin.cache.Cacheable
import groovy.time.TimeCategory
import org.codehaus.groovy.grails.web.json.JSONObject
import stocks.rate.Metal
import stocks.rate.event.MetalEvent

class MetalSeries9Service {

    def timeSeriesDB9Service

    def write(List<MetalEvent> metalEvents, newDBOnly = false) {

        def serie = new Serie()
        metalEvents.each { metalEvent ->
            [
                    "price",
                    "change",
                    "percent",
                    "low",
                    "high"
            ].each { property ->
                if (metalEvent.time)
                    serie.addPoint(new Point("metal_${property}")
                            .tags([metalId: metalEvent.dataId])
                            .time(metalEvent.time)
                            .value(metalEvent."${property}"))
            }

        }

        if (!newDBOnly)
            timeSeriesDB9Service.write(serie)
        timeSeriesDB9Service.write(serie, 'chahartablo')
    }

    @Cacheable(value = 'sparkLine', key = '#symbol.toString().concat("-").concat(#daysCount.toString())')
    def sparkLine(String symbol, Integer daysCount) {
        def symbolId = Metal.findBySymbol(symbol)?.id
        priceList(symbolId, new Date() - daysCount, new Date(), '1d')?.collect { it.value } ?: []
    }

    def priceList(Long metalId, Date startDate = null, Date endDate = null, String groupingMode = '1d') {
        valueList(metalId, 'price', startDate, endDate, groupingMode)
    }

    def lastPrice(Long metalId, Date endDate = null) {
        lastValue(metalId, 'price', endDate)
    }

    def changeList(Long metalId, Date startDate = null, Date endDate = null, String groupingMode = '1d') {
        valueList(metalId, 'change', startDate, endDate, groupingMode)
    }

    def lastChange(Long metalId, Date endDate = null) {
        lastValue(metalId, 'change', endDate)
    }

    def percentList(Long metalId, Date startDate = null, Date endDate = null, String groupingMode = '1d') {
        valueList(metalId, 'percent', startDate, endDate, groupingMode)
    }

    def lastPercent(Long metalId, Date endDate = null) {
        lastValue(metalId, 'percent', endDate)
    }

    def lowList(Long metalId, Date startDate = null, Date endDate = null, String groupingMode = '1d') {
        valueList(metalId, 'low', startDate, endDate, groupingMode)
    }

    def lastLow(Long metalId, Date endDate = null) {
        lastValue(metalId, 'low', endDate)
    }

    def highList(Long metalId, Date startDate = null, Date endDate = null, String groupingMode = '1d') {
        valueList(metalId, 'high', startDate, endDate, groupingMode)
    }

    def lastHigh(Long metalId, Date endDate = null) {
        lastValue(metalId, 'high', endDate)
    }

    def metalHistoryList(Long metalId, Date startDate = null, Date endDate = null, String groupingMode = '1d') {
        if (!startDate)
            use(TimeCategory) {
                startDate = firstMetalDate(metalId)
            }
        if (!endDate)
            endDate = new Date()
        use(TimeCategory) {
            endDate = endDate + 1.day
        }
        def propertyList = [
                "price",
                "change",
                "percent",
                "low",
                "high"
        ]
        def series = timeSeriesDB9Service.query("SELECT LAST(value) FROM ${propertyList.collect { pr -> "metal_${pr}" }.join(', ')} WHERE metalId = '${metalId}' AND time >= ${startDate.time * 1000}u and time <= ${endDate.time * 1000}u GROUP BY time(${groupingMode})")[0]?.series
        def list = []
        for (def i = 0; i < series.collect { it.values.size() }.min(); i++) {
            def item = [:]
            item.metalId = metalId
            item.date = Date.parse("yyyy-MM-dd'T'hh:mm:ss'Z'", series[0].values[i][0])
            series.each { serie ->
                item."${serie.name.split('_').last()}" = serie.values[i][1] as Double
            }
            if (item.price)
                list << item
        }
        list.sort { it.date }

    }

    def valueList(Long metalId, String property, Date startDate = null, Date endDate = null, String groupingMode = '1d') {
        if (!startDate)
            use(TimeCategory) {
                startDate = new Date() - 20.years
            }
        if (!endDate)
            endDate = new Date()
        use(TimeCategory) {
            endDate = endDate + 1.day
        }
        def values = timeSeriesDB9Service.query("SELECT LAST(value) FROM metal_${property} WHERE metalId = '${metalId}' AND time >= ${startDate.time * 1000}u and time <= ${endDate.time * 1000}u GROUP BY time(${groupingMode})")[0]?.series?.values
        values ? values[0].findAll { it[1] }.collect {
            [date: Date.parse("yyyy-MM-dd'T'hh:mm:ss'Z'", it[0]), value: it[1] as Double]
        } : []
    }

    Double lastValue(Long metalId, String property, Date endDate = null) {
        if (!endDate)
            endDate = new Date()
        use(TimeCategory) {
            endDate = endDate + 1.day
        }
        def values = timeSeriesDB9Service.query("SELECT LAST(value) FROM metal_${property} WHERE metalId = '${metalId}' AND time <= ${endDate.time * 1000}u")[0]?.series?.values
        def value = values ? values[0].find()[1] : null
        if (value == JSONObject.NULL)
            return null
        value as Double
    }

    Date firstMetalDate(Long metalId) {
        MetalEvent.createCriteria().list {
            data {
                eq('id', metalId)
            }
            projections {
                property('time')
            }
            order('time', ORDER_ASCENDING)
            maxResults(1)
        }[0]
    }
}
