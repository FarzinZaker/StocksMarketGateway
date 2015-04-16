package stocks.portfolio.basic

import stocks.User

class BusinessPartner {

    static auditable = true
    static searchable = true

    String name

    User owner

    Boolean deleted = false
    Date dateCreated
    Date lastUpdated

    static mapping = {
        table 'port_base_biz_partner'
    }

    static constraints = {
    }
}
