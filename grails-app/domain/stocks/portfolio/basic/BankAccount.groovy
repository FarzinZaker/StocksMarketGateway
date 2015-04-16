package stocks.portfolio.basic

import stocks.User


class BankAccount {

    static auditable = true
    static searchable = true

    String bank
    String branch
    String accountNumber
    String accountType

    User owner

    Boolean deleted = false
    Date dateCreated
    Date lastUpdated

    static mapping = {
        table 'port_base_bank_account'
    }

    static constraints = {
        bank nullable: true
        branch nullable: true
        accountNumber nullable: true
        accountType nullable: true
    }
}
