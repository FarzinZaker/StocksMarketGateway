package stocks.portfolio.portfolioItems

import stocks.portfolio.PortfolioItem
import stocks.rate.Coin

class PortfolioCoinItem extends PortfolioItem {

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
