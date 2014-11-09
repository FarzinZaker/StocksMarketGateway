package stocks.alerting

import stocks.User

class SentMessage {

    static auditable = true

    String body
    String receiverNumber
    User user
    Integer retryCount = 0
    String lastExecutionMessage = ''

    Date dateCreated

    static mapping = {
        table 'alerting_message_sent'
        tablePerHierarchy false
        user lazy: true
        body type:'text'
    }

    static constraints = {
        lastExecutionMessage nullable: true
    }
}
