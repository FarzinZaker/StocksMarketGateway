package stocks.commodity

class CommodityMarketActivity {

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

    Date creationDate
    Date modificationDate

    transient Double getValue(){
        internalValue + exportValue
    }

    transient Double getVolume(){
        internalVolume + exportVolume
    }

    transient Integer getBuyersCount(){
        internalBuyersCount + exportBuyersCount
    }

    transient Integer getSellersCount(){
        internalSellersCount + exportSellersCount
    }

    transient Integer getTradeCount(){
        internalTradeCount + exportTradeCount
    }

    static mapping = {
        table 'commodity_market_activity'
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
