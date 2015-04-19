package stocks.portfolio.portfolioItems

import stocks.portfolio.PortfolioItem
import stocks.portfolio.basic.ImmovableProperty

class PortfolioImmovableItem extends PortfolioItem {

    ImmovableProperty immovableProperty

    transient Long getPropertyId() {
        immovableProperty.id
    }

    transient String getPropertyTitle() {
        immovableProperty.name
    }

    static constraints = {
    }
}
