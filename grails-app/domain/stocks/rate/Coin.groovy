package stocks.rate

class Coin {

    String symbol
    Double price
    Double change
    Double percent
    Double low
    Double high
    Date time

    Date creationDate
    Date modificationDate

    static mapping = {
        table 'rate_coin'
        percent column: 'perc'
    }

    static constraints = {
    }
}
