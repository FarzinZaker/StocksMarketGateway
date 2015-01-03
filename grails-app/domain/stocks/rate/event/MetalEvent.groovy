package stocks.rate.event

import stocks.rate.Metal

class MetalEvent {

    String symbol
    Double price
    Double change
    Double percent
    Double low
    Double high
    Date time

    Date creationDate
    Metal data

    static mapping = {
        table 'rate_metal_event'
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
    }
}
