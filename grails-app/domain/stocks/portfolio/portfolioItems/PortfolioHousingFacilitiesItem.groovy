package stocks.portfolio.portfolioItems

import stocks.portfolio.PortfolioItem
import stocks.tse.Symbol

class PortfolioHousingFacilitiesItem  extends PortfolioItem{
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
