package stocks.tse

class SymbolPriceAdjustment {

    Symbol symbol
    Long symbolInternalCode
    Date date
    Double oldPrice
    Double adjustedPrice
    Boolean applied = false

    Date creationDate
    Date modificationDate

    static mapping = {
        table 'tse_symbol_price_adjustment'
        date column: 'dat'
    }

    static constraints = {
        symbol nullable: true
        symbolInternalCode nullable: true
        date nullable: true
        oldPrice nullable: true
        adjustedPrice nullable: true
    }
}
