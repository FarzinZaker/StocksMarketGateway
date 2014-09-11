package stocks.commodity.event

import stocks.commodity.Commodity
import stocks.commodity.Group
import stocks.commodity.MainGroup
import stocks.commodity.Producer
import stocks.commodity.Provider
import stocks.commodity.Subgroup
import stocks.commodity.TradeStatistics

class TradeStatisticsEvent {

    Subgroup subgroup
    Producer producer

    Commodity commodity
    String contractType
    Integer lowestPrice
    Integer closingWeightedAveragePrice
    Integer highestPrice
    Integer offering
    Integer bestOfferingPrice
    Integer demand
    Integer tradeVolume
    Long tradeValue
    Date tradeDate
    Date deliveryDate
    String deliveryPoint
    Provider provider
    Date settlementMaturityDate

    Date creationDate
    TradeStatistics data

    static mapping = {
        table 'commodity_trade_statistics_event'
    }

    static constraints = {

        subgroup nullable: true
        producer nullable: true
        commodity nullable: true
        contractType nullable: true
        lowestPrice nullable: true
        closingWeightedAveragePrice nullable: true
        highestPrice nullable: true
        offering nullable: true
        bestOfferingPrice nullable: true
        demand nullable: true
        tradeVolume nullable: true
        tradeValue nullable: true
        tradeDate nullable: true
        deliveryDate nullable: true
        deliveryPoint nullable: true
        provider nullable: true
        settlementMaturityDate nullable: true

        data nullable: true
    }
}
