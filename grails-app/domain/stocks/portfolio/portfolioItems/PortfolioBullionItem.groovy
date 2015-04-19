package stocks.portfolio.portfolioItems

import stocks.portfolio.PortfolioItem

class PortfolioBullionItem extends PortfolioItem {

    Integer carat

    transient Long getPropertyId() {
        carat as Long
    }

    transient String getPropertyTitle() {
        "شمش طلای ${carat} عیار"
    }

    static constraints = {
        carat inList: [9, 14, 18, 20, 24]
    }
}
