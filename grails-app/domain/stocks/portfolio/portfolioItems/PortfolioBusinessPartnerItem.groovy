package stocks.portfolio.portfolioItems

import stocks.portfolio.PortfolioCashItem
import stocks.portfolio.PortfolioItem
import stocks.portfolio.basic.BusinessPartner

class PortfolioBusinessPartnerItem extends PortfolioCashItem {

    BusinessPartner partner

    transient Long getPropertyId() {
        partner.id
    }

    transient String getPropertyTitle() {
        partner.name
    }

    static constraints = {
    }

    def beforeInsert() {
        avgBuyCost = cost
        shareCount = shareCount != 0 ? 1 : 0
    }

    def beforeUpdate() {
        avgBuyCost = cost
        shareCount = shareCount != 0 ? 1 : 0
    }
}
