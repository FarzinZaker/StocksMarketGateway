package stocks.portfolio.basic

import stocks.User
import stocks.portfolio.Portfolio

class CustomBonds {

    static auditable = true
    static searchable = true

    String name
    String bank
    Double value
    Float benefit
    Integer benefitPeriod
    String benefitPeriodType
    Date firstPayDate
    Date dueDate

    User owner
    Portfolio portfolio

    Boolean deleted = false
    Date dateCreated
    Date lastUpdated

    static mapping = {
        table 'port_base_cust_bonds'
    }

    static constraints = {
        portfolio nullable: true
        name()
        bank nullable: true
        value nullable: true
        benefit nullable: true
        benefitPeriod nullable: true
        benefitPeriodType nullable: true, inList: ['monthly', 'yearly']
        firstPayDate nullable: true
        dueDate nullable: true
    }
}
