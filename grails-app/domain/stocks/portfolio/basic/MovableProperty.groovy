package stocks.portfolio.basic

import stocks.User

class MovableProperty {

    static auditable = true
    static searchable = true

    String name
    Integer price

    User owner

    Boolean deleted = false
    Date dateCreated
    Date lastUpdated

    static constraints = {
    }
}
