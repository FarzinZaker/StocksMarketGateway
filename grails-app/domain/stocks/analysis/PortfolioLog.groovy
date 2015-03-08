package stocks.analysis

import grails.converters.JSON

class PortfolioLog {

    BackTest backTest
    Date date
    Integer remainingOutlay

    Integer stockCount = 0
    Double price

    static constraints = {
    }
}
