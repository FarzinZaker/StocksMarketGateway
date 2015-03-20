package stocks.alerting

import stocks.User

class SentMessage {

    String body
    String receiverNumber
    User user
    Integer retryCount = 0
    String lastExecutionMessage = ''

    Date dateCreated

    static mapping = {
        table 'alt_message_sent'
        tablePerHierarchy false
        user lazy: true
        body type:'clob'
    }

    static constraints = {
        lastExecutionMessage nullable: true
    }
}
