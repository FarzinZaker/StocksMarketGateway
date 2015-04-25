package stocks.tse

class PriceAdjustmentService {

    def symbolIndicatorService

    def apply(SymbolPriceAdjustment priceAdjustment) {

        def priceList = SymbolDailyTrade.createCriteria().list {
            eq('symbolInternalCode', priceAdjustment.symbolInternalCode)
            lte('dailySnapshot', priceAdjustment.date)
            order('dailySnapshot', ORDER_DESCENDING)
        }

        if (priceList && priceList.size()) {
            def adjustmentRate = priceAdjustment.adjustedPrice / priceAdjustment.oldPrice

            for (def i = 0; i < priceList.size(); i++)
                adjustDailyTrade(priceList[i], adjustmentRate)

            symbolIndicatorService.recalculateIndicators(priceAdjustment.symbol)
        }

        priceAdjustment.applied = true
        priceAdjustment.save(flush: true)
    }

    def adjustDailyTrade(SymbolDailyTrade dailyTrade, Double adjustmentRate) {
        dailyTrade.oldClosingPrice = dailyTrade.closingPrice
        dailyTrade.closingPrice = Math.round(dailyTrade.closingPrice * adjustmentRate)
        dailyTrade.firstTradePrice = Math.round(dailyTrade.firstTradePrice * adjustmentRate)
        dailyTrade.lastTradePrice = Math.round(dailyTrade.lastTradePrice * adjustmentRate)
        dailyTrade.maxPrice = Math.round(dailyTrade.maxPrice* adjustmentRate)
        dailyTrade.minPrice = Math.round(dailyTrade.minPrice * adjustmentRate)
        dailyTrade.yesterdayPrice = Math.round(dailyTrade.yesterdayPrice * adjustmentRate)
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

//            symbolIndicatorService.recalculateIndicators(priceAdjustment.symbol)
        }

        priceAdjustment.applied = false
        priceAdjustment.save(flush: true)
    }

    def undoDailyTrade(SymbolDailyTrade dailyTrade, Double adjustmentRate) {
        dailyTrade.oldClosingPrice = null
        dailyTrade.closingPrice = Math.round(dailyTrade.closingPrice / adjustmentRate)
        dailyTrade.firstTradePrice = Math.round(dailyTrade.firstTradePrice / adjustmentRate)
        dailyTrade.lastTradePrice = Math.round(dailyTrade.lastTradePrice / adjustmentRate)
        dailyTrade.maxPrice = Math.round(dailyTrade.maxPrice/ adjustmentRate)
        dailyTrade.minPrice = Math.round(dailyTrade.minPrice / adjustmentRate)
        dailyTrade.yesterdayPrice = Math.round(dailyTrade.yesterdayPrice / adjustmentRate)
        dailyTrade.save(flush: true)
    }
}
