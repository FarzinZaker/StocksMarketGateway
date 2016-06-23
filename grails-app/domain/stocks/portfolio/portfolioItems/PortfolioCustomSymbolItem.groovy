package stocks.portfolio.portfolioItems

import stocks.portfolio.PortfolioItem
import stocks.portfolio.PortfolioPropertyItem
import stocks.portfolio.basic.CustomSymbol

class PortfolioCustomSymbolItem extends PortfolioPropertyItem {

    CustomSymbol customSymbol

    transient Long getPropertyId() {
        customSymbol.id
    }

    transient String getPropertyTitle() {
        customSymbol.name
    }

    static constraints = {
    }
}
