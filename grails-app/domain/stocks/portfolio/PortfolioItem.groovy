package stocks.portfolio

import stocks.tse.Symbol

class PortfolioItem {
    Symbol symbol
    Portfolio portfolio
    Long cost
    Long currentValue

    String getPortfolioName() {
        portfolio?.name
    }

    static hasMany = [actions: PortfolioAction]
    static belongsTo = [portfolio: Portfolio]

    static transients = ['currentValue', 'portfolioName']

    static constraints = {
        symbol(nullable: false)
        portfolio(nullable: false)
    }
}
