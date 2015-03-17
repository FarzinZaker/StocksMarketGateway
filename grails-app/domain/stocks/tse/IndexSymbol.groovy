package stocks.tse

class IndexSymbol {

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
    Date modificationDate

    static mapping = {
        table 'tse_index_symbol'
        date column: 'dat'
    }

    static constraints = {
        index nullable: false
        indexCode nullable: true
        symbol nullable: true
        symbolInternalCode nullable: true
        symbolPersianName nullable: true
        symbolPersianCode nullable: true
        date nullable: true
        symbolYesterdayFundPercent nullable: true
        stocksCount nullable: true
        symbolFundAdjustmentPercent nullable: true
    }
}
