package stocks.analysis

import grails.converters.JSON
import stocks.tse.Symbol

class BackTestSignal {

    BackTest backTest
    Symbol symbol
    Double price
    Integer count
    Double wage
    Double tax
    Date date

    Integer totalTradeCount
    Integer totalTradeVolume
    Double totalTradeValue
    Double closingPrice
    Double firstTradePrice
    Double lastTradePrice
    Double priceChange
    Double minPrice
    Double maxPrice
    Double yesterdayPrice

    String indicatorsJSON

    transient void setIndicators(value){
        indicators = value as JSON
    }

    transient def getIndicators() {
        JSON.parse(indicatorsJSON)
    }

    static mapping = {
        table 'analysis_back_test_signal'
    }

    static constraints = {
    }
}
