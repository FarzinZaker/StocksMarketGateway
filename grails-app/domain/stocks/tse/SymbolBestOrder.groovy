package stocks.tse

class SymbolBestOrder {

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
    Date modificationDate

    static mapping = {
        table 'tse_symbol_best_order'
    }

    static constraints = {
//        marketIdentifier nullable: true, parameterIndex: 0
        number nullable: true
        symbol nullable: true
        symbolInternalCode nullable: true
        orderVolume nullable: true
        orderCount nullable: true
        orderValue nullable: true
        offerVolume nullable: true
        offerCount nullable: true
        offerValue nullable: true
    }
}
