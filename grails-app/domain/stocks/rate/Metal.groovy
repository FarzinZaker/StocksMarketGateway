package stocks.rate

import stocks.RateHelper

class Metal {

    static searchable = true

    String symbol
    Double price
    Double change
    Double percent
    Double low
    Double high
    Date time

    Date creationDate
    Date modificationDate

    transient String getName(){
        RateHelper.METALS."${symbol}"?.name as String
    }

    static mapping = {
        table 'rate_metal'
        percent column: 'perc'
        symbol index: 'idx_me_symbol'
        sort 'name'
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

    @Override
    String toString(){
        name
    }
}
