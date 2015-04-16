package stocks.portfolio.basic

import stocks.User

class CustomSymbol {

    static auditable = true
    static searchable = true

    String name
    String code
    Date establishDate

    User owner

    Boolean deleted = false
    Date dateCreated
    Date lastUpdated

    static mapping = {
        table 'port_base_cust_sym'
    }

    static constraints = {

        code nullable: true
        establishDate nullable: true
    }
}
