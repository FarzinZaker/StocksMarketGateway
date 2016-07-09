package stocks.tse

class PriceService {

    def adjustedPriceSeries9Service

    Double lastPrice(Symbol symbol, Date date = new Date(), String adjustmentType = null) {
        if (!adjustmentType)
            adjustmentType = AdjustmentHelper.defaultType
        def dailyTrade = lastDailyTrade(symbol, date, adjustmentType)
        dailyTrade?.lastTradePrice ?: adjustedPriceSeries9Service.lastLastTradePrice(symbol?.id, date, adjustmentType)
    }

    SymbolAdjustedDailyTrade lastDailyTrade(Symbol symbol, Date date = new Date(), String adjustmentType = null) {
        if (!adjustmentType)
            adjustmentType = AdjustmentHelper.defaultType
        def parameters = [max: 1, sort: "date", order: "desc"]
        SymbolAdjustedDailyTrade.findAllBySymbolAndAdjustmentTypeAndDateLessThanEquals(symbol, adjustmentType, date, parameters)?.find()
    }

    SymbolAdjustedDailyTrade dailyTrade(Symbol symbol, Date date = new Date(), String adjustmentType = null) {
        if (!adjustmentType)
            adjustmentType = AdjustmentHelper.defaultType
        def parameters = [max: 1, sort: "date", order: "desc"]
        SymbolAdjustedDailyTrade.findAllBySymbolAndAdjustmentTypeAndDate(symbol, adjustmentType, date, parameters)?.find()
    }
}

