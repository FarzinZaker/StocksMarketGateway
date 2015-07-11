package stocks.tse.event

import stocks.tse.MarketValue

class MarketValueEvent {

    Integer marketIdentifier
    Double value
    Double valueChange
    Integer tradeCount
    Double tradeVolume
    Double tradeValue
    Date date

    Date creationDate
    MarketValue data

    static mapping = {
        table 'tse_market_value_ev'
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
