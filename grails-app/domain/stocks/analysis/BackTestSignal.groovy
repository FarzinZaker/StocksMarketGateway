package stocks.analysis

import grails.converters.JSON
import stocks.tse.Symbol

class BackTestSignal {

    BackTest backTest
    Symbol symbol
    Double price
    Integer stockCount
    Double wage
    Double tax
    Date date
    Double effect
    String reason

//    Integer totalTradeCount
//    Integer totalTradeVolume
//    Double totalTradeValue
//    Double closingPrice
//    Double firstTradePrice
//    Double lastTradePrice
//    Double priceChange
//    Double minPrice
//    Double maxPrice
//    Double yesterdayPrice

//    String indicatorsJSON


//    transient void setIndicators(value){
//        indicatorsJSON = value as JSON
//    }
//
//    transient def getIndicators() {
//        JSON.parse(indicatorsJSON)
//    }

    static mapping = {
        table 'ana_back_test_signal'
        date column: 'dat'
    }

    static constraints = {
        effect nullable: true
    }
}
