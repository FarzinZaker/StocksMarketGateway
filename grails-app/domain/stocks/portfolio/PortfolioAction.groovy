package stocks.portfolio

import stocks.Broker
import stocks.portfolio.basic.BankAccount
import stocks.portfolio.basic.BusinessPartner

class PortfolioAction {
    String actionType
    Date actionDate
    Date creationDate
    Date updateDate
    Long sharePrice
    Long shareCount
    Float discount
    Float wage
    Broker broker
    Long brokerPortion
    BankAccount bankAccount
    Long bankPortion
    BusinessPartner businessPartner
    Long businessPartnerPortion
    String actionDescription
    PortfolioItem portfolioItem

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

    Long getSignedBrokerPortion() {
        if (['s', 't'].contains(actionType))
            return brokerPortion
        else
            return -brokerPortion
    }

    Long getSignedBankPortion() {
        if (['s', 't'].contains(actionType))
            return bankPortion
        else
            return -bankPortion
    }

    Long getSignedBusinessPartnerPortion() {
        if (['s', 't'].contains(actionType))
            return businessPartnerPortion
        else
            return -businessPartnerPortion
    }

    static transients = ['sign', 'signedShareCount', 'signedCost', 'signedBrokerPortion', 'signedBankPortion', 'signedBusinessPartnerPortion']

    static belongsTo = [portfolioItem: PortfolioItem]

    static mapping = {
        table 'port_action'
    }

    static constraints = {
        actionType(nullable: false, inList: ['b', 's', 'd', 'w', 't']) // in future additional actions could be added, like going short or long
        actionDate(nullable: false)
        creationDate(nullable: true)
        updateDate(nullable: true)
        sharePrice(nullable: false)
        shareCount(nullable: false)
        discount(nullable: true)
        wage(nullable: true)
        broker(nullable: true)
        brokerPortion(nullable: true)
        bankAccount(nullable: true)
        bankPortion(nullable: true)
        businessPartner(nullable: true)
        businessPartnerPortion(nullable: true)
        actionDescription()
        portfolioItem(nullable: false)
    }
}
