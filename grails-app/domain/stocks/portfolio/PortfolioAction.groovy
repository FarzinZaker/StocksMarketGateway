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

    byte getSign() {
        if (actionType == 'b')
            return 1
        else
            return -1
    }

    Long getSignedShareCount() {
        if (actionType == 'b')
            return shareCount
        else
            return -shareCount
    }

    Long getSignedCost() {
        if (actionType == 'b')
            return shareCount * sharePrice
        else
            return -shareCount * sharePrice
    }

    static transients = ['sign', 'signedShareCount', 'signedCost']

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
