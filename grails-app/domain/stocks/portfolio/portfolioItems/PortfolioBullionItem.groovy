package stocks.portfolio.portfolioItems

import stocks.portfolio.PortfolioItem

class PortfolioBullionItem extends PortfolioItem {

    Integer carat

    static constraints = {
        carat inList: [9, 14, 18, 20, 24]
    }
}
