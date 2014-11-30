package stocks.portfolio

import stocks.tse.Symbol

class PortfolioItem {
    Symbol symbol
    Portfolio portfolio
    Long cost
    Long currentValue

    static hasMany = [actions: PortfolioAction]
    static belongsTo = [portfolio: Portfolio]

    static constraints = {
        symbol(nullable: false)
        portfolio(nullable: false)
    }
}
