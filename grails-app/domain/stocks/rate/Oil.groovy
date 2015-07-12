package stocks.rate

import stocks.RateHelper

class Oil {

    static searchable = true

    String symbol
    Double price
    String unit
    Double change
    Double percent
    Double open
    Double low
    Double high
    Date time
    Date creationDate
    Date modificationDate

    transient String getName(){
        RateHelper.OILS."${symbol}".name as String
    }

    static mapping = {
        table 'rate_oil'
        percent column: 'perc'
        symbol index: 'idx_oil_symbol'
        sort 'name'
    }

    static constraints = {
        symbol nullable: true
        price nullable: true
        unit nullable: true
        open nullable: true
        change nullable: true
        percent nullable: true
        low nullable: true
        high nullable: true
        time nullable: true
    }
}
