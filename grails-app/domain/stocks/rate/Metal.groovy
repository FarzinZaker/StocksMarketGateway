package stocks.rate

class Metal {

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
        table 'rate_metal'
        percent column: 'perc'
    }

    static constraints = {
    }
}
