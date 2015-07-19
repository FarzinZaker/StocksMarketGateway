package stocks.alerting

import stocks.User

class QueuedMessage {

    String title
    String type
    String body
    String receiverNumber
    Long receiverId
    User user
    String deliveryMethod
    String status
    Integer retryCount = 0
    String lastExecutionMessage = ''

    Date dateCreated
    Date lastUpdated

    static mapping = {
        table 'alt_message_queued'
        tablePerHierarchy false
        user lazy: true
        body type:'text'
    }

    static constraints = {

        deliveryMethod inList: MessageHelper.DELIVERY_METHOD_LIST
        status inList: MessageHelper.STATUS_LIST
        lastExecutionMessage nullable: true
    }
}
