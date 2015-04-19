package stocks.portfolio.portfolioItems

import stocks.portfolio.PortfolioItem
import stocks.portfolio.basic.CustomBonds

class PortfolioCustomBondsItem extends PortfolioItem {

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
