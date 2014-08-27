package stocks.alerting

import fi.joensuu.joyds1.calendar.JalaliCalendar
import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass
import sms1000.SmsLocator
import stocks.TemplateHelper
import stocks.User

class SmsService {

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

        sendMessage(queryInstance.owner, prepareMessage(queryInstance, [record]))
    }

    def sendScheduledMessage(QueryInstance queryInstance, recordList) {
        if (!recordList?.size())
            return

        sendMessage(queryInstance.owner, prepareMessage(queryInstance, recordList))
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

    def sendMessage(User user, String body) {
        def message = new QueuedMessage()
        message.body = body
        message.receiverNumber = user.mobile
        message.user = user
        message.save()
    }

    def sendMessage(QueuedMessage message) {
        def messageService = new SmsLocator().getsmsSoap()

        message.retryCount++
        def result = messageService.doSendSMS(
                parameters.agah.userName,
                parameters.agah.userPassword,
                parameters.agah.senderNumber,
                message.receiverNumber,
                message.body,
                true, false, false, '')

        message.lastExecutionMessage = result

        if (result.toLowerCase().contains('ok')) {
            def sentMessage = new SentMessage(message.properties)
            sentMessage.dateCreated = new Date()
            sentMessage.save()
            message.delete()
        } else {
            if (message.retryCount < 5) {
                message.save()
            } else {
                def failedMessage = new FailedMessage(message.properties)
                failedMessage.dateCreated = new Date()
                failedMessage.save()
                message.delete()
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
