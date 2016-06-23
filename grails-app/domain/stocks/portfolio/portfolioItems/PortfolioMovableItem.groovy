package stocks.portfolio.portfolioItems

import stocks.portfolio.PortfolioItem
import stocks.portfolio.PortfolioPropertyItem
import stocks.portfolio.basic.MovableProperty

class PortfolioMovableItem extends PortfolioPropertyItem {

    MovableProperty movableProperty

    transient Long getPropertyId() {
        movableProperty.id
    }

    transient String getPropertyTitle() {
        movableProperty.name
    }

    static constraints = {
    }
}
