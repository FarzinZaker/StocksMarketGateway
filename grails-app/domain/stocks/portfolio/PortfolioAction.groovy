package stocks.portfolio

import stocks.Broker

class PortfolioAction {
    String actionType
    Date actionDate
    Date creationDate
    Date updateDate
    Long sharePrice
    Long shareCount
    Broker broker
    Float discount
    Float wage
    String actionDescription
    PortfolioItem portfolioItem
    PortfolioAction parentAction
    Portfolio portfolio

    byte getSign() {
        if (actionType == 'b')
            return 1
        else
            return -1
    }

    Long getSignedShareCount() {
        if (['b', 'd'].contains(actionType))
            return shareCount
        else
            return -shareCount
    }

    Long getSignedCost() {
        if (['b', 'd'].contains(actionType))
            return shareCount * sharePrice
        else
            return -shareCount * sharePrice
    }

    static transients = ['sign', 'signedShareCount', 'signedCost']

    static belongsTo = [portfolioItem: PortfolioItem]

    static mapping = {
        table 'port_action'
    }

    static constraints = {
        actionType(nullable: false, inList: ['b', 's', 'd', 'w'])
        // in future additional actions could be added, like going short or long
        actionDate(nullable: false)
        creationDate(nullable: true)
        updateDate(nullable: true)
        sharePrice(nullable: false)
        shareCount(nullable: false)
        broker(nullable: true)
        discount(nullable: true)
        wage(nullable: true)
        actionDescription()
        portfolioItem(nullable: true)
        parentAction(nullable: true)
    }
}
