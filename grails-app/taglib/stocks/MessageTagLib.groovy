package stocks

class MessageTagLib {
    static namespace = "msg"
    def springSecurityService

    def sendButton = { attrs, body ->
        def user = springSecurityService.currentUser as User
        if (user?.id != attrs.userId?.toLong())
            out << render(template: '/message/sendButton', model: [user: User.get(attrs.userId)])
    }

    def sendWindow = { attrs, body ->
        out << render(template: '/message/messageFormWindow')
    }

    def menu = { attrs, body ->
        def user = springSecurityService.currentUser as User
        if (user) {
            def count = Message.countByReceiverAndIsReadAndDeleted(user, false, false)
            def list = Message.createCriteria().list {
                receiver {
                    eq('id', user?.id)
                }
                eq('deleted', false)
                eq('isRead', false)
                maxResults(5)
                order('isRead', ORDER_DESCENDING)
                order('dateCreated', ORDER_DESCENDING)
            }
            out << render(template: '/message/menu', model: [list: list, count: count])
        }
    }
}
