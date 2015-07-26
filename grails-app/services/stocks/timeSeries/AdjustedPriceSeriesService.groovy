package stocks.timeSeries

import grails.plugin.cache.CachePut
import grails.plugin.cache.Cacheable
import groovy.time.TimeCategory
import stocks.tse.AdjustmentHelper
import stocks.tse.SymbolDailyTrade

class AdjustedPriceSeriesService {

    def timeSeriesDBService

    def clear(Long symbolId, List<String> adjustmentTypes) {

        adjustmentTypes.each { adjustmentType ->

            [
                    'firstTradePrice',
                    'lastTradePrice',
                    'closingPrice',
                    'minPrice',
                    'maxPrice',
                    'totalTradeCount',
                    'totalTradeValue',
                    'totalTradeVolume',
                    'yesterdayPrice',
                    'priceChange'
            ].each { property ->
                timeSeriesDBService.dropSerie("dailyTrade_${adjustmentType}_${symbolId}_${property}")
            }

        }
    }

    def write(List dailyTrades, List<String> adjustmentTypes) {

        def serie = new Serie()

        adjustmentTypes.each { adjustmentType ->

            dailyTrades.each { dailyTrade ->
                if (dailyTrade.symbolId) {
                    [
                            'firstTradePrice',
                            'lastTradePrice',
                            'closingPrice',
                            'minPrice',
                            'maxPrice',
                            'totalTradeCount',
                            'totalTradeValue',
                            'totalTradeVolume',
                            'yesterdayPrice',
                            'priceChange'
                    ].each { property ->
                        serie.addPoint(new Point("dailyTrade_${adjustmentType}_${dailyTrade.symbolId}_${property}")
//                            .tags([symbolId: dailyTrade.symbolId?.toString()])
                                .time(dailyTrade.date)
                                .value(dailyTrade."${property}"))
                    }
                }

//                serie.addPoint(new Point("dailyTrade.${adjustmentType}.adjustable").tags([symbolId: dailyTrade.symbolId?.toString()]).time(dailyTrade.date).value(dailyTrade.symbolPersianCode.startsWith("تسه") ? 0 : 1))
            }
        }

        timeSeriesDBService.write(serie)
    }

    @Cacheable(value = 'sparkLine', key = '#symbolId.toString().concat("-").concat(#daysCount.toString())')
    def sparkLIine(Long symbolId, Integer daysCount) {
        priceList(symbolId, 'closingPrice', new Date() - daysCount, new Date(), '1d')?.collect { it.value } ?: []
    }

    def firstTradePriceList(Long symbolId, Date startDate = null, Date endDate = null, String groupingMode = '1d', String adjustmentType = null) {
        priceList(symbolId, 'firstTradePrice', startDate, endDate, groupingMode, adjustmentType)
    }

    def lastFirstTradePrice(Long symbolId, Date endDate = null, String adjustmentType = null) {
        lastPrice(symbolId, 'firstTradePrice', endDate, adjustmentType)
    }

    def closingPriceList(Long symbolId, Date startDate = null, Date endDate = null, String groupingMode = '1d', String adjustmentType = null) {
        priceList(symbolId, 'closingPrice', startDate, endDate, groupingMode, adjustmentType)
    }

    def lastClosingPrice(Long symbolId, Date endDate = null, String adjustmentType = null) {
        lastPrice(symbolId, 'closingPrice', endDate, adjustmentType)
    }

    def minPriceList(Long symbolId, Date startDate = null, Date endDate = null, String groupingMode = '1d', String adjustmentType = null) {
        priceList(symbolId, 'minPrice', startDate, endDate, groupingMode, adjustmentType)
    }

    def lastMinPrice(Long symbolId, Date endDate = null, String adjustmentType = null) {
        lastPrice(symbolId, 'minPrice', endDate, adjustmentType)
    }

    def maxPriceList(Long symbolId, Date startDate = null, Date endDate = null, String groupingMode = '1d', String adjustmentType = null) {
        priceList(symbolId, 'maxPrice', startDate, endDate, groupingMode, adjustmentType)
    }

    def lastMaxPrice(Long symbolId, Date endDate = null, String adjustmentType = null) {
        lastPrice(symbolId, 'maxPrice', endDate, adjustmentType)
    }

    def totalTradeCountList(Long symbolId, Date startDate = null, Date endDate = null, String groupingMode = '1d', String adjustmentType = null) {
        priceList(symbolId, 'totalTradeCount', startDate, endDate, groupingMode, adjustmentType)
    }

    def lastTotalTradeCount(Long symbolId, Date endDate = null, String adjustmentType = null) {
        lastPrice(symbolId, 'totalTradeCount', endDate, adjustmentType)
    }

    def totalTradeValueList(Long symbolId, Date startDate = null, Date endDate = null, String groupingMode = '1d', String adjustmentType = null) {
        priceList(symbolId, 'totalTradeValue', startDate, endDate, groupingMode, adjustmentType)
    }

    def lastTotalTradeValue(Long symbolId, Date endDate = null, String adjustmentType = null) {
        lastPrice(symbolId, 'totalTradeValue', endDate, adjustmentType)
    }

    def totalTradeVolumeList(Long symbolId, Date startDate = null, Date endDate = null, String groupingMode = '1d', String adjustmentType = null) {
        priceList(symbolId, 'totalTradeVolume', startDate, endDate, groupingMode, adjustmentType)
    }

    def lastTotalTradeVolume(Long symbolId, Date endDate = null, String adjustmentType = null) {
        lastPrice(symbolId, 'totalTradeVolume', endDate, adjustmentType)
    }

    def yesterdayPriceList(Long symbolId, Date startDate = null, Date endDate = null, String groupingMode = '1d', String adjustmentType = null) {
        priceList(symbolId, 'yesterdayPrice', startDate, endDate, groupingMode, adjustmentType)
    }

    def lastYesterdayPrice(Long symbolId, Date endDate = null, String adjustmentType = null) {
        lastPrice(symbolId, 'yesterdayPrice', endDate, adjustmentType)
    }

    def priceChangeList(Long symbolId, Date startDate = null, Date endDate = null, String groupingMode = '1d', String adjustmentType = null) {
        priceList(symbolId, 'priceChange', startDate, endDate, groupingMode, adjustmentType)
    }

    def lastPriceChange(Long symbolId, Date endDate = null, String adjustmentType = null) {
        lastPrice(symbolId, 'priceChange', endDate, adjustmentType)
    }

    def lastTradePriceList(Long symbolId, Date startDate = null, Date endDate = null, String groupingMode = '1d', String adjustmentType = null) {
        priceList(symbolId, 'lastTradePrice', startDate, endDate, groupingMode, adjustmentType)
    }

    def lastLastTradePrice(Long symbolId, Date endDate = null, String adjustmentType = null) {
        lastPrice(symbolId, 'lastTradePrice', endDate, adjustmentType)
    }

    def dailyTradeList(Long symbolId, Date startDate = null, Date endDate = null, String groupingMode = '1d', String adjustmentType = null) {
        if (!startDate)
            use(TimeCategory) {
                startDate = firstTradeDate(symbolId) ?: new Date() - 10.years
            }
        if (!endDate)
            endDate = new Date()

        if (!adjustmentType)
            adjustmentType = AdjustmentHelper.defaultType
        def propertyList = [
                'firstTradePrice',
                'lastTradePrice',
                'closingPrice',
                'minPrice',
                'maxPrice',
                'totalTradeCount',
                'totalTradeValue',
                'totalTradeVolume',
                'yesterdayPrice',
                'priceChange'
        ]
        def series
        if (groupingMode == '')
            series = timeSeriesDBService.query("SELECT value FROM ${propertyList.collect { pr -> "dailyTrade_${adjustmentType}_${symbolId}_${pr}" }.join(', ')} WHERE time >= ${startDate.time * 1000}u and time <= ${endDate.time * 1000}u")[0]?.series
        else
            series = timeSeriesDBService.query("SELECT LAST(value) FROM ${propertyList.collect { pr -> "dailyTrade_${adjustmentType}_${symbolId}_${pr}" }.join(', ')} WHERE time >= ${startDate.time * 1000}u and time <= ${endDate.time * 1000}u GROUP BY time(${groupingMode})")[0]?.series
        def list = []
        def closingPriceSerie = series.find { it.name.endsWith('closingPrice') }
        if (!closingPriceSerie)
            return []
        for (def i = 0; i < closingPriceSerie.values.size(); i++) {
            def item = [:]
            item.symbolId = symbolId
            item.date = Date.parse("yyyy-MM-dd'T'hh:mm:ss'Z'", closingPriceSerie.values[i][0])
            series.each { serie ->
                item."${serie.name.split('_').last()}" = serie.values.find {
                    it[0] == closingPriceSerie.values[i][0]
                }[1] as Double
            }
            list << item
        }
        list = list.findAll { it.closingPrice }
        list.sort { it.date }

    }

    def priceList(Long symbolId, String property, Date startDate = null, Date endDate = null, String groupingMode = '1d', String adjustmentType = null) {
        if (!startDate)
            use(TimeCategory) {
                startDate = new Date() - 20.years
            }
        if (!endDate)
            endDate = new Date()
        if (!adjustmentType)
            adjustmentType = AdjustmentHelper.defaultType
        def values
        if (groupingMode == '')
            values = timeSeriesDBService.query("SELECT value FROM dailyTrade_${adjustmentType}_${symbolId}_${property} WHERE time >= ${startDate.time * 1000}u and time <= ${endDate.time * 1000}u")[0]?.series?.values
        else
            values = timeSeriesDBService.query("SELECT LAST(value) FROM dailyTrade_${adjustmentType}_${symbolId}_${property} WHERE time >= ${startDate.time * 1000}u and time <= ${endDate.time * 1000}u GROUP BY time(${groupingMode})")[0]?.series?.values
        values ? values[0].findAll { it[1] }.collect {
            [date: Date.parse("yyyy-MM-dd'T'hh:mm:ss'Z'", it[0]), value: it[1] as Double]
        }.findAll { it.value } : []
    }

    def lastDailyTrade(Long symbolId, Date endDate = null, String adjustmentType = null) {
        if (!endDate)
            endDate = new Date()

        if (!adjustmentType)
            adjustmentType = AdjustmentHelper.defaultType
        def propertyList = [
                'firstTradePrice',
                'lastTradePrice',
                'closingPrice',
                'minPrice',
                'maxPrice',
                'totalTradeCount',
                'totalTradeValue',
                'totalTradeVolume',
                'yesterdayPrice',
                'priceChange'
        ]
        def series = timeSeriesDBService.query("SELECT LAST(value) FROM ${propertyList.collect { pr -> "dailyTrade_${adjustmentType}_${symbolId}_${pr}" }.join(', ')} WHERE time <= ${endDate.time * 1000}u")[0]?.series
        def closingPriceSerie = series.find { it.name.endsWith('closingPrice') }
        if (!closingPriceSerie)
            return null
        def item = [:]
        item.symbolId = symbolId
        item.date = endDate
        series.each { serie ->
            item."${serie.name.split('_').last()}" = serie.values.find {
                it[0] == closingPriceSerie.values[0][0]
            }[1] as Double
        }
        return item
    }

    Double lastPrice(Long symbolId, String property, Date endDate = null, String adjustmentType = null) {
        if (!endDate)
            endDate = new Date()
        if (!adjustmentType)
            adjustmentType = AdjustmentHelper.defaultType
        def values = timeSeriesDBService.query("SELECT LAST(value) FROM dailyTrade_${adjustmentType}_${symbolId}_${property} WHERE time <= ${endDate.time * 1000}u")[0]?.series?.values
        values ? values[0].find()[1] as Double : null
    }

    Date firstTradeDate(Long symbolId) {
        SymbolDailyTrade.createCriteria().list {
            symbol {
                eq('id', symbolId)
            }
            projections {
                property('date')
            }
            order('date', ORDER_ASCENDING)
            maxResults(1)
        }[0]
    }

}
