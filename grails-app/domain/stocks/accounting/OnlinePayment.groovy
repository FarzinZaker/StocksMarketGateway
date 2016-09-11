package stocks.accounting

import stocks.User

class OnlinePayment {
    static auditable = true

    Integer accountId
    int amount
    Date date
    User owner
    String initialResultCode
    String referenceId
    String resultCode
    String transactionReferenceCode

    static mapping = {
        table 'acc_online_payment'
        date column: 'dat'
    }

    static constraints = {
        accountId()
        owner nullable: true
        amount()
        date()
        initialResultCode nullable: true
        referenceId nullable: true
        resultCode nullable: true
        transactionReferenceCode nullable: true
    }
}
