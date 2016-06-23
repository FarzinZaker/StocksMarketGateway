package stocks.portfolio.portfolioItems

import stocks.portfolio.PortfolioItem
import stocks.portfolio.PortfolioPropertyItem
import stocks.tse.Symbol

class PortfolioBondsItem extends PortfolioPropertyItem {

    Symbol bonds

    transient Long getPropertyId() {
        bonds.id
    }

    transient String getPropertyTitle() {
        "${bonds.persianCode} - ${bonds.persianName}"
    }

    static constraints = {
    }
}
