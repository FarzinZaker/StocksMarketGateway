package stocks.portfolio.basic

import stocks.User
import stocks.portfolio.Portfolio


class BankAccount {

    static auditable = true
    static searchable = true

    String bank
    String branch
    String accountNumber
    String accountType

    User owner
    Portfolio portfolio

    Boolean deleted = false
    Date dateCreated
    Date lastUpdated

    static mapping = {
        table 'port_base_bank_account'
    }

    static constraints = {
        portfolio nullable: true
        bank nullable: true
        branch nullable: true
        accountNumber nullable: true
        accountType nullable: true
    }
}
