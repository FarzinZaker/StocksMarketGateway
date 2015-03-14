package stocks.tse

class PriceService {

    Double lastPrice(Symbol symbol, Date date = new Date()) {
        def dailyTrade = lastDailyTrade(symbol, date)
        dailyTrade ? (dailyTrade?.closingPrice ?: 0) : 0
    }

    SymbolDailyTrade lastDailyTrade(Symbol symbol, Date date = new Date()) {
        def parameters = [max: 1, sort: "creationDate", order: "desc"]
        SymbolDailyTrade.findAllBySymbolAndDateLessThanEquals(symbol, date, parameters)?.find()
    }

    SymbolDailyTrade dailyTrade(Symbol symbol, Date date = new Date()) {
        def parameters = [max: 1, sort: "creationDate", order: "desc"]
        SymbolDailyTrade.findAllBySymbolAndDailySnapshot(symbol, date, parameters)?.find()
    }
}
