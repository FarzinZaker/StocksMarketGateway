package stocks.portfolio.portfolioItems

import stocks.portfolio.PortfolioItem
import stocks.portfolio.PortfolioPropertyItem
import stocks.tse.Symbol

class PortfolioHousingFacilitiesItem  extends PortfolioPropertyItem{
    Symbol housingFacility

    transient Long getPropertyId() {
        housingFacility.id
    }

    transient String getPropertyTitle() {
        "${housingFacility.persianCode} - ${housingFacility.persianName}"
    }
    static constraints = {
    }
}
