package stocks

import grails.converters.JSON
import stocks.alerting.MessageHelper
import stocks.alerting.QueuedMessage
import stocks.alerting.SentMessage

class MobileController {

    def springSecurityService

    def authenticate() {
        if (!params.username || !params.password) {
            render([
                    status: 'f',
                    id    : 0
            ] as JSON)
            return
        }

        def user = User.findByUsername(params.username?.toString()?.toLowerCase())
        if (!user || !user.enabled || user.accountExpired || user.passwordExpired) {
            render([
                    status: 'f',
                    id    : 0
            ] as JSON)
            return
        }

        if (user.password == springSecurityService.encodePassword(params.password?.toString())) {
            if (!user.useMobilePushNotification) {
                user.useMobilePushNotification = true
                user.save()
            }
            render([
                    status: 's',
                    id    : user.id?.toString()
            ] as JSON)
        } else {
            render([
                    status: 'f',
                    id    : 0
            ] as JSON)
        }
    }

    def messageDelivery() {
        if (!params.id || !params.user) {
            render([
                    status: 'f'
            ] as JSON)
            return
        }
        def message = QueuedMessage.get(params.id as Long)
        if (message && message.receiverId == (params.user as Long)) {

            message.status = MessageHelper.STATUS_SUCCEED
            message.save()
            render([
                    status: 's'
            ] as JSON)
        } else {
            render([
                    status: 'f'
            ] as JSON)
        }
    }

    def messageList() {

        println(params)

        if (!params.user) {
            render([
                    status: 'f',
                    list  : []
            ] as JSON)
            return
        }

        def user = params.user as Long
        def type = params.type as String
        def after = params.after as Long
        def before = params.before as Long

        def list = SentMessage.createCriteria().list {
            eq('receiverId', user)
            if (type)
                eq('type', type)
            if (after)
                gt('id', after)
            if (before) {
                lt('id', before)
                maxResults(10)
            }
            if (!before && !after) {
                maxResults(10)
            }
            if (after) {
                order('id', ORDER_ASCENDING)
            } else {
                order('id', ORDER_DESCENDING)
            }
            projections {
                property('id')
                property('type')
                property('title')
                property('dateCreated')
            }
        }.collect {
            [
                    id   : it[0],
                    type : it[1],
                    title: it[2],
                    date : it[3]
            ]
        }

        render([
                status: 's',
                list  : list
        ] as JSON)
    }

    def messageBody() {

        if (!params.user || !params.id) {
            render([
                    status: 'f',
                    body  : ''
            ] as JSON)
            return
        }
        def user = params.user as Long
        def id = params.id as Long

        def message = SentMessage.get(id)
        if (message.receiverId != user) {
            render([
                    status: 'f',
                    body  : ''
            ] as JSON)
            return
        }

        render([
                status: 's',
                body  : message?.body
        ] as JSON)
    }
}
