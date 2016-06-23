package stocks.portfolio.portfolioItems

import stocks.Broker
import stocks.portfolio.PortfolioCashItem
import stocks.portfolio.PortfolioItem

class PortfolioBrokerItem extends PortfolioCashItem {

    Broker broker

    transient Long getPropertyId() {
        broker.id
    }

    transient String getPropertyTitle() {
        broker.name
    }

    static constraints = {
    }

    def beforeInsert() {
        avgBuyCost = cost
        shareCount = shareCount != 0 ? 1 : 0
    }

    def beforeUpdate() {
        avgBuyCost = cost
        shareCount = shareCount != 0 ? 1 : 0
    }
}
