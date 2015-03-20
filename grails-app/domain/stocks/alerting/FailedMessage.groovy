package stocks.alerting

import stocks.User

class FailedMessage {

    String body
    String receiverNumber
    User user
    Integer retryCount = 0
    String lastExecutionMessage = ''

    Date dateCreated

    static mapping = {
        table 'alt_message_failed'
        tablePerHierarchy false
        user lazy: true
        body type:'text'
    }

    static constraints = {
        lastExecutionMessage nullable: true
    }
}
