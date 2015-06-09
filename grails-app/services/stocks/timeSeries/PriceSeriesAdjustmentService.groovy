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

        def closingPriceList = adjustedPriceSeriesService.closingPriceList(symbolId, AdjustmentHelper.TYPE_CAPITAL_INCREASE_PLUS_BROUGHT)
        def firstTradePriceList = adjustedPriceSeriesService.firstTradePriceList(symbolId, AdjustmentHelper.TYPE_CAPITAL_INCREASE_PLUS_BROUGHT)
        def lastTradePriceList = adjustedPriceSeriesService.lastTradePriceList(symbolId, AdjustmentHelper.TYPE_CAPITAL_INCREASE_PLUS_BROUGHT)
        def maxPriceList = adjustedPriceSeriesService.maxPriceList(symbolId, AdjustmentHelper.TYPE_CAPITAL_INCREASE_PLUS_BROUGHT)
        def minPriceList = adjustedPriceSeriesService.minPriceList(symbolId, AdjustmentHelper.TYPE_CAPITAL_INCREASE_PLUS_BROUGHT)
        def yesterdayPriceList = adjustedPriceSeriesService.yesterdayPriceList(symbolId, AdjustmentHelper.TYPE_CAPITAL_INCREASE_PLUS_BROUGHT)


        if (closingPriceList && closingPriceList.size()) {

            def finalList = []
            for (def i = 1; i < closingPriceList.size(); i++) {
                def newDate = closingPriceList[i].date
                use(TimeCategory){
                    newDate = newDate + 10.years
                }
                def adjustmentRate = yesterdayPriceList[i - 1].value / closingPriceList[i].value
                if (adjustmentRate != 1) {
                    closingPriceList[i].value = yesterdayPriceList[i - 1].value
                    firstTradePriceList[i].value = Math.round((firstTradePriceList[i].value * adjustmentRate) as Double)
                    if (lastTradePriceList?.size())
                        lastTradePriceList[i].value = Math.round((lastTradePriceList[i].value * adjustmentRate) as Double)
                    maxPriceList[i].value = Math.round((maxPriceList[i].value * adjustmentRate) as Double)
                    minPriceList[i].value = Math.round((minPriceList[i].value * adjustmentRate) as Double)
                    yesterdayPriceList[i].value = Math.round((yesterdayPriceList[i].value * adjustmentRate) as Double)

                    finalList << [
                            symbolId       : symbolId?.toString(),
                            date           : newDate,
                            closingPrice   : closingPriceList[i].value,
                            firstTradePrice: firstTradePriceList[i].value,
                            lastTradePrice : lastTradePriceList?.size() ? lastTradePriceList[i].value : 0,
                            maxPrice       : maxPriceList[i].value,
                            minPrice       : minPriceList[i].value,
                            yesterdayPrice : yesterdayPriceList[i].value
                    ]
                }
            }

            if (finalList?.size())
                adjustedPriceSeriesService.write(finalList?.size() > 500 ? finalList[0..500] :finalList)

//            symbolIndicatorBulkService.recalculateIndicators(symbol)
        }
    }
}
