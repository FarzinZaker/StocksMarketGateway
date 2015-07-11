package stocks.tse

class EnergyMarketValue {

    Integer marketIdentifier
    Integer tradeCount
    Double tradeVolume
    Double tradeValue
    Double extraTradeValue
    Double extraTradeCount
    Date date

    Date creationDate
    Date modificationDate

    static mapping = {
        table 'tse_enrg_market_value'
        date column: 'dat'
    }

    static constraints = {
        marketIdentifier nullable: true
        tradeCount nullable: true
        tradeVolume nullable: true
        tradeValue nullable: true
        extraTradeValue nullable: true
        extraTradeCount nullable: true
        date nullable: true
    }
}
