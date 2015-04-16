package stocks.portfolio.basic

import stocks.User

class ImmovableProperty {

    static auditable = true
    static searchable = true

    String name
    Integer area
    Integer price
    String address

    User owner

    Boolean deleted = false
    Date dateCreated
    Date lastUpdated

    static constraints = {
        area nullable: true
        address nullable: true
    }
}
