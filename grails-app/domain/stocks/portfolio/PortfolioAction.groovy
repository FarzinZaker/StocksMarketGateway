package stocks.portfolio

class PortfolioAction {
    String actionType
    Date actionDate
    Date creationDate
    Date updateDate
    Long sharePrice
    Long shareCount
    String actionDescription
    PortfolioItem portfolioItem

    static belongsTo = [portfolioItem: PortfolioItem]

    static constraints = {
        actionType(nullable: false, inList: ['b', 's']) // in future additional actions could be added, like going short or long
        actionDate(nullable: false)
        creationDate(nullable: true)
        updateDate(nullable: true)
        sharePrice(nullable: false)
        shareCount(nullable: false)
        actionDescription()
        portfolioItem(nullable: false)
    }
}
