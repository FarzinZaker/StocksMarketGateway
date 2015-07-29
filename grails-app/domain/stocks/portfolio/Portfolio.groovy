package stocks.portfolio

import stocks.User

class Portfolio {
    String name

    User owner

    Boolean defaultItems = true
    Boolean fullAccounting = false
    Boolean useWageAndDiscount = false
    Boolean useBroker = false

    Date dateCreated
    Date lastUpdated
    Boolean deleted = false

    static belongsTo = [owner: User]

    static mapping = {
        table 'port_portfolio'
    }

    static constraints = {
        name(nullable: false, unique: ["owner"])
        owner(nullable: false)
        deleted(nullable: false)
        defaultItems(nullable: true)
        fullAccounting(nullable: true)
        useWageAndDiscount(nullable: true)
        useBroker(nullable: true)
    }
}
