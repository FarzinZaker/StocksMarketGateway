package stocks.tse

class PriceAdjustmentService {

    def symbolIndicatorBulkService

    def apply(String type, List args){
        switch (type){
            case AdjustmentHelper.TYPE_CAPITAL_INCREASE_PLUS_BROUGHT:
                applyCapitalIncreasePlusBrought(args[0] as Long)
        }
    }

    def applyCapitalIncreasePlusBrought(Long symbolId) {

        def symbol = Symbol.get(symbolId)

        def priceList = SymbolAdjustedDailyTrade.createCriteria().list {
            eq('symbol', symbol)
            eq('adjustmentType', AdjustmentHelper.TYPE_CAPITAL_INCREASE_PLUS_BROUGHT)
            order('date', ORDER_DESCENDING)
        }

        if (priceList && priceList.size()) {

            for (def i = 1; i < priceList.size(); i++) {
                def list = priceList.findAll { it.date.clearTime() == priceList[i].date.clearTime() }
                for (def j = 0; j < list.size(); j++)
                    adjustDailyTrade(list[j], priceList[i - 1].yesterdayPrice)
                i += list.size() - 1
            }

            symbolIndicatorBulkService.recalculateIndicators(symbol)
        }
    }

    def adjustDailyTrade(SymbolAdjustedDailyTrade dailyTrade, Double realClosingPrice) {
        def adjustmentRate = realClosingPrice / dailyTrade.closingPrice
        if (adjustmentRate == 1)
            return
        dailyTrade.closingPrice = realClosingPrice
        dailyTrade.firstTradePrice = dailyTrade.firstTradePrice * adjustmentRate
        dailyTrade.lastTradePrice = Math.round(dailyTrade.lastTradePrice * adjustmentRate)
        dailyTrade.maxPrice = Math.round(dailyTrade.maxPrice * adjustmentRate)
        dailyTrade.minPrice = Math.round(dailyTrade.minPrice * adjustmentRate)
        dailyTrade.yesterdayPrice = Math.round(dailyTrade.yesterdayPrice * adjustmentRate)
        dailyTrade.save(flush: true)
    }
}
