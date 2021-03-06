package stocks.timeSeries

import groovy.time.TimeCategory
import stocks.tse.AdjustmentHelper
import stocks.tse.Symbol
import stocks.tse.SymbolAdjustedDailyTrade
import stocks.tse.SymbolAdjustmentQueue

class PriceSeriesAdjustmentService {

    def symbolIndicatorBulkService
    def adjustedPriceSeries9Service

    def apply(String type, List args) {

        switch (type) {
            case AdjustmentHelper.TYPE_CAPITAL_INCREASE_PLUS_BROUGHT:
                applyCapitalIncreasePlusBrought(args[0] as Long)
        }
    }

    def applyCapitalIncreasePlusBrought(Long symbolId) {

        def dailyTrades = adjustedPriceSeries9Service.dailyTradeList(symbolId, null, null, '', AdjustmentHelper.TYPE_CAPITAL_INCREASE_PLUS_BROUGHT).sort {
            -it.date.time
        }

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

            if (finalList?.size()) {
                adjustedPriceSeries9Service.write(finalList, [AdjustmentHelper.TYPE_CAPITAL_INCREASE_PLUS_BROUGHT])

//                symbolIndicatorBulkService.recalculateIndicators(Symbol.get(symbolId))
            }
        }
    }


    def undo(String type, List args) {
        switch (type) {
            case AdjustmentHelper.TYPE_CAPITAL_INCREASE_PLUS_BROUGHT:
                undoCapitalIncreasePlusBrought(args[0] as Long)
        }
    }

    def undoCapitalIncreasePlusBrought(Long symbolId) {

        def symbol = Symbol.get(symbolId)
        SymbolAdjustedDailyTrade.findAllBySymbolAndAdjustmentType(symbol, AdjustmentHelper.TYPE_CAPITAL_INCREASE_PLUS_BROUGHT).each { adjustedDailyTrade ->
            def dailyTrade = adjustedDailyTrade.dailyTrade
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
                adjustedDailyTrade."${property}" = dailyTrade."${property}"
            }
            adjustedDailyTrade.save(flush: true)
        }

        def dailyTrades = adjustedPriceSeries9Service.dailyTradeList(symbolId, null, null, '', AdjustmentHelper.TYPE_NONE)
        adjustedPriceSeries9Service.write(dailyTrades, [AdjustmentHelper.TYPE_CAPITAL_INCREASE_PLUS_BROUGHT])

        def queueItem = SymbolAdjustmentQueue.findBySymbol(symbol)?:new SymbolAdjustmentQueue(symbol: symbol, adjustmentType: AdjustmentHelper.TYPE_CAPITAL_INCREASE_PLUS_BROUGHT)
        queueItem.applied = false
        queueItem.save(flush: true)

//        symbolIndicatorBulkService.recalculateIndicators(symbol)
    }

}
