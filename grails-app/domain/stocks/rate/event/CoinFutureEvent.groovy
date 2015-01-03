package stocks.rate.event

import stocks.rate.CoinFuture

class CoinFutureEvent {

    String contractCode
    Date lastTradingDate
    String contractDescription
    Double contractSize
    String contractSizeUnitPersianDescription
    String contractSizeUnitDescription
    String contractCurrencyPersianDescription
    String contractCurrencyDescription
    Integer contractCurrencyDecimalPlaces
    Double lastSettlementPrice
    Date lastSettlementPriceDate
    Integer yesterdayOpenInterests
    Integer bidTotalVolume
    Integer bidVolume1
    Double bidPrice1
    Integer bidVolume2
    Double bidPrice2
    Integer bidVolume3
    Double bidPrice3
    Integer bidVolume4
    Double bidPrice4
    Integer bidVolume5
    Double bidPrice5
    Integer askTotalVolume
    Integer askVolume1
    Double askPrice1
    Integer askVolume2
    Double askPrice2
    Integer askVolume3
    Double askPrice3
    Integer askVolume4
    Double askPrice4
    Integer askVolume5
    Double askPrice5
    Date ordersDateTime
    Double firstTradedPrice
    Date firstTradedPriceTime
    Double firstTradedPriceChanges
    Double firstTradedPriceChangesPercent
    Double lastTradedPrice
    Date lastTradedPriceTime
    Double lastTradedPriceChanges
    Double lastTradedPriceChangesPercent
    Double highTradedPrice
    Double highTradedPriceChanges
    Double highTradedPriceChangesPercent
    Double lowTradedPrice
    Double lowTradedPriceChanges
    Double lowTradedPriceChangesPercent
    Double averageTradedPrice
    Double openingPrice
    Double closingPrice
    Integer tradesCount
    Integer tradesVolume
    Double tradesValue
    String tradesValueCurrencyPersianDescription
    String tradesValueCurrencyDescription
    Integer openInterests
    Double openInterestsChanges
    Double openInterestsChangesPercent
    Date lastUpdate
    Boolean expired

    Date creationDate
    CoinFuture data

    static mapping = {
        table 'rate_future_event'
    }
    static constraints = {


        data nullable: true
    }
}
