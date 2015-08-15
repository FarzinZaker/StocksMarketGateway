package stocks.portfolio.basic

import stocks.User
import stocks.portfolio.Portfolio

class CustomSymbolPriority {

    static auditable = true
    static searchable = true

    String name
    CustomSymbol symbol

    User owner
    Portfolio portfolio

    Boolean deleted = false
    Date dateCreated
    Date lastUpdated

    static mapping = {
        table 'port_base_cust_sym_priority'
    }

    static constraints = {
        portfolio nullable: true
    }
}
