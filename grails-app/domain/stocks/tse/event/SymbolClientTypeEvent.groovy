package stocks.tse.event

import stocks.tse.Symbol
import stocks.tse.SymbolClientType

class SymbolClientTypeEvent {

    Symbol symbol
    Long symbolInternalCode
    Integer individualBuyCount
    Integer legalBuyCount
    Double individualBuyVolume
    Double legalBuyVolume
    Integer individualSellCount
    Integer legalSellCount
    Double individualSellVolume
    Double legalSellVolume
    Date date

    Date creationDate
    SymbolClientType data

    static mapping = {
        table 'tse_symbol_client_type_ev'
        date column: 'dat'
    }

    static constraints = {
        symbol nullable: true, xmlNodeName: 'InsCode', fkColumn: 'InternalCode'
        symbolInternalCode nullable: true, xmlNodeName: 'InsCode'
        individualBuyCount nullable: true, xmlNodeName: 'Buy_CountI'
        legalBuyCount nullable: true, xmlNodeName: 'Buy_CountN'
        individualBuyVolume nullable: true, xmlNodeName: 'Buy_I_Volume'
        legalBuyVolume nullable: true, xmlNodeName: 'Buy_N_Volume'
        individualSellCount nullable: true, xmlNodeName: 'Sell_CountI'
        legalSellCount nullable: true, xmlNodeName: 'Sell_CountN'
        individualSellVolume nullable: true, xmlNodeName: 'Sell_I_Volume'
        legalSellVolume nullable: true, xmlNodeName: 'Sell_N_Volume'
        data nullable: true
    }
}
