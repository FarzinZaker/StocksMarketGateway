package stocks.portfolio

class PortfolioAction {
    String actionType
    Date actionDate
    Long sharePrice
    Long shareCount
    String actionDescription
    PortfolioItem portfolioItem

    static belongsTo = [portfolioItem: PortfolioItem]

    static constraints = {
        actionType(nullable: false, inList: ['b', 's']) // in future additional actions could be added, like going short or long
        actionDate(nullable: false)
        sharePrice(nullable: false)
        shareCount(nullable: false)
        actionDescription()
        portfolioItem(nullable: false)
    }
}
