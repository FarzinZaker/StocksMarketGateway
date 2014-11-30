package stocks.portfolio

class PortfolioAction {
    String actionType
    String actionDate
    String price
    String actionDescription
    PortfolioItem portfolioItem

    static belongsTo = [portfolioItem: PortfolioItem]

    static constraints = {
        actionType(nullable: false, inList: ['b', 's']) // in future additional actions could be added, like going short or long
        actionDate(nullable: false)
        price(nullable: false)
        actionDescription()
    }
}
