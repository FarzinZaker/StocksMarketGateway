package stocks.tse.event

import stocks.tse.Index
import stocks.tse.IndexSymbol
import stocks.tse.Symbol

class IndexSymbolEvent {

    Index index
    Long indexCode
    Symbol symbol
    Long symbolInternalCode
    String symbolPersianName
    String symbolPersianCode
    Date date
    Double symbolYesterdayFundPercent
    Long stocksCount
    Double symbolFundAdjustmentPercent


    Date creationDate
    IndexSymbol data

    static mapping = {
        table 'tse_index_symbol_ev'
        date column: 'dat'
    }

    static constraints = {
        index nullable: false, xmlNodeName: 'IdxCode', fkColumn: 'InternalCode'
        indexCode nullable: true, xmlNodeName: 'IdxCode'
        symbol nullable: true, xmlNodeName: 'InsCode', fkColumn: 'InternalCode'
        symbolInternalCode nullable: true, xmlNodeName: 'InsCode'
        symbolPersianName nullable: true, xmlNodeName: 'LVal30'
        symbolPersianCode nullable: true, xmlNodeName: 'LVal18AFC'
        date nullable: true, xmlNodeName: 'DEven', locale: 'en'
        symbolYesterdayFundPercent nullable: true, xmlNodeName: 'XValCapRfV'
        stocksCount nullable: true, xmlNodeName: 'NumberOFShare'
        symbolFundAdjustmentPercent nullable: true, xmlNodeName: 'KAjCapValCpsIdx'

        data nullable: true
    }
}
