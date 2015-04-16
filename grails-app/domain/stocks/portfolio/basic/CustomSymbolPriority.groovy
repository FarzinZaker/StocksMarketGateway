package stocks.portfolio.basic

import stocks.User

class CustomSymbolPriority {

    static auditable = true
    static searchable = true

    String name
    CustomSymbol symbol

    User owner

    Boolean deleted = false
    Date dateCreated
    Date lastUpdated

    static mapping = {
        table 'port_base_cust_sym_priority'
    }

    static constraints = {
    }
}
