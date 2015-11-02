package stocks.tse.event

import org.apache.axis.types.UnsignedByte
import stocks.tse.Symbol
import stocks.tse.SymbolAverageTrade

class SymbolAverageTradeEvent {

    Symbol symbol
    Integer daysCount

    Double tradeCount
    Double tradeVolume
    Double tradeValue

    Date creationDate

    SymbolAverageTrade data

    static mapping = {
        table 'tse_symbol_avg_trade_ev'
    }

    static constraints = {
        daysCount inList: [5, 10, 30, 90, 180, 365]
    }
}
