package stocks.timeSeries

import grails.plugin.cache.Cacheable
import groovy.time.TimeCategory
import stocks.rate.Coin
import stocks.rate.event.CoinEvent

class CoinSeries9Service {

    def timeSeriesDB9Service

    def write(List<CoinEvent> coinEvents) {

        def serie = new Serie()
        coinEvents.each { coinEvent ->
            [
                    "price",
                    "change",
                    "percent",
                    "low",
                    "high"
            ].each { property ->
                if (coinEvent.time)
                    serie.addPoint(new Point("coin_${property}")
                            .tags([coinId: coinEvent.dataId])
                            .time(coinEvent.time)
                            .value(coinEvent."${property}"))
            }

        }

        timeSeriesDB9Service.write(serie)
    }

    @Cacheable(value = 'sparkLine', key = '#symbol.toString().concat("-").concat(#daysCount.toString())')
    def sparkLine(String symbol, Integer daysCount) {
        def symbolId = Coin.findBySymbol(symbol)?.id
        priceList(symbolId, new Date() - daysCount, new Date(), '1d')?.collect { it.value } ?: []
    }

    def priceList(Long coinId, Date startDate = null, Date endDate = null, String groupingMode = '1d') {
        valueList(coinId, 'price', startDate, endDate, groupingMode)
    }

    def lastPrice(Long coinId, Date endDate = null) {
        lastValue(coinId, 'price', endDate)
    }

    def changeList(Long coinId, Date startDate = null, Date endDate = null, String groupingMode = '1d') {
        valueList(coinId, 'change', startDate, endDate, groupingMode)
    }

    def lastChange(Long coinId, Date endDate = null) {
        lastValue(coinId, 'change', endDate)
    }

    def percentList(Long coinId, Date startDate = null, Date endDate = null, String groupingMode = '1d') {
        valueList(coinId, 'percent', startDate, endDate, groupingMode)
    }

    def lastPercent(Long coinId, Date endDate = null) {
        lastValue(coinId, 'percent', endDate)
    }

    def lowList(Long coinId, Date startDate = null, Date endDate = null, String groupingMode = '1d') {
        valueList(coinId, 'low', startDate, endDate, groupingMode)
    }

    def lastLow(Long coinId, Date endDate = null) {
        lastValue(coinId, 'low', endDate)
    }

    def highList(Long coinId, Date startDate = null, Date endDate = null, String groupingMode = '1d') {
        valueList(coinId, 'high', startDate, endDate, groupingMode)
    }

    def lastHigh(Long coinId, Date endDate = null) {
        lastValue(coinId, 'high', endDate)
    }

    def coinHistoryList(Long coinId, Date startDate = null, Date endDate = null, String groupingMode = '1d') {
        if (!startDate)
            use(TimeCategory) {
                startDate = firstCoinDate(coinId)
            }
        if (!endDate)
            endDate = new Date()
        use(TimeCategory){
            endDate = endDate + 1.day
        }
        def propertyList = [
                "price",
                "change",
                "percent",
                "low",
                "high"
        ]
        def series = timeSeriesDB9Service.query("SELECT LAST(value) FROM ${propertyList.collect { pr -> "coin_${pr}" }.join(', ')} WHERE coinId = '${coinId}' AND time >= ${startDate.time * 1000}u and time <= ${endDate.time * 1000}u GROUP BY time(${groupingMode})")[0]?.series
        def list = []
        for (def i = 0; i < series.collect { it.values.size() }.min(); i++) {
            def item = [:]
            item.coinId = coinId
            item.date = Date.parse("yyyy-MM-dd'T'hh:mm:ss'Z'", series[0].values[i][0])
            series.each { serie ->
                item."${serie.name.split('_').last()}" = serie.values[i][1] as Double
            }
            if (item.price)
                list << item
        }
        list.sort { it.date }

    }

    def valueList(Long coinId, String property, Date startDate = null, Date endDate = null, String groupingMode = '1d') {
        if (!startDate)
            use(TimeCategory) {
                startDate = new Date() - 20.years
            }
        if (!endDate)
            endDate = new Date()
        use(TimeCategory){
            endDate = endDate + 1.day
        }
        def values = timeSeriesDB9Service.query("SELECT LAST(value) FROM coin_${property} WHERE coinId = '${coinId}' AND time >= ${startDate.time * 1000}u and time <= ${endDate.time * 1000}u GROUP BY time(${groupingMode})")[0]?.series?.values
        values ? values[0].findAll { it[1] }.collect {
            [date: Date.parse("yyyy-MM-dd'T'hh:mm:ss'Z'", it[0]), value: it[1] as Double]
        } : []
    }

    Double lastValue(Long coinId, String property, Date endDate = null) {
        if (!endDate)
            endDate = new Date()
        use(TimeCategory){
            endDate = endDate + 1.day
        }
        def values = timeSeriesDB9Service.query("SELECT LAST(value) FROM coin_${property} WHERE coinId = '${coinId}' AND time <= ${endDate.time * 1000}u")[0]?.series?.values
        values ? values[0].find()[1] as Double : null
    }

    Date firstCoinDate(Long coinId) {
        CoinEvent.createCriteria().list {
            data {
                eq('id', coinId)
            }
            projections {
                property('time')
            }
            order('time', ORDER_ASCENDING)
            maxResults(1)
        }[0]
    }
}
