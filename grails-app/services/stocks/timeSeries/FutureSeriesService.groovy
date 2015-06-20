package stocks.timeSeries

import groovy.time.TimeCategory
import stocks.rate.event.CoinFutureEvent

class FutureSeriesService {

    def timeSeriesDBService

    def write(List<CoinFutureEvent> futureEvents) {

        def serie = new Serie()
        futureEvents.each { futureEvent ->
            [
                    "closingPrice"
            ].each { property ->
                if (futureEvent.lastTradingDate)
                    serie.addPoint(new Point("future_${futureEvent.dataId}_${property}")
                            .time(futureEvent.lastTradingDate)
                            .value(futureEvent."${property}"))
            }

        }

        timeSeriesDBService.write(serie)
    }

    def closingPriceList(Long futureId, Date startDate = null, Date endDate = null, String groupingMode = '1d') {
        priceList(futureId, 'closingPrice', startDate, endDate, groupingMode)
    }

    def lastClosingPrice(Long futureId, Date endDate = null) {
        lastPrice(futureId, 'closingPrice', endDate)
    }

    def futureHistoryList(Long futureId, Date startDate = null, Date endDate = null, String groupingMode = '1d') {
        if (!startDate)
            use(TimeCategory) {
                startDate = firstFutureDate(futureId)
            }
        if (!endDate)
            endDate = new Date()
        def propertyList = [
                "closingPrice"
        ]
        def series = timeSeriesDBService.query("SELECT LAST(value) FROM ${propertyList.collect { pr -> "future_${futureId}_${pr}" }.join(', ')} WHERE time >= ${startDate.time * 1000}u and time <= ${endDate.time * 1000}u GROUP BY time(${groupingMode})")[0]?.series
        def list = []
        for (def i = 0; i < series.collect { it.values.size() }.min(); i++) {
            def item = [:]
            item.futureId = futureId
            item.date = Date.parse("yyyy-MM-dd'T'hh:mm:ss'Z'", series[0].values[i][0])
            series.each { serie ->
                item."${serie.name.split('_').last()}" = serie.values[i][1] as Double
            }
            if (item.closingPrice)
                list << item
        }
        list.sort { it.date }

    }

    def priceList(Long futureId, String property, Date startDate = null, Date endDate = null, String groupingMode = '1d') {
        if (!startDate)
            use(TimeCategory) {
                startDate = new Date() - 20.years
            }
        if (!endDate)
            endDate = new Date()
        def values = timeSeriesDBService.query("SELECT LAST(value) FROM future_${futureId}_${property} WHERE time >= ${startDate.time * 1000}u and time <= ${endDate.time * 1000}u GROUP BY time(${groupingMode})")[0]?.series?.values
        values ? values[0].findAll { it[1] }.collect {
            [date: Date.parse("yyyy-MM-dd'T'hh:mm:ss'Z'", it[0]), value: it[1] as Double]
        } : []
    }

    Double lastPrice(Long futureId, String property, Date endDate = null) {
        if (!endDate)
            endDate = new Date()
        def values = timeSeriesDBService.query("SELECT LAST(value) FROM future_${futureId}_${property} WHERE time <= ${endDate.time * 1000}u")[0]?.series?.values
        values ? values[0].find()[1] as Double : null
    }

    Date firstFutureDate(Long futureId) {
        CoinFutureEvent.createCriteria().list {
            data {
                eq('id', futureId)
            }
            projections {
                property('lastTradingDate')
            }
            order('lastTradingDate', ORDER_ASCENDING)
            maxResults(1)
        }[0]
    }
}
