package stocks.alerting

import stocks.User

class FailedMessage {

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

    static mapping = {
        table 'alt_message_failed'
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
