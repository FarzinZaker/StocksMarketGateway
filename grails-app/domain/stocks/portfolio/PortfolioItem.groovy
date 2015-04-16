package stocks.portfolio

import stocks.tse.Symbol

class PortfolioItem {
    Portfolio portfolio

    Long shareCount
    Long cost

    String getPortfolioName() {
        portfolio?.name
    }

    static hasMany = [actions: PortfolioAction]
    static belongsTo = [portfolio: Portfolio]
    static transients = ['portfolioName']

    static mapping = {
        table 'port_item'
    }

    static constraints = {
        portfolio(nullable: false)
        shareCount(nullable: false)
        cost(nullable: false)
    }
}
