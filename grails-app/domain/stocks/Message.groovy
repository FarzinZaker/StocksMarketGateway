package stocks

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
        if (body?.size() > 100)
            body?.substring(0, 100) + '...'
        else
            body
    }
}
