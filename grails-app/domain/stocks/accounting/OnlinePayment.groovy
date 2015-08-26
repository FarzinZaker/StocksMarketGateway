package stocks.accounting

import stocks.User

class OnlinePayment {
    static auditable = true

    Integer accountId
//    Order order
    int amount
    Date date
    User owner
    String initialResultCode
    String referenceId
    String resultCode
    String transactionReferenceCode
//    Boolean usingCustomerAccountValueAllowed

    static mapping = {
        table 'acc_online_payment'
        date column: 'dat'
    }

    static constraints = {
        accountId()
        owner nullable: true
//        order nullable: true
        amount()
        date()
        initialResultCode nullable: true
        referenceId nullable: true
        resultCode nullable: true
        transactionReferenceCode nullable: true
    }
}
