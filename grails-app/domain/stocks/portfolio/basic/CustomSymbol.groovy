package stocks.portfolio.basic

import stocks.User
import stocks.portfolio.Portfolio

class CustomSymbol {

    static auditable = true
    static searchable = true

    String name
    String code
    Date establishDate

    User owner
    Portfolio portfolio

    Boolean deleted = false
    Date dateCreated
    Date lastUpdated

    static mapping = {
        table 'port_base_cust_sym'
    }

    static constraints = {
        portfolio nullable: true
        code nullable: true
        establishDate nullable: true
    }
}
