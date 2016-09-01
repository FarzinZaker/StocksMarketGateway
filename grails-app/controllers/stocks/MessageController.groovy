package stocks

import grails.converters.JSON
import org.apache.lucene.search.BooleanQuery
import org.ocpsoft.prettytime.PrettyTime

class MessageController {

    def springSecurityService

    def form() {
        [
                receiver       : User.get(params.receiver),
                originalMessage: Message.get(params.inReplyTo),
                forward        : params.forward ? true : false
        ]
    }

    def userAutoComplete() {
        def queryStr = params."filter[filters][0][value]"?.toString() ?: ''
        BooleanQuery.setMaxClauseCount(1000000)

        def result = User.search("*${queryStr}*", max: 20).results.unique { a, b -> a?.id <=> b?.id }.collect {
            [
                    name : it.nickname,
                    value: it.id
            ]
        }
        render([data: result] as JSON)
    }

    def save() {
        def message = new Message(params)
        message.sender = springSecurityService.currentUser as User
        if (message.inReplyTo)
            message.rootMessage = (message.inReplyTo?.rootMessage ?: message.inReplyTo)
        message.save(flush: true)
        render '1'
    }

    def delete() {
        def message = Message.get(params.id)
        message.deleted = true
        message.save(flush: true)
        render '1'
    }

    def list() {

    }

    def jsonList() {

        def value = [:]
        def parameters = [offset: params.skip, max: params.pageSize, sort: params["sort[0][field]"] ?: "dateCreated", order: params["sort[0][dir]"] ?: "desc"]

        def list = Message.findAllByDeleted(false, parameters)
        value.total = Message.countByDeleted(false)

        value.data = list.collect {
            [
                    id           : it.id,
                    isRead       : it.isRead,
                    shortBody    : it.shortBody,
                    sender       : it.sender?.nickname,
                    senderId     : it?.senderId,
                    rootMessageId: it?.rootMessageId ?: it?.id,
                    date         : new PrettyTime(new Locale('fa')).format(it?.dateCreated)
            ]
        }

        render value as JSON
    }

    def view() {
        def rootMessage = Message.get(params.id)
        def user = springSecurityService.currentUser as User
        def msg = Message.get(params.msg)
        if(msg){
            msg.isRead = true
            msg.save(flush: true)
        }
        [
                rootMessage: rootMessage,
                msg        : msg,
                list       : Message.findAllByRootMessage(rootMessage),
                user       : user
        ]
    }
}
