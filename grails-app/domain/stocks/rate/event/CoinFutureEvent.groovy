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

    static snapshotGroupProperty = 'data'
    static snapshotDateProperty = 'lastTradedPriceTime'

    Date dailySnapshot
    Date weeklySnapshot
    Date monthlySnapshot

    Date creationDate
    CoinFuture data

    static mapping = {
        table 'rate_future_ev'

        contractSizeUnitPersianDescription column: 'contractSizeUnitFaDesc'
        contractSizeUnitDescription column: 'contractSizeUnitDesc'
        contractCurrencyPersianDescription column: 'contractCurrencyFaDesc'
        contractCurrencyDescription column: 'contractCurrencyDesc'
        contractCurrencyDecimalPlaces column: 'contractCurrencyDecimalPlaces'
        firstTradedPriceChangesPercent column: 'firstTradedPriceChangesPerc'
        lastTradedPriceChangesPercent column: 'lastTradedPriceChangesPerc'
        highTradedPriceChangesPercent column: 'highTradedPriceChangesPerc'
        lowTradedPriceChangesPercent column: 'lowTradedPriceChangesPerc'
        tradesValueCurrencyPersianDescription column: 'tradesValueCurrencyFaDesc'
        tradesValueCurrencyDescription column: 'tradesValueCurrencyDesc'
        openInterestsChangesPercent column: 'openInterestsChangesPerc'
    }
    static constraints = {

        contractCode nullable: true
        lastTradingDate nullable: true
        contractDescription nullable: true
        contractSize nullable: true
        contractSizeUnitPersianDescription nullable: true
        contractSizeUnitDescription nullable: true
        contractCurrencyPersianDescription nullable: true
        contractCurrencyDescription nullable: true
        contractCurrencyDecimalPlaces nullable: true
        lastSettlementPrice nullable: true
        lastSettlementPriceDate nullable: true
        yesterdayOpenInterests nullable: true
        bidTotalVolume nullable: true
        bidVolume1 nullable: true
        bidPrice1 nullable: true
        bidVolume2 nullable: true
        bidPrice2 nullable: true
        bidVolume3 nullable: true
        bidPrice3 nullable: true
        bidVolume4 nullable: true
        bidPrice4 nullable: true
        bidVolume5 nullable: true
        bidPrice5 nullable: true
        askTotalVolume nullable: true
        askVolume1 nullable: true
        askPrice1 nullable: true
        askVolume2 nullable: true
        askPrice2 nullable: true
        askVolume3 nullable: true
        askPrice3 nullable: true
        askVolume4 nullable: true
        askPrice4 nullable: true
        askVolume5 nullable: true
        askPrice5 nullable: true
        ordersDateTime nullable: true
        firstTradedPrice nullable: true
        firstTradedPriceTime nullable: true
        firstTradedPriceChanges nullable: true
        firstTradedPriceChangesPercent nullable: true
        lastTradedPrice nullable: true
        lastTradedPriceTime nullable: true
        lastTradedPriceChanges nullable: true
        lastTradedPriceChangesPercent nullable: true
        highTradedPrice nullable: true
        highTradedPriceChanges nullable: true
        highTradedPriceChangesPercent nullable: true
        lowTradedPrice nullable: true
        lowTradedPriceChanges nullable: true
        lowTradedPriceChangesPercent nullable: true
        averageTradedPrice nullable: true
        openingPrice nullable: true
        closingPrice nullable: true
        tradesCount nullable: true
        tradesVolume nullable: true
        tradesValue nullable: true
        tradesValueCurrencyPersianDescription nullable: true
        tradesValueCurrencyDescription nullable: true
        openInterests nullable: true
        openInterestsChanges nullable: true
        openInterestsChangesPercent nullable: true
        lastUpdate nullable: true
        expired nullable: true

        data nullable: true

        dailySnapshot nullable: true
        weeklySnapshot nullable: true
        monthlySnapshot nullable: true
    }
}
