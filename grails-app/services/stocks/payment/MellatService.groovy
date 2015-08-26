package stocks.payment

import stocks.payment.mellat.IPaymentGateway
import stocks.payment.mellat.PaymentGatewayImplServiceLocator

class MellatService {

    def grailsApplication

    IPaymentGateway getService(){
        new PaymentGatewayImplServiceLocator().getPaymentGatewayImplPort()
    }

    def prepareForPayment(Map account, id, amount, customerId) {
        def onlinePaymentConfiguration = new XmlParser().parseText(account.onlinePaymentConfiguration)
        def result = getService().bpPayRequest(
                account.onlinePaymentConfiguration.terminalCode.text().toLong(),
                account.onlinePaymentConfiguration.userName.text(),
                account.onlinePaymentConfiguration.password.text(),
                id.toLong(),
                amount.toLong(),
                new Date().format('yyyyMMdd'),
                new Date().format('HHmmss'),
                'OnlinePayment',
                "${grailsApplication.config.grails.serverURL}/order/onlinePaymentResultMellat",
                0).toString().split(',')

        return result
    }

    def verifyPayment(Map account, orderId, saleOrderId, saleReferenceId){
        def result = getService().bpVerifyRequest(
                account.onlinePaymentConfiguration.terminalCode.text().toLong(),
                account.onlinePaymentConfiguration.userName.text(),
                account.onlinePaymentConfiguration.password.text(),
                orderId.toLong(),
                saleOrderId.toLong(),
                saleReferenceId.toLong()).toString()

        return result
    }

    def settlePayment(Map account, orderId, saleOrderId, saleReferenceId){
        def onlinePaymentConfiguration = new XmlParser().parseText(account.onlinePaymentConfiguration)
        def result = getService().bpSettleRequest(
                account.onlinePaymentConfiguration.terminalCode.text().toLong(),
                account.onlinePaymentConfiguration.userName.text(),
                account.onlinePaymentConfiguration.password.text(),
                orderId.toLong(),
                saleOrderId.toLong(),
                saleReferenceId.toLong()).toString()

        return result
    }
}
