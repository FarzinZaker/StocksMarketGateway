package stocks.rate.event

import stocks.RateHelper
import stocks.rate.Oil

class OilEvent {

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
    Oil data

    transient String getName(){
        RateHelper.OILS."${symbol}".name as String
    }

    static mapping = {
        table 'rate_oil_ev'
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
