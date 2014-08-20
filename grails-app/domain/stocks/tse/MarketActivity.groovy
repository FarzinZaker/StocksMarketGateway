package stocks.tse

class MarketActivity {

    Integer marketIdentifier
    Double indexLastValue
    Double indexChange
    Date date
    Integer tradeCount
    Double tradeValue
    Double tradeVolume
    String state
    Double value

    Date creationDate
    Date modificationDate

    static mapping = {
        table 'tse_market_activity'
    }

    static constraints = {
        marketIdentifier nullable: true
        indexLastValue nullable: true
        indexChange nullable: true
        date nullable: true
        tradeCount nullable: true
        tradeValue nullable: true
        tradeVolume nullable: true
        state nullable: true
        value nullable: true
    }
}
