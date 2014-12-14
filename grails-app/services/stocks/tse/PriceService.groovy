package stocks.tse

class PriceService {

    def lastPrice(Symbol symbol) {
        def parameters = [max: 1, sort: "creationDate", order: "desc"]
        def dailyTrade = SymbolDailyTrade.findAllBySymbol(symbol, parameters)

        dailyTrade? (dailyTrade[0]?.closingPrice?:0):0
    }
}
