package stocks.alerting

import fi.joensuu.joyds1.calendar.JalaliCalendar
import grails.util.Environment
import groovy.time.TimeCategory
import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass
import sms1000.SmsLocator
import stocks.MessageJob
import stocks.TemplateHelper
import stocks.User
import stocks.codal.Announcement
import stocks.tse.SupervisorMessage

class SmsService {
    static transactional = false
    def grailsApplication

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

        sendMessage(User.get(queryInstance.ownerId), queryInstance.domainClazz, prepareTitle(queryInstance, [record]), prepareMessage(queryInstance, [record]))
    }

    def sendScheduledMessage(QueryInstance queryInstance, recordList) {
        if (!recordList?.size())
            return

        sendMessage(User.get(queryInstance.ownerId), queryInstance.domainClazz, prepareTitle(queryInstance, recordList), prepareMessage(queryInstance, recordList))
    }

    String prepareTitle(QueryInstance queryInstance, recordList) {
        switch (queryInstance?.domainClazz) {
            case SupervisorMessage.class.canonicalName:
                return recordList.collect{SupervisorMessage item ->
                    item.title
                }.join(', ')
            case Announcement.class.canonicalName:
                return recordList.collect{Announcement item ->
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
                    def tokens = domainClass.persistentProperties.findAll {
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

    def sendMessage(User user, String type, String title, String body) {
        try {

            QueuedMessage.withTransaction {
                def message = new QueuedMessage()
                message.type = type
                message.title = title
                message.body = body
                message.receiverNumber = user.mobile
                message.receiverId = user.id
                message.user = user
                message.deliveryMethod = user.useMobilePushNotification ? MessageHelper.DELIVERY_METHOD_PUSH : MessageHelper.DELIVERY_METHOD_SMS
                message.status = MessageHelper.STATUS_WAITING
                message.save()
            }
        } catch (ignored) {
            if (Environment.current != Environment.DEVELOPMENT)
                throw ignored
        }
    }

    def sendMessage(QueuedMessage message) {
        if(message.deliveryMethod == MessageHelper.DELIVERY_METHOD_PUSH){
            if(message.status == MessageHelper.STATUS_WAITING)
                sendMessageViaMobilePush(message)
            if(message.status == MessageHelper.STATUS_SENT){
                def expireTime = message.lastUpdated
                use(TimeCategory){
                    expireTime = message.lastUpdated + 30.seconds
                }
                if(expireTime > new Date()){
                    message.deliveryMethod = MessageHelper.DELIVERY_METHOD_SMS
                    message.save(flush:true)
                    sendMessageViaSMS(message)
                }
            }
        }
        else if(message.deliveryMethod == MessageHelper.DELIVERY_METHOD_SMS){
            sendMessageViaSMS(message)
        }
    }

    def sendMessageViaMobilePush(QueuedMessage message) {

    }


    def sendMessageViaSMS(QueuedMessage message) {
        def messageService = new SmsLocator().getsmsSoap()

        message.retryCount++
        def result
        try {
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
        }
        catch (exception) {
            result = exception.message
        }

        message.lastExecutionMessage = result

        if (result.toLowerCase().contains('ok')) {
            SentMessage.withTransaction {
                def sentMessage = new SentMessage(message.properties)
                sentMessage.status = MessageHelper.STATUS_SUCCEED
                sentMessage.dateCreated = new Date()
                sentMessage.save()
            }
            QueuedMessage.withTransaction {
                message.delete()
            }
        } else {
            if (message.retryCount < 5) {
                message.status = MessageHelper.STATUS_FAILED
                message.save()
            } else {
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
