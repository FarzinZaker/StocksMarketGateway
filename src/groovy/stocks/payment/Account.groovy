package stocks.payment

import org.codehaus.groovy.grails.commons.ApplicationHolder

/**
 * Created by farzin on 8/25/2015.
 */
public class Account {
    String bankName
    byte[] bankLogo
    Boolean hasOnlinePayment
    String branchName
    String ownerName
    String accountNumber
    String shebaNumber
    String cardNumber
    String type
    String onlinePaymentConfiguration

    String toString(){
        def g = ApplicationHolder.application.mainContext.getBean( 'org.codehaus.groovy.grails.plugins.web.taglib.ApplicationTagLib' )
        g.message(code:"account.${bankName}.${type}")
    }
}
