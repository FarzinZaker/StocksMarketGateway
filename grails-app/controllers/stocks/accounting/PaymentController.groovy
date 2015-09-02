package stocks.accounting

import org.hibernate.engine.TransactionHelper
import stocks.AccountingHelper
import stocks.User

class PaymentController {

    def springSecurityService
    def mellatService
    def samanService

    def online() {
        if (params.accountId) {
            def user = springSecurityService.currentUser as User
            def account = grailsApplication.config.accounts.find { it.id == params.accountId?.toInteger() } as Map
            def model = [bankName: account?.bankName]
            def onlinePayment = new OnlinePayment()
            onlinePayment.accountId = account?.id
            onlinePayment.amount = params.amount as Integer
            onlinePayment.owner = user
            onlinePayment.date = new Date()
            onlinePayment.save()

            switch (account.bankName) {
                case 'mellat':

                    def result = mellatService.prepareForPayment(account, onlinePayment.id, onlinePayment.amount, user?.id)
                    if (result[0] == "0")
                        model.refId = result[1]
                    else
                        flash.message = result[0]

                    onlinePayment.initialResultCode = result[0] ?: null
                    if (result.size() > 1)
                        onlinePayment.referenceId = result[1]
                    onlinePayment.save()

                    break
                case 'saman':

                    model.amount = onlinePayment.amount
                    model.reservationNumber = onlinePayment.id
                    model.merchantId = account.onlinePaymentConfiguration.userName
                    break
            }
            model
        }
    }

    def onlineResultSaman() {

        def model = [:]
        Long reservationNumber = params.ResNum?.toLong();
        def status = params.State.toString();
        def referenceNumber = params.RefNum ? params.RefNum.toString() : '';
        model.appURL = params.appURL
        def onlinePayment = OnlinePayment.get(reservationNumber)
        model.onlinePayment = onlinePayment

        double state = -100;
        if (status.equals("OK")) {
            state = samanService.verifyPayment(grailsApplication.config.accounts.find {
                it.id == onlinePayment.accountId
            } as Map, referenceNumber)

            def transaction = new Transaction()
            transaction.date = new Date()
            transaction.accountId = onlinePayment.accountId
            transaction.creator = onlinePayment.owner
            transaction.customer = onlinePayment.owner
            transaction.type = AccountingHelper.TRANSACTION_TYPE_DEPOSIT
            transaction.value = onlinePayment.amount
            transaction.description = message(code: 'transaction.description.payment.online.saman')
            transaction.save()
        }
        model.verificationResult = state.toInteger()
        if (state.toInteger() > 0)
            onlinePayment.amount = state.toInteger()
        onlinePayment.resultCode = state.toString()
        onlinePayment.transactionReferenceCode = referenceNumber //params.MID
        onlinePayment.save()

        if (state.toInteger() > 0) {
            model.verificationResult = 0
        }

        render view: 'onlineResult', model: model
    }
}
