package stocks.timeSeries

import stocks.tse.AdjustmentHelper
import stocks.tse.SymbolDailyTrade

class AdjustedPriceSeriesService {

    def timeSeriesDBService

    def write(List dailyTrades) {

        def serie = new Serie()

        AdjustmentHelper.TYPES.each { adjustmentType ->

            dailyTrades.each { dailyTrade ->
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

//                serie.addPoint(new Point("dailyTrade.${adjustmentType}.adjustable").tags([symbolId: dailyTrade.symbolId?.toString()]).time(dailyTrade.date).value(dailyTrade.symbolPersianCode.startsWith("تسه") ? 0 : 1))
            }
        }

        timeSeriesDBService.write(serie)
    }

    def firstTradePriceList(Long symbolId, String adjustmentType) {
        priceList(symbolId, adjustmentType, 'firstTradePrice')
    }

    def closingPriceList(Long symbolId, String adjustmentType) {
        priceList(symbolId, adjustmentType, 'closingPrice')
    }

    def minPriceList(Long symbolId, String adjustmentType) {
        priceList(symbolId, adjustmentType, 'minPrice')
    }

    def maxPriceList(Long symbolId, String adjustmentType) {
        priceList(symbolId, adjustmentType, 'maxPrice')
    }

    def totalTradeCountList(Long symbolId, String adjustmentType) {
        priceList(symbolId, adjustmentType, 'totalTradeCount')
    }

    def totalTradeValueList(Long symbolId, String adjustmentType) {
        priceList(symbolId, adjustmentType, 'totalTradeValue')
    }

    def totalTradeVolumeList(Long symbolId, String adjustmentType) {
        priceList(symbolId, adjustmentType, 'totalTradeVolume')
    }

    def yesterdayPriceList(Long symbolId, String adjustmentType) {
        priceList(symbolId, adjustmentType, 'yesterdayPrice')
    }

    def priceChangeList(Long symbolId, String adjustmentType) {
        priceList(symbolId, adjustmentType, 'priceChange')
    }

    def lastTradePriceList(Long symbolId, String adjustmentType) {
        priceList(symbolId, adjustmentType, 'lastTradePrice')
    }

    def dailyTradeList(Long symbolId, String adjustmentType) {
        def series = timeSeriesDBService.query("""SELECT * FROM ${
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
            ].collect { pr -> "dailyTrade_${adjustmentType}_${symbolId}_${pr}" }.join(', ')
        } """)[0]?.series
        def list = []
        for (def i = 0; i < series.collect { it.values.size() }.min(); i++) {
            def item = [:]
            item.symbolId = symbolId
            item.date = Date.parse("yyyy-MM-dd'T'hh:mm:ss'Z'", series[0].values[i][0])
            series.each { serie ->
                item."${serie.name.split('_').last()}" = serie.values[i][1] as Double
            }
            list << item
        }
        list.sort { it.date }

    }

    def priceList(Long symbolId, String adjustmentType, String property) {
        def values = timeSeriesDBService.query("SELECT * FROM \"dailyTrade_${adjustmentType}_${symbolId}_${property}\"")[0]?.series?.values
        values ? values[0].collect {
            [date: Date.parse("yyyy-MM-dd'T'hh:mm:ss'Z'", it[0]), value: it[1] as Double]
        } : []
    }
}
