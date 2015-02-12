package stocks.rate

class Currency {

    String symbol
    String name
    Double price
    Double change
    Double percent
    Double low
    Double high
    Date time

    Date creationDate
    Date modificationDate

    static mapping = {
        table 'rate_currency'
        percent column: 'perc'
        symbol index: 'idx_symbol'
    }

    static constraints = {

        symbol nullable: true
        name nullable: true
        price nullable: true
        change nullable: true
        percent nullable: true
        low nullable: true
        high nullable: true
        time nullable: true
    }
}
