package stocks.tse.event

import stocks.tse.EnergyMarketValue

class EnergyMarketValueEvent {

    Integer marketIdentifier
    Integer tradeCount
    Double tradeVolume
    Double tradeValue
    Double extraTradeValue
    Double extraTradeCount
    Date date

    EnergyMarketValue data
    Date creationDate

    static mapping = {
        table 'tse_enrg_market_value_ev'
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
        data nullable: true
    }
}
