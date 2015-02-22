package stocks.tse.event

import stocks.tse.Symbol
import stocks.tse.SymbolPriceAdjustment

class SymbolPriceAdjustmentEvent {

    Symbol symbol
    Long symbolInternalCode
    Date date
    Double oldPrice
    Double adjustedPrice

    Date creationDate
    SymbolPriceAdjustment data

    static mapping = {
        table 'tse_symbol_price_adjustment_event'
    }

    static constraints = {
        symbol nullable: true,  xmlNodeName: 'inscode', fkColumn: 'InternalCode'
        symbolInternalCode nullable: true, xmlNodeName: 'inscode'
        date nullable: true, xmlNodeName: 'DEven', locale: 'en'
        oldPrice nullable: true, xmlNodeName: 'PClosingNoAdj'
        adjustedPrice nullable: true, xmlNodeName: 'PClosing'
        data nullable: true
    }
}
