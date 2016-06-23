package stocks.portfolio.portfolioItems

import stocks.portfolio.PortfolioItem
import stocks.portfolio.PortfolioPropertyItem
import stocks.tse.Symbol

class PortfolioSymbolItem extends PortfolioPropertyItem {

    Symbol symbol

    transient Long getPropertyId() {
        symbol.id
    }

    transient String getPropertyTitle() {
        "${symbol.persianCode} - ${symbol.persianName}"
    }

    static constraints = {
    }
}
