package stocks.timeSeries

import grails.plugin.cache.Cacheable
import groovy.time.TimeCategory
import org.codehaus.groovy.grails.web.json.JSONObject
import stocks.rate.Currency
import stocks.rate.event.CurrencyEvent

class CurrencySeries9Service {

    def timeSeriesDB9Service

    def write(List<CurrencyEvent> currencyEvents) {

        def serie = new Serie()
        currencyEvents.each { currencyEvent ->
            [
                    "price",
                    "change",
                    "percent",
                    "low",
                    "high"
            ].each { property ->
                if (currencyEvent.time)
                    serie.addPoint(new Point("currency_${property}")
                            .tags([currencyId: currencyEvent.dataId])
                            .time(currencyEvent.time)
                            .value(currencyEvent."${property}"))
            }

        }

        timeSeriesDB9Service.write(serie)
    }

    @Cacheable(value = 'sparkLine', key = '#symbol.toString().concat("-").concat(#daysCount.toString())')
    def sparkLine(String symbol, Integer daysCount) {
        def symbolId = Currency.findBySymbol(symbol)?.id
        priceList(symbolId, new Date() - daysCount, new Date(), '1d')?.collect { it.value } ?: []
    }

    def priceList(Long currencyId, Date startDate = null, Date endDate = null, String groupingMode = '1d') {
        valueList(currencyId, 'price', startDate, endDate, groupingMode)
    }

    def lastPrice(Long currencyId, Date endDate = null) {
        lastValue(currencyId, 'price', endDate)
    }

    def changeList(Long currencyId, Date startDate = null, Date endDate = null, String groupingMode = '1d') {
        valueList(currencyId, 'change', startDate, endDate, groupingMode)
    }

    def lastChange(Long currencyId, Date endDate = null) {
        lastValue(currencyId, 'change', endDate)
    }

    def percentList(Long currencyId, Date startDate = null, Date endDate = null, String groupingMode = '1d') {
        valueList(currencyId, 'percent', startDate, endDate, groupingMode)
    }

    def lastPercent(Long currencyId, Date endDate = null) {
        lastValue(currencyId, 'percent', endDate)
    }

    def lowList(Long currencyId, Date startDate = null, Date endDate = null, String groupingMode = '1d') {
        valueList(currencyId, 'low', startDate, endDate, groupingMode)
    }

    def lastLow(Long currencyId, Date endDate = null) {
        lastValue(currencyId, 'low', endDate)
    }

    def highList(Long currencyId, Date startDate = null, Date endDate = null, String groupingMode = '1d') {
        valueList(currencyId, 'high', startDate, endDate, groupingMode)
    }

    def lastHigh(Long currencyId, Date endDate = null) {
        lastValue(currencyId, 'high', endDate)
    }

    def currencyHistoryList(Long currencyId, Date startDate = null, Date endDate = null, String groupingMode = '1d') {
        if (!startDate)
            use(TimeCategory) {
                startDate = firstCurrencyDate(currencyId)
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
        def series = timeSeriesDB9Service.query("SELECT LAST(value) FROM ${propertyList.collect { pr -> "currency_${pr}" }.join(', ')} WHERE currencyId = '${currencyId}' AND time >= ${startDate.time * 1000}u and time <= ${endDate.time * 1000}u GROUP BY time(${groupingMode})")[0]?.series
        def list = []
        for (def i = 0; i < series.collect { it.values.size() }.min(); i++) {
            def item = [:]
            item.currencyId = currencyId
            item.date = Date.parse("yyyy-MM-dd'T'hh:mm:ss'Z'", series[0].values[i][0])
            series.each { serie ->
                item."${serie.name.split('_').last()}" = serie.values[i][1] as Double
            }
            if (item.price)
                list << item
        }
        list.sort { it.date }

    }

    def valueList(Long currencyId, String property, Date startDate = null, Date endDate = null, String groupingMode = '1d') {
        if (!startDate)
            use(TimeCategory) {
                startDate = new Date() - 20.years
            }
        if (!endDate)
            endDate = new Date()
        use(TimeCategory) {
            endDate = endDate + 1.day
        }
        def values = timeSeriesDB9Service.query("SELECT LAST(value) FROM currency_${property} WHERE currencyId = '${currencyId}' AND time >= ${startDate.time * 1000}u and time <= ${endDate.time * 1000}u GROUP BY time(${groupingMode})")[0]?.series?.values
        values ? values[0].findAll { it[1] }.collect {
            [date: Date.parse("yyyy-MM-dd'T'hh:mm:ss'Z'", it[0]), value: it[1] as Double]
        } : []
    }

    Double lastValue(Long currencyId, String property, Date endDate = null) {
        if (!endDate)
            endDate = new Date()
        use(TimeCategory) {
            endDate = endDate + 1.day
        }
        def values = timeSeriesDB9Service.query("SELECT LAST(value) FROM currency_${property} WHERE currencyId = '${currencyId}' AND time <= ${endDate.time * 1000}u")[0]?.series?.values
        def value = values ? values[0].find()[1] : null
        if (value == JSONObject.NULL)
            return null
        value as Double
    }

    Date firstCurrencyDate(Long currencyId) {
        CurrencyEvent.createCriteria().list {
            data {
                eq('id', currencyId)
            }
            projections {
                property('time')
            }
            order('time', ORDER_ASCENDING)
            maxResults(1)
        }[0]
    }
}
