package stocks.portfolio.portfolioItems

import stocks.portfolio.PortfolioItem
import stocks.portfolio.PortfolioPropertyItem
import stocks.portfolio.basic.CustomBonds

class PortfolioCustomBondsItem extends PortfolioPropertyItem {

    CustomBonds customBonds

    transient Long getPropertyId() {
        customBonds.id
    }

    transient String getPropertyTitle() {
        customBonds.name
    }

    static constraints = {
    }
}
