package stocks.portfolio.basic

import stocks.User
import stocks.portfolio.Portfolio

class ImmovableProperty {

    static auditable = true
    static searchable = true

    String name
    Integer area
    Integer price
    String address

    User owner
    Portfolio portfolio

    Boolean deleted = false
    Date dateCreated
    Date lastUpdated

    static mapping = {
        table 'port_base_immovable_property'
    }

    static constraints = {
        portfolio nullable: true
        area nullable: true
        address nullable: true
    }
}
