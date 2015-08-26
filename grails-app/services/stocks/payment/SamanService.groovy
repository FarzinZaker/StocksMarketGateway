package stocks.payment

import stocks.payment.saman.PaymentIFBindingLocator
import stocks.payment.saman.PaymentIFBindingSoap_PortType

class SamanService {

    PaymentIFBindingSoap_PortType getService(){
        new PaymentIFBindingLocator().getPaymentIFBindingSoap()
    }

    Double verifyPayment(Map account, String referenceNumber){

        def result = getService().verifyTransaction(referenceNumber, account.onlinePaymentConfiguration.userName)

        return result
    }
}
