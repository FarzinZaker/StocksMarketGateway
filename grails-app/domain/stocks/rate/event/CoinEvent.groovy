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

    Date creationDate
    Coin data

    static mapping = {
        table 'rate_coin_event'
        percent column: 'perc'
    }
    static constraints = {


        data nullable: true
    }
}
