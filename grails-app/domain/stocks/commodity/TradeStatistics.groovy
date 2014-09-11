package stocks.commodity

class TradeStatistics {

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
    Date modificationDate

    static mapping = {
        table 'commodity_trade_statistics'
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
    }
}
