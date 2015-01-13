package stocks.rate.event

import stocks.rate.Coin

class CoinEvent {

    String symbol
    Double price
    Double change
    Double percent
    Double low
    Double high
    Date time

    static snapshotGroupProperty = 'symbol'

    Date dailySnapshot
    Date weeklySnapshot
    Date monthlySnapshot

    Date creationDate
    Coin data

    static mapping = {
        table 'rate_coin_event'
        percent column: 'perc'
    }
    static constraints = {

        symbol nullable: true
        price nullable: true
        change nullable: true
        percent nullable: true
        low nullable: true
        high nullable: true
        time nullable: true

        data nullable: true

        dailySnapshot nullable: true
        weeklySnapshot nullable: true
        monthlySnapshot nullable: true
    }
}
