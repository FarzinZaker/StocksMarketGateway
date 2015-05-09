package stocks.tse

class PriceAdjustmentService {

    def symbolIndicatorService

    def apply(Long symbolId) {

        def priceList = SymbolDailyTrade.createCriteria().list {
            symbol {
                idEq(symbolId)
            }
            isNotNull('dailySnapshot')
            order('dailySnapshot', ORDER_DESCENDING)
        }

        if (priceList && priceList.size()) {

            for (def i = 1; i < priceList.size(); i++) {
                def list = priceList.findAll { it.dailySnapshot == priceList[i].dailySnapshot }
                for (def j = 0; j < list.size(); j++)
                    adjustDailyTrade(list[j], priceList[i - 1].yesterdayPrice)
                i += list.size() - 1
            }

            symbolIndicatorService.recalculateIndicators(priceAdjustment.symbol)
        }
    }

    def adjustDailyTrade(SymbolDailyTrade dailyTrade, Double realClosingPrice) {
        def adjustmentRate = realClosingPrice / dailyTrade.closingPrice
        if (adjustmentRate == 1)
            return
        dailyTrade.oldClosingPrice = dailyTrade.closingPrice
        dailyTrade.closingPrice = realClosingPrice
        dailyTrade.firstTradePrice = dailyTrade.firstTradePrice * adjustmentRate
        dailyTrade.lastTradePrice = dailyTrade.lastTradePrice * adjustmentRate
        dailyTrade.maxPrice = dailyTrade.maxPrice * adjustmentRate
        dailyTrade.minPrice = dailyTrade.minPrice * adjustmentRate
        dailyTrade.yesterdayPrice = dailyTrade.yesterdayPrice * adjustmentRate
        dailyTrade.save(flush: true)
    }

    def undo(SymbolPriceAdjustment priceAdjustment) {

        def priceList = SymbolDailyTrade.createCriteria().list {
            eq('symbolInternalCode', priceAdjustment.symbolInternalCode)
            lte('dailySnapshot', priceAdjustment.date)
            order('dailySnapshot', ORDER_DESCENDING)
        }

        if (priceList && priceList.size()) {
            def adjustmentRate = priceAdjustment.adjustedPrice / priceAdjustment.oldPrice

            for (def i = 0; i < priceList.size(); i++)
                undoDailyTrade(priceList[i], adjustmentRate)

            symbolIndicatorService.recalculateIndicators(priceAdjustment.symbol)
        }

        priceAdjustment.applied = false
        priceAdjustment.save(flush: true)
    }

    def undoDailyTrade(SymbolDailyTrade dailyTrade, Double adjustmentRate) {
        dailyTrade.oldClosingPrice = null
        dailyTrade.closingPrice = Math.round(dailyTrade.closingPrice / adjustmentRate)
        dailyTrade.firstTradePrice = Math.round(dailyTrade.firstTradePrice / adjustmentRate)
        dailyTrade.lastTradePrice = Math.round(dailyTrade.lastTradePrice / adjustmentRate)
        dailyTrade.maxPrice = Math.round(dailyTrade.maxPrice / adjustmentRate)
        dailyTrade.minPrice = Math.round(dailyTrade.minPrice / adjustmentRate)
        dailyTrade.yesterdayPrice = Math.round(dailyTrade.yesterdayPrice / adjustmentRate)
        dailyTrade.save(flush: true)
    }
}
