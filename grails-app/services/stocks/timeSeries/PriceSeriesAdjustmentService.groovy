package stocks.timeSeries

import groovy.time.TimeCategory
import stocks.tse.AdjustmentHelper
import stocks.tse.Symbol
import stocks.tse.SymbolAdjustedDailyTrade

class PriceSeriesAdjustmentService {

    def symbolIndicatorBulkService
    def adjustedPriceSeriesService

    def apply(String type, List args) {
        switch (type) {
            case AdjustmentHelper.TYPE_CAPITAL_INCREASE_PLUS_BROUGHT:
                applyCapitalIncreasePlusBrought(args[0] as Long)
        }
    }

    def applyCapitalIncreasePlusBrought(Long symbolId) {

//        def closingPriceList = adjustedPriceSeriesService.closingPriceList(symbolId, AdjustmentHelper.TYPE_CAPITAL_INCREASE_PLUS_BROUGHT)
//        def firstTradePriceList = adjustedPriceSeriesService.firstTradePriceList(symbolId, AdjustmentHelper.TYPE_CAPITAL_INCREASE_PLUS_BROUGHT)
//        def lastTradePriceList = adjustedPriceSeriesService.lastTradePriceList(symbolId, AdjustmentHelper.TYPE_CAPITAL_INCREASE_PLUS_BROUGHT)
//        def maxPriceList = adjustedPriceSeriesService.maxPriceList(symbolId, AdjustmentHelper.TYPE_CAPITAL_INCREASE_PLUS_BROUGHT)
//        def minPriceList = adjustedPriceSeriesService.minPriceList(symbolId, AdjustmentHelper.TYPE_CAPITAL_INCREASE_PLUS_BROUGHT)
//        def yesterdayPriceList = adjustedPriceSeriesService.yesterdayPriceList(symbolId, AdjustmentHelper.TYPE_CAPITAL_INCREASE_PLUS_BROUGHT)

        def dailyTrades = adjustedPriceSeriesService.dailyTradeList(symbolId, AdjustmentHelper.TYPE_CAPITAL_INCREASE_PLUS_BROUGHT)

        if (dailyTrades && dailyTrades.size()) {

            def finalList = []
            for (def i = 1; i < dailyTrades.size(); i++) {
                def adjustmentRate = dailyTrades[i - 1].yesterdayPrice / dailyTrades[i].closingPrice
                if (adjustmentRate != 1) {
                    dailyTrades[i].closingPrice = dailyTrades[i - 1].yesterdayPrice
                    dailyTrades[i].firstTradePrice = Math.round((dailyTrades[i].firstTradePrice * adjustmentRate) as Double)
                    dailyTrades[i].lastTradePrice = Math.round((dailyTrades[i].lastTradePrice * adjustmentRate) as Double)
                    dailyTrades[i].maxPrice = Math.round((dailyTrades[i].maxPrice * adjustmentRate) as Double)
                    dailyTrades[i].minPrice = Math.round((dailyTrades[i].minPrice * adjustmentRate) as Double)
                    dailyTrades[i].yesterdayPrice = Math.round((dailyTrades[i].yesterdayPrice * adjustmentRate) as Double)

                    finalList << dailyTrades[i]
                }
            }

            if (finalList?.size())
                adjustedPriceSeriesService.write(finalList)

//            symbolIndicatorBulkService.recalculateIndicators(symbol)
        }
    }
}
