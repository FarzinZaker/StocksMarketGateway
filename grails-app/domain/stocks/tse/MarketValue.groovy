package stocks.tse

class MarketValue {

    Integer marketIdentifier
    Double value
    Double valueChange
    Integer tradeCount
    Double tradeVolume
    Double tradeValue
    Date date

    Date creationDate
    Date modificationDate

    static mapping = {
        table 'tse_market_value'
        date column: 'dat'
    }

    static constraints = {
        marketIdentifier nullable: true
        value nullable: true
        tradeCount nullable: true
        tradeVolume nullable: true
        tradeValue nullable: true
        date nullable: true
    }
}
