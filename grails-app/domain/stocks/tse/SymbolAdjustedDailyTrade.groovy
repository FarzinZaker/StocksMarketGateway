package stocks.tse

class SymbolAdjustedDailyTrade {

    Symbol symbol
    SymbolDailyTrade dailyTrade
    Date date
    Integer totalTradeCount
    Integer totalTradeVolume
    Double totalTradeValue
    Double closingPrice
    Double firstTradePrice
    Double lastTradePrice
    Double priceChange
    Double minPrice
    Double maxPrice
    Double yesterdayPrice

    String adjustmentType

    Date dailySnapshot
    Date weeklySnapshot
    Date monthlySnapshot

    Date creationDate
    Date modificationDate

    static snapshotGroupProperty = ['symbol', 'adjustmentType']
    static snapshotDateProperty = 'date'

    static mapping = {
        table 'tse_symbol_adj_daily_trade'
        sort id: "desc"
        symbol index: 'idx_sajdt_symbol,idx_sadt_symbol_type_date'
        date column: 'dat', index: 'idx_sadt_symbol_type_date'
        adjustmentType index: 'idx_sadt_symbol_type_date'
    }

    static constraints = {
        symbol nullable: true
        date nullable: true
        totalTradeCount nullable: true
        totalTradeVolume nullable: true
        totalTradeValue nullable: true
        closingPrice nullable: true
        firstTradePrice nullable: true
        lastTradePrice nullable: true
        priceChange nullable: true
        minPrice nullable: true
        maxPrice nullable: true
        yesterdayPrice nullable: true

        adjustmentType inList: AdjustmentHelper.TYPES

        dailySnapshot nullable: true
        weeklySnapshot nullable: true
        monthlySnapshot nullable: true
    }
}
