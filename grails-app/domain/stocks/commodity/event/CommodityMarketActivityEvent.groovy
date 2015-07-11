package stocks.commodity.event

import stocks.commodity.CommodityMarketActivity

class CommodityMarketActivityEvent {

    Integer marketIdentifier
    Double internalVolume
    Double exportVolume
    Double internalValue
    Double exportValue
    Integer internalBuyersCount
    Integer exportBuyersCount
    Integer internalSellersCount
    Integer exportSellersCount
    Integer internalTradeCount
    Integer exportTradeCount

    Date date

    CommodityMarketActivity data
    Date creationDate

    static mapping = {
        table 'commodity_market_activity_ev'
        date column: 'dat'
    }

    static constraints = {
        internalVolume nullable: true
        exportVolume nullable: true
        internalValue nullable: true
        exportValue nullable: true
        internalBuyersCount nullable: true
        exportBuyersCount nullable: true
        internalSellersCount nullable: true
        exportSellersCount nullable: true
        internalTradeCount nullable: true
        exportTradeCount nullable: true
    }
}
