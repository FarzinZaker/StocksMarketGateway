package stocks.rate.event

import stocks.rate.Currency

class CurrencyEvent {

    String symbol
    Double price
    Double change
    Double percent
    Double low
    Double high
    Date time

    Date creationDate
    Currency data

    static mapping = {
        table 'rate_currency_ev'
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
