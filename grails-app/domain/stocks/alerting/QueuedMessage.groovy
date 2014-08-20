package stocks.alerting

import stocks.User

class QueuedMessage {

    String body
    String receiverNumber
    User user
    Integer retryCount = 0
    String lastExecutionMessage = ''

    Date dateCreated
    Date lastUpdated

    static mapping = {
        table 'alerting_message_queued'
        tablePerHierarchy false
        user lazy: true
    }

    static constraints = {
        lastExecutionMessage nullable: true
    }
}
