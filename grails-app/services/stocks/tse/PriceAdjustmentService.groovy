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
        dailyTrade.closingPrice *= adjustmentRate
        dailyTrade.firstTradePrice *= adjustmentRate
        dailyTrade.lastTradePrice *= adjustmentRate
        dailyTrade.maxPrice *= adjustmentRate
        dailyTrade.minPrice *= adjustmentRate
        dailyTrade.yesterdayPrice *= adjustmentRate
        dailyTrade.save(flush: true)
    }
}
