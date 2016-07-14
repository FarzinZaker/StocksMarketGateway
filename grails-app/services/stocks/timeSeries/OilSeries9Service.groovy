package stocks.timeSeries

import grails.plugin.cache.Cacheable
import groovy.time.TimeCategory
import org.codehaus.groovy.grails.web.json.JSONObject
import stocks.rate.Oil
import stocks.rate.event.OilEvent

class OilSeries9Service {

    def timeSeriesDB9Service

    def write(List<OilEvent> oilEvents, newDBOnly = false) {

        def serie = new Serie()
        oilEvents.each { oilEvent ->
            [
                    "price",
                    "change",
                    "percent",
                    "open",
                    "low",
                    "high"
            ].each { property ->
                if (oilEvent.time && oilEvent."${property}")
                    serie.addPoint(new Point("oil_${property}")
                            .tags([oilId: oilEvent.dataId])
                            .time(oilEvent.time)
                            .value(oilEvent."${property}" * 1000))
            }

        }

        if (!newDBOnly)
            timeSeriesDB9Service.write(serie)
        timeSeriesDB9Service.write(serie, 'chahartablo')
    }

    @Cacheable(value = 'sparkLine', key = '#symbol.toString().concat("-").concat(#daysCount.toString())')
    def sparkLine(String symbol, Integer daysCount) {
        def symbolId = Oil.findBySymbol(symbol)?.id
        priceList(symbolId, new Date() - daysCount, new Date(), '1d')?.collect { it.value } ?: []
    }

    def priceList(Long oilId, Date startDate = null, Date endDate = null, String groupingMode = '1d') {
        valueList(oilId, 'price', startDate, endDate, groupingMode)
    }

    def lastPrice(Long oilId, Date endDate = null) {
        lastValue(oilId, 'price', endDate)
    }

    def changeList(Long oilId, Date startDate = null, Date endDate = null, String groupingMode = '1d') {
        valueList(oilId, 'change', startDate, endDate, groupingMode)
    }

    def lastChange(Long oilId, Date endDate = null) {
        lastValue(oilId, 'change', endDate)
    }

    def percentList(Long oilId, Date startDate = null, Date endDate = null, String groupingMode = '1d') {
        valueList(oilId, 'percent', startDate, endDate, groupingMode)
    }

    def lastPercent(Long oilId, Date endDate = null) {
        lastValue(oilId, 'percent', endDate)
    }

    def openList(Long oilId, Date startDate = null, Date endDate = null, String groupingMode = '1d') {
        valueList(oilId, 'open', startDate, endDate, groupingMode)
    }

    def lastOpen(Long oilId, Date endDate = null) {
        lastValue(oilId, 'open', endDate)
    }

    def lowList(Long oilId, Date startDate = null, Date endDate = null, String groupingMode = '1d') {
        valueList(oilId, 'low', startDate, endDate, groupingMode)
    }

    def lastLow(Long oilId, Date endDate = null) {
        lastValue(oilId, 'low', endDate)
    }

    def highList(Long oilId, Date startDate = null, Date endDate = null, String groupingMode = '1d') {
        valueList(oilId, 'high', startDate, endDate, groupingMode)
    }

    def lastHigh(Long oilId, Date endDate = null) {
        lastValue(oilId, 'high', endDate)
    }

    def oilHistoryList(Long oilId, Date startDate = null, Date endDate = null, String groupingMode = '1d') {
        if (!startDate)
            use(TimeCategory) {
                startDate = firstOilDate(oilId)
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
                "open",
                "low",
                "high"
        ]
        def series = timeSeriesDB9Service.query("SELECT LAST(value) FROM ${propertyList.collect { pr -> "oil_${pr}" }.join(', ')} WHERE oilId = '${oilId}' AND time >= ${startDate.time * 1000}u and time <= ${endDate.time * 1000}u GROUP BY time(${groupingMode})")[0]?.series
        def list = []
        for (def i = 0; i < series.collect { it.values.size() }.min(); i++) {
            def item = [:]
            item.oilId = oilId
            item.date = Date.parse("yyyy-MM-dd'T'hh:mm:ss'Z'", series[0].values[i][0])
            series.each { serie ->
                item."${serie.name.split('_').last()}" = (serie.values[i][1] as Double) / 1000
            }
            if (item.price)
                list << item
        }
        list.sort { it.date }

    }

    def valueList(Long oilId, String property, Date startDate = null, Date endDate = null, String groupingMode = '1d') {
        if (!startDate)
            use(TimeCategory) {
                startDate = new Date() - 20.years
            }
        if (!endDate)
            endDate = new Date()
        use(TimeCategory) {
            endDate = endDate + 1.day
        }
        def values = timeSeriesDB9Service.query("SELECT LAST(value) FROM oil_${property} WHERE oilId = '${oilId}' AND time >= ${startDate.time * 1000}u and time <= ${endDate.time * 1000}u GROUP BY time(${groupingMode})")[0]?.series?.values
        values ? values[0].findAll { it[1] }.collect {
            [date: Date.parse("yyyy-MM-dd'T'hh:mm:ss'Z'", it[0]), value: (it[1] as Double) / 1000]
        } : []
    }

    Double lastValue(Long oilId, String property, Date endDate = null) {
        if (!endDate)
            endDate = new Date()
        use(TimeCategory) {
            endDate = endDate + 1.day
        }
        def values = timeSeriesDB9Service.query("SELECT LAST(value) FROM oil_${property} WHERE oilId = '${oilId}' AND time <= ${endDate.time * 1000}u")[0]?.series?.values
        def value = values ? values[0].find()[1] : null
        if (value == JSONObject.NULL)
            return null
        value as Double
    }

    Date firstOilDate(Long oilId) {
        OilEvent.createCriteria().list {
            data {
                eq('id', oilId)
            }
            projections {
                property('time')
            }
            order('time', ORDER_ASCENDING)
            maxResults(1)
        }[0]
    }
}
