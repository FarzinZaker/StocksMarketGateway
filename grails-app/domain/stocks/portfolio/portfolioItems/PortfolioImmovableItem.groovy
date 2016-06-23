package stocks.portfolio.portfolioItems

import stocks.portfolio.PortfolioItem
import stocks.portfolio.PortfolioPropertyItem
import stocks.portfolio.basic.ImmovableProperty

class PortfolioImmovableItem extends PortfolioPropertyItem {

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
