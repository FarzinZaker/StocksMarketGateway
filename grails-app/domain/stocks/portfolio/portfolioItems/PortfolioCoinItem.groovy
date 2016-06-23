package stocks.portfolio.portfolioItems

import stocks.portfolio.PortfolioItem
import stocks.portfolio.PortfolioPropertyItem
import stocks.rate.Coin

class PortfolioCoinItem extends PortfolioPropertyItem {

    Coin coin

    transient Long getPropertyId() {
        coin.id
    }

    transient String getPropertyTitle() {
        coin.name
    }

    static constraints = {
    }
}
