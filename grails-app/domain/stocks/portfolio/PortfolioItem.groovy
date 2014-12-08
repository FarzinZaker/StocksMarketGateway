package stocks.portfolio

import stocks.tse.Symbol

class PortfolioItem {
    Symbol symbol
    Portfolio portfolio
    Long shareCount
    Long cost

    String getPortfolioName() {
        portfolio?.name
    }

    static hasMany = [actions: PortfolioAction]
    static belongsTo = [portfolio: Portfolio]
    static transients = ['portfolioName']

    static constraints = {
        symbol(nullable: false, unique: ["portfolio"])
        portfolio(nullable: false)
        shareCount(nullable: false)
        cost(nullable: false)
    }
}
