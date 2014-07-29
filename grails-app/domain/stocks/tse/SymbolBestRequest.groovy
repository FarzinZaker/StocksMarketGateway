package stocks.tse

class SymbolBestRequest {

//    Integer marketIdentifier
    Integer number
    Symbol symbol
    Long symbolInternalCode
    Integer requestVolume
    Integer requestCount
    Integer requestValue
    Integer offerVolume
    Integer offerCount
    Integer offerValue


    Date creationDate
    Date modificationDate

    static mapping = {
        table 'tse_symbol_best_request'
    }

    static constraints = {
//        marketIdentifier nullable: true, parameterIndex: 0
        number nullable: true, xmlNodeName: 'number'
        symbol nullable: true, xmlNodeName: 'InsCode', fkColumn: 'InternalCode'
        symbolInternalCode nullable: true, xmlNodeName: 'InsCode'
        requestVolume nullable: true, xmlNodeName: 'QTitMeDem'
        requestCount nullable: true, xmlNodeName: 'ZOrdMeDem'
        requestValue nullable: true, xmlNodeName: 'PMeDem'
        offerVolume nullable: true, xmlNodeName: 'ZOrdMeOf'
        offerCount nullable: true, xmlNodeName: 'ZOrdMeOf'
        offerValue nullable: true, xmlNodeName: 'PMeOf'
    }
}
