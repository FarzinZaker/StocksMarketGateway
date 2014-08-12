package stocks.tse.event

import stocks.tse.MarketActivity

class MarketActivityEvent {

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
    MarketActivity data

    static mapping = {
        table 'tse_market_activity_event'
    }

    static constraints = {
        marketIdentifier nullable: true, parameterIndex: 0
        indexLastValue nullable: true, xmlNodeName: 'IndexLastValue'
        indexChange nullable: true, xmlNodeName: 'IndexChange'
        date nullable: true, xmlNodeName: 'MarketActivityDEven', locale: 'en', timeXmlNode: 'MarketActivityDEven'
        tradeCount nullable: true, xmlNodeName: 'MarketActivityZTotTran'
        tradeValue nullable: true, xmlNodeName: 'MarketActivityQTotCap'
        tradeVolume nullable: true, xmlNodeName: 'MarketActivityQTotTran'
        state nullable: true, xmlNodeName: 'MarketState'
        value nullable: true, xmlNodeName: 'MarketValue'
        data nullable: true
    }
}
