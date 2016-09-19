package stocks

import stocks.util.StringHelper

class Message {

    String body
    User sender
    User receiver
    Message inReplyTo
    Message rootMessage
    Boolean isRead = false
    Boolean deleted = false
    Date dateCreated
    Date lastUpdated

    static constraints = {
        inReplyTo nullable: true
        rootMessage nullable: true
    }

    public transient String getShortBody() {
        def strippedBody = body
        strippedBody = strippedBody.replaceAll("<(.|\n)*?>", '')
        if (strippedBody?.size() > 100)
            strippedBody?.substring(0, 100) + '...'
        else
            strippedBody
    }
}
