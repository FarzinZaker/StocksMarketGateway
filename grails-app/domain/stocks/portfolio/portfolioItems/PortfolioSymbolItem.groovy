package stocks.portfolio.portfolioItems

import stocks.portfolio.PortfolioItem
import stocks.tse.Symbol

class PortfolioSymbolItem extends PortfolioItem {

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