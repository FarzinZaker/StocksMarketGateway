package stocks.portfolio.portfolioItems

import stocks.portfolio.PortfolioItem
import stocks.tse.Symbol

class PortfolioInvestmentFundItem extends PortfolioItem{
    Symbol fund

    transient Long getPropertyId() {
        fund.id
    }

    transient String getPropertyTitle() {
        "${fund.persianCode} - ${fund.persianName}"
    }
    static constraints = {
    }
}
