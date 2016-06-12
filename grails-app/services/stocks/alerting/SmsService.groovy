package stocks.alerting

import fi.joensuu.joyds1.calendar.JalaliCalendar
import grails.util.Environment
import groovy.time.TimeCategory
import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass
import org.jboss.aerogear.unifiedpush.message.MessageResponseCallback
import org.jboss.aerogear.unifiedpush.message.UnifiedMessage
import sms1000.SmsLocator
import stocks.MessageJob
import stocks.TemplateHelper
import stocks.User
import stocks.codal.Announcement
import stocks.messaging.PushUtil

//import stocks.messaging.PushUtil
import stocks.tse.SupervisorMessage

class SmsService {
    static transactional = false
    def grailsApplication
    def telegramService

    static parameters = [
            agah: [
                    userName    : "ofogh",
                    userPassword: "f@n@v@r@n",
                    senderNumber: '500012001048551'
            ]
    ]

    def sendEventBasedMessage(QueryInstance queryInstance, record) {
        if (!record)
            return

        def user = User.get(queryInstance.ownerId)
        def title = prepareTitle(queryInstance, [record])
        def message = prepareMessage(queryInstance, [record])

        if (queryInstance.smsEnabled == null || queryInstance.smsEnabled)
            sendMessage(user, queryInstance.domainClazz, title, message, MessageHelper.DELIVERY_METHOD_SMS)

        if (queryInstance.telegramEnabled == null || queryInstance.telegramEnabled)
            sendMessage(user, queryInstance.domainClazz, title, message, MessageHelper.DELIVERY_METHOD_TELEGRAM)

        if (queryInstance.appEnabled == null || queryInstance.appEnabled)
            sendMessage(user, queryInstance.domainClazz, title, message, MessageHelper.DELIVERY_METHOD_PUSH)
    }

    def sendScheduledMessage(QueryInstance queryInstance, recordList) {
        if (!recordList?.size())
            return

        def user = User.get(queryInstance.ownerId)
        def title = prepareTitle(queryInstance, recordList)
        def message = prepareMessage(queryInstance, recordList)

        if (queryInstance.smsEnabled == null || queryInstance.smsEnabled)
            sendMessage(user, queryInstance.domainClazz, title, message, MessageHelper.DELIVERY_METHOD_SMS)

        if (queryInstance.telegramEnabled == null || queryInstance.telegramEnabled)
            sendMessage(user, queryInstance.domainClazz, title, message, MessageHelper.DELIVERY_METHOD_TELEGRAM)

        if (queryInstance.appEnabled == null || queryInstance.appEnabled)
            sendMessage(user, queryInstance.domainClazz, title, message, MessageHelper.DELIVERY_METHOD_PUSH)
    }

    String prepareTitle(QueryInstance queryInstance, recordList) {
        switch (queryInstance?.domainClazz) {
            case SupervisorMessage.class.canonicalName:
                return recordList.collect { SupervisorMessage item ->
                    item.title
                }.join(', ')
            case Announcement.class.canonicalName:
                return recordList.collect { Announcement item ->
                    item.title
                }.join(', ')
            default:
                return ''
        }
    }

    String prepareMessage(QueryInstance queryInstance, recordList) {

        def message = queryInstance.smsHeaderTemplate + '\n' +
                recordList.collect { record ->
                    def msg = queryInstance.smsTemplate

                    DefaultGrailsDomainClass domainClass = grailsApplication.getDomainClass(queryInstance.query.domainClazz) as DefaultGrailsDomainClass
                    domainClass.persistentProperties.findAll {
                        it.domainClass.constrainedProperties."${it.name}".metaConstraints.token
                    }.each { property ->
                        def value = record."${property.name}"
                        switch (property.type) {
                            case Integer:
                                value = value.toString()
                                break;
                            case Date:
                                value = formatDate(value)
                                break;
                            default:
                                value = value.toString()
                        }
                        msg = msg.replace("[${property.name}]".toString(), value.toString());
                    }
                    msg
                }.join('\n') + '\n' +
                queryInstance.smsFooterTemplate

        TemplateHelper.SYSTEM_TOKENS.each { token ->
            message = message.replace("[${token}]", TemplateHelper.getSystemTokenValue(token))
        }

        message
    }

    def sendMessage(User user, String type, String title, String body, String deliveryMethod = MessageHelper.DELIVERY_METHOD_SMS) {
        try {

            QueuedMessage.withTransaction {
                def message = new QueuedMessage()
                message.type = type
                message.title = title
                message.body = body
                message.receiverNumber = user.mobile
                message.receiverId = user.id
                message.user = user
                message.deliveryMethod = deliveryMethod
                message.status = MessageHelper.STATUS_WAITING
                message.save(flush: true)
            }
        } catch (ignored) {
            if (Environment.current != Environment.DEVELOPMENT)
                throw ignored
        }
    }

    def sendMessage(QueuedMessage message) {
        message.retryCount++

        try {
            if (message.deliveryMethod == MessageHelper.DELIVERY_METHOD_PUSH) {
                sendMessageViaMobilePush(message)
            } else if (message.deliveryMethod == MessageHelper.DELIVERY_METHOD_SMS) {
                sendMessageViaSMS(message)
            } else if (message.deliveryMethod == MessageHelper.DELIVERY_METHOD_TELEGRAM) {
                sendMessageViaTelegram(message)
            }
        }
        catch (exception) {
            message.lastExecutionMessage = exception.message
            message.status = MessageHelper.STATUS_FAILED
        }
        message.save(flush:true)


        if (message.status == MessageHelper.STATUS_SUCCEED) {
            SentMessage.withTransaction {
                def sentMessage = new SentMessage(message.properties)
                sentMessage.dateCreated = new Date()
                sentMessage.save()
            }
            QueuedMessage.withTransaction {
                message.delete()
            }
        } else if (message.status == MessageHelper.STATUS_FAILED && message.retryCount >= 5) {
            FailedMessage.withTransaction {
                def failedMessage = new FailedMessage(message.properties)
                failedMessage.dateCreated = new Date()
                failedMessage.save()
            }
            QueuedMessage.withTransaction {
                message.delete()
            }
        }
    }

    def sendMessageViaTelegram(QueuedMessage message) {
        if (message.user?.telegramUser?.chatId) {
            telegramService.sendMessage(message.user, message.body)
            message.status = MessageHelper.STATUS_SUCCEED
        } else
            message.status = MessageHelper.STATUS_FAILED
    }

    def sendMessageViaMobilePush(QueuedMessage message) {
        PushUtil.push(message.receiverId?.toString(), message?.title)
        message.status = MessageHelper.STATUS_SUCCEED
    }

    def sendCustomMessage(String number, String body) {
        new SmsLocator().getsmsSoap().doSendSMS(
                parameters.agah.userName,
                parameters.agah.userPassword,
                parameters.agah.senderNumber,
                number,
                body,
                true, false, false, '')
    }

    def sendMessageViaSMS(QueuedMessage message) {
        def messageService = new SmsLocator().getsmsSoap()

        def result
        if (Environment.current == Environment.DEVELOPMENT)
            result = 'development test: ok'
        else
            result = messageService.doSendSMS(
                    parameters.agah.userName,
                    parameters.agah.userPassword,
                    parameters.agah.senderNumber,
                    message.receiverNumber,
                    message.body,
                    true, false, false, '')

        message.lastExecutionMessage = result

        if (result.toLowerCase().contains('ok')) {
            message.status = MessageHelper.STATUS_SUCCEED
        } else {
            message.status = MessageHelper.STATUS_FAILED
        }

        return result
    }

    def formatDate = { date ->
        def result
        def cal = Calendar.getInstance()
        cal.setTime(date)
        def jc = new JalaliCalendar(cal)

        result = String.format("%04d/%02d/%02d", jc.getYear(), jc.getMonth(), jc.getDay())
        if (date.hours > 0 || date.minutes > 0 || date.seconds > 0)
            result += String.format(" %02d:%02d", cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE))

    }
}
