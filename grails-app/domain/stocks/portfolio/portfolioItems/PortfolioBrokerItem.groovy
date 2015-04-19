package stocks.portfolio.portfolioItems

import stocks.Broker
import stocks.portfolio.PortfolioItem

class PortfolioBrokerItem extends PortfolioItem {

    Broker broker

    transient Long getPropertyId() {
        broker.id
    }

    transient String getPropertyTitle() {
        broker.name
    }

    static constraints = {
    }
}
