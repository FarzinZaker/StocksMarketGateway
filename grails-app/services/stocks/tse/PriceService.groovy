package stocks.tse

class PriceService {

    Double lastPrice(Symbol symbol, Date date = new Date(), String adjustmentType = null) {
        if(!adjustmentType)
            adjustmentType = AdjustmentHelper.globalAdjustmentType
        def dailyTrade = lastDailyTrade(symbol, date, adjustmentType)
        dailyTrade ? (dailyTrade?.closingPrice ?: 0) : 0
    }

    SymbolAdjustedDailyTrade lastDailyTrade(Symbol symbol, Date date = new Date(), String adjustmentType = null) {
        if(!adjustmentType)
            adjustmentType = AdjustmentHelper.globalAdjustmentType
        def parameters = [max: 1, sort: "date", order: "desc"]
        SymbolAdjustedDailyTrade.findAllBySymbolAndAdjustmentTypeAndDateLessThanEquals(symbol, adjustmentType, date, parameters)?.find()
    }

    SymbolAdjustedDailyTrade dailyTrade(Symbol symbol, Date date = new Date(), String adjustmentType = null) {
        if(!adjustmentType)
            adjustmentType = AdjustmentHelper.globalAdjustmentType
        def parameters = [max: 1, sort: "date", order: "desc"]
        SymbolAdjustedDailyTrade.findAllBySymbolAndAdjustmentTypeAndDate(symbol, adjustmentType, date, parameters)?.find()
    }
}
