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
        symbol nullable: true
        price nullable: true
        change nullable: true
        percent nullable: true
        low nullable: true
        high nullable: true
        time nullable: true
    }
}
