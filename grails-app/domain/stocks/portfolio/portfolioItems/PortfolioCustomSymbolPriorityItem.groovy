package stocks.portfolio.portfolioItems

import stocks.portfolio.PortfolioItem
import stocks.portfolio.basic.CustomSymbolPriority

class PortfolioCustomSymbolPriorityItem extends PortfolioItem {

    CustomSymbolPriority customSymbolPriority

    transient Long getPropertyId() {
        customSymbolPriority.id
    }

    transient String getPropertyTitle() {
        customSymbolPriority.name
    }

    static constraints = {
    }
}
