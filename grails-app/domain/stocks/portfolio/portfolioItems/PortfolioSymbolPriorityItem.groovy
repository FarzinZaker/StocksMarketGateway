package stocks.portfolio.portfolioItems

import stocks.portfolio.PortfolioItem
import stocks.portfolio.PortfolioPropertyItem
import stocks.tse.Symbol

class PortfolioSymbolPriorityItem extends PortfolioPropertyItem {

    Symbol symbolPriority

    transient Long getPropertyId() {
        symbolPriority.id
    }

    transient String getPropertyTitle() {
        "${symbolPriority.persianCode} - ${symbolPriority.persianName}"
    }

    static constraints = {
    }
}
