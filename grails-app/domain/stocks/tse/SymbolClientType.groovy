package stocks.tse

class SymbolClientType {

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
    Date modificationDate

    static mapping = {
        table 'tse_symbol_client_type'
        date column: 'dat'
    }

    static constraints = {
        symbol nullable: true
        symbolInternalCode nullable: true
        individualBuyCount nullable: true
        legalBuyCount nullable: true
        individualBuyVolume nullable: true
        legalBuyVolume nullable: true
        individualSellCount nullable: true
        legalSellCount nullable: true
        individualSellVolume nullable: true
        legalSellVolume nullable: true
    }
}
