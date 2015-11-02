package stocks.tse

class SymbolAverageTrade {

    Symbol symbol
    Integer daysCount

    Double tradeCount
    Double tradeVolume
    Double tradeValue

    Date creationDate
    Date modificationDate

    static mapping = {
        table 'tse_symbol_avg_trade'
    }

    static constraints = {
        daysCount inList: [5, 10, 30, 90, 180, 365]
    }
}
