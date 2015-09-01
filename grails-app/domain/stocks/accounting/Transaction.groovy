package stocks.accounting

import stocks.AccountingHelper
import stocks.User

class Transaction {

    static auditable = true
    Integer accountId
    Integer value
    String type
    Date date

    User creator
    User customer

    Boolean deleted = false
//    Order order

    static mapping = {
        table 'acc_transaction'
        date column: 'dat'
    }

    static constraints = {
        accountId (nullable: true)
        value min: 0
        type inList: AccountingHelper.TRANSACTION_TYPE_LIST
//        order nullable: true
        creator(nullable: true)
    }
}