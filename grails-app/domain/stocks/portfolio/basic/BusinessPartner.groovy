package stocks.portfolio.basic

import stocks.User
import stocks.portfolio.Portfolio

class BusinessPartner {

    static auditable = true
    static searchable = true

    String name

    User owner
    Portfolio portfolio

    Boolean deleted = false
    Date dateCreated
    Date lastUpdated

    static mapping = {
        table 'port_base_biz_partner'
    }

    static constraints = {
        portfolio nullable: true
    }

    @Override
    public String toString(){
        name
    }
}
