package stocks.tse.event

import stocks.tse.Symbol
import stocks.tse.SymbolBestOrder

class SymbolBestOrderEvent {

    //    Integer marketIdentifier
    Integer number
    Symbol symbol
    Long symbolInternalCode
    Integer orderVolume
    Integer orderCount
    Integer orderValue
    Integer offerVolume
    Integer offerCount
    Integer offerValue


    Date creationDate
    SymbolBestOrder data

    static mapping = {
        table 'tse_symbol_best_order_ev'
        number column: 'num'
    }

    static constraints = {
//        marketIdentifier nullable: true, parameterIndex: 0
        number nullable: true, xmlNodeName: 'number'
        symbol nullable: true, xmlNodeName: 'InsCode', fkColumn: 'InternalCode'
        symbolInternalCode nullable: true, xmlNodeName: 'InsCode'
        orderVolume nullable: true, xmlNodeName: 'QTitMeDem'
        orderCount nullable: true, xmlNodeName: 'ZOrdMeDem'
        orderValue nullable: true, xmlNodeName: 'PMeDem'
        offerVolume nullable: true, xmlNodeName: 'ZOrdMeOf'
        offerCount nullable: true, xmlNodeName: 'ZOrdMeOf'
        offerValue nullable: true, xmlNodeName: 'PMeOf'
        data nullable:true
    }
}
