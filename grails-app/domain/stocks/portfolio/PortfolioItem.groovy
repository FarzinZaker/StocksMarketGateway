package stocks.portfolio

import stocks.tse.Symbol

import java.beans.Introspector

class PortfolioItem {
    Portfolio portfolio

    Long shareCount
    Long cost
    Long avgBuyCost

    String getPortfolioName() {
        portfolio?.name
    }

//    static hasMany = [actions: PortfolioAction]
    static belongsTo = [portfolio: Portfolio]
    static transients = ['portfolioName']

    transient String getItemType() {
        this.class.name
    }

    transient Long getPropertyId() {
        0
    }

    transient String getPropertyTitle() {
        ''
    }

    static mapping = {
        table 'port_item'
    }

    static constraints = {
        portfolio(nullable: false)
        shareCount(nullable: false)
        cost(nullable: false)
        avgBuyCost(nullable: true)
    }
}
