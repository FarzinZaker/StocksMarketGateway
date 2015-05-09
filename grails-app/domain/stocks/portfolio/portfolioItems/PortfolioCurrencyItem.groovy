package stocks.portfolio.portfolioItems

import stocks.portfolio.PortfolioItem
import stocks.rate.Currency

class PortfolioCurrencyItem extends PortfolioItem {

    Currency currency

    transient Long getPropertyId() {
        currency.id
    }

    transient String getPropertyTitle() {
        currency.name
    }

    static constraints = {
    }
}