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
                    serie.addPoint(new Point("dailyTrade.${adjustmentType}.${dailyTrade.symbolId}.${property}")
//                            .tags([symbolId: dailyTrade.symbolId?.toString()])
                            .time(dailyTrade.date)
                            .value(dailyTrade."${property}"))
                }

//                serie.addPoint(new Point("dailyTrade.${adjustmentType}.adjustable").tags([symbolId: dailyTrade.symbolId?.toString()]).time(dailyTrade.date).value(dailyTrade.symbolPersianCode.startsWith("تسه") ? 0 : 1))
            }
        }

        timeSeriesDBService.write(serie)
    }

    def firstTradePriceList(Long symbolId, String adjustmentType){
        priceList(symbolId, adjustmentType, 'firstTradePrice')
    }

    def closingPriceList(Long symbolId, String adjustmentType){
        priceList(symbolId, adjustmentType, 'closingPrice')
    }

    def minPriceList(Long symbolId, String adjustmentType){
        priceList(symbolId, adjustmentType, 'minPrice')
    }

    def maxPriceList(Long symbolId, String adjustmentType){
        priceList(symbolId, adjustmentType, 'maxPrice')
    }

    def totalTradeCountList(Long symbolId, String adjustmentType){
        priceList(symbolId, adjustmentType, 'totalTradeCount')
    }

    def totalTradeValueList(Long symbolId, String adjustmentType){
        priceList(symbolId, adjustmentType, 'totalTradeValue')
    }

    def totalTradeVolumeList(Long symbolId, String adjustmentType){
        priceList(symbolId, adjustmentType, 'totalTradeVolume')
    }

    def yesterdayPriceList(Long symbolId, String adjustmentType){
        priceList(symbolId, adjustmentType, 'yesterdayPrice')
    }

    def priceChangeList(Long symbolId, String adjustmentType){
        priceList(symbolId, adjustmentType, 'priceChange')
    }

    def lastTradePriceList(Long symbolId, String adjustmentType){
        priceList(symbolId, adjustmentType, 'lastTradePrice')
    }

    def priceList(Long symbolId, String adjustmentType, String property){
        def values = timeSeriesDBService.query("SELECT * FROM \"dailyTrade.${adjustmentType}.${symbolId}.${property}\"")[0]?.series?.values
        values? values[0].collect{[date:Date.parse("yyyy-MM-dd'T'hh:mm:ss'Z'",it[0]), value:it[1] as Double]} : []
    }
}
