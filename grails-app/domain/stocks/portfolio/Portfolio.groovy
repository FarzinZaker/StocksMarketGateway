package stocks.portfolio

import stocks.User

class Portfolio {
    String name

    User owner

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
    }
}
