package stocks.portfolio

import stocks.User

class Portfolio {
    String name
    User owner
    Date creationDate
    Boolean deleted = false

    static belongsTo = [owner: User]

    static constraints = {
        name(nullable: false)
        owner(nullable: false)
        creationDate(nullable: false)
        deleted(nullable: false)
    }
}
