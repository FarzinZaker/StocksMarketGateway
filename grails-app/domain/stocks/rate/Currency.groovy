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
    }

    static constraints = {
    }
}
