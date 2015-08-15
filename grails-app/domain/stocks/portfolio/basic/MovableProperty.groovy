package stocks.portfolio.basic

import stocks.User
import stocks.portfolio.Portfolio

class MovableProperty {

    static auditable = true
    static searchable = true

    String name
    Integer price

    User owner
    Portfolio portfolio

    Boolean deleted = false
    Date dateCreated
    Date lastUpdated

    static mapping = {
        table 'port_base_movable_property'
    }

    static constraints = {
        portfolio nullable: true
    }
}
