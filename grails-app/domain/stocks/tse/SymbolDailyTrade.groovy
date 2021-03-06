package stocks.tse

class SymbolDailyTrade {

//    Integer marketIdentifier
    Symbol symbol
    Long symbolInternalCode
    String symbolPersianCode
    String symbolPersianName
    Date date
    Integer totalTradeCount
    Integer totalTradeVolume
    Double totalTradeValue
    Double closingPrice
    Double oldClosingPrice
    Double firstTradePrice
    Double lastTradePrice
    Double priceChange
    Double minPrice
    Double maxPrice
    Double yesterdayPrice
    Boolean isLast

    Date creationDate
    Date modificationDate

    Boolean indicatorsCalculated = false

    static mapping = {
        table 'tse_symbol_daily_trade'
        sort id: "desc"
        symbol index: 'idx_sdt_symbol,idx_sdt_indicatorsCalculated'
        indicatorsCalculated index: 'idx_sdt_indicatorsCalculated'
        date column: 'dat'
    }

    static constraints = {
//        marketIdentifier nullable: true, parameterIndex: 0
        symbol nullable: true
        symbolInternalCode nullable: true
        symbolPersianCode nullable: true
        date nullable: true
        totalTradeCount nullable: true
        totalTradeVolume nullable: true
        totalTradeValue nullable: true
        symbolPersianName nullable: true
        closingPrice nullable: true
        oldClosingPrice nullable: true
        firstTradePrice nullable: true
        lastTradePrice nullable: true
        priceChange nullable: true
        minPrice nullable: true
        maxPrice nullable: true
        yesterdayPrice nullable: true
        isLast nullable: true

        indicatorsCalculated nullable: true
    }
}
