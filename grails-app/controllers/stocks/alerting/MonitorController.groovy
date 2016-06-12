package stocks.alerting

import fi.joensuu.joyds1.calendar.JalaliCalendar
import grails.converters.JSON
import groovy.time.TimeCategory
import stocks.DataService
import stocks.DataServiceState

class MonitorController {

    def dataService

    def dataSources() {

    }

    def dataSourcesJson() {
        def value = [:]


        value.data = dataService.getJobList().collect { item ->
            def row = null
            use(TimeCategory) {
                row = [
                        id              : item.jobName,
                        jobName         : message(code: item.jobName),
                        nextFireDate    : formatDate(item.nextFireTime),
                        nextFireTime    : formatTime(item.nextFireTime),
                        previousFireDate: formatDate(item.previousFireTime),
                        previousFireTime: formatTime(item.previousFireTime),
                        timesTriggered  : item.timesTriggered,
                        status          : item.nextFireTime + 2.minutes > new Date(),
                        statusData      : DataServiceState.findByServiceNameAndIsLastState(item.jobName.split('_').first(), true)?.data
                ]
            }
            row
        }
        value.total = value.data.size()
        render value as JSON
    }

    def restartJob() {
        try {
            dataService.restartJob(params.name as String)
            render "1"
        }
        catch (ignored) {
            render "0"
        }
    }

    private static def formatDate(Date date) {

        if (!date)
            return '-'

        def cal = Calendar.getInstance()
        cal.setTime(date)
        def jc = new JalaliCalendar(cal as GregorianCalendar)
        String.format("%04d/%02d/%02d", jc.getYear(), jc.getMonth(), jc.getDay())
    }

    private static def formatTime(Date date) {

        if (!date)
            return '-'

        def cal = Calendar.getInstance()
        cal.setTime(date)
        String.format("%02d:%02d:%02d", cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND))
    }

    def messages() {

    }

    def queuedMessagesJson() {
        def value = [:]


        value.data = QueuedMessage.list([max: 20, sort: "id", order: "desc"]).collect { item ->
            [
                    id                  : item.id,
                    receiverNumber      : item.receiverNumber,
                    user                : item.user?.toString(),
                    timeCreated         : formatTime(item.dateCreated),
                    dateCreated         : formatDate(item.dateCreated),
                    retryCount          : item.retryCount,
                    lastExecutionMessage: item.lastExecutionMessage,
                    body                : item.body,
                    deliveryMethod      : message(code: "deliveryMethod.${item.deliveryMethod}")
            ]
        }
        value.total = value.data.size()
        render value as JSON
    }

    def sentMessagesJson() {
        def value = [:]


        value.data = SentMessage.list([max: 20, sort: "id", order: "desc"]).collect { item ->
            [
                    id                  : item.id,
                    receiverNumber      : item.receiverNumber,
                    user                : item.user?.toString(),
                    timeCreated         : formatTime(item.dateCreated),
                    dateCreated         : formatDate(item.dateCreated),
                    retryCount          : item.retryCount,
                    lastExecutionMessage: item.lastExecutionMessage,
                    body                : item.body,
                    deliveryMethod      : message(code: "deliveryMethod.${item.deliveryMethod}")
            ]
        }
        value.total = value.data.size()
        render value as JSON
    }

    def failedMessagesJson() {
        def value = [:]


        value.data = FailedMessage.list([max: 20, sort: "id", order: "desc"]).collect { item ->
            [
                    id                  : item.id,
                    receiverNumber      : item.receiverNumber,
                    user                : item.user.toString(),
                    timeCreated         : formatTime(item.dateCreated),
                    dateCreated         : formatDate(item.dateCreated),
                    retryCount          : item.retryCount,
                    lastExecutionMessage: item.lastExecutionMessage,
                    body                : item.body,
                    deliveryMethod      : message(code: "deliveryMethod.${item.deliveryMethod}")
            ]
        }
        value.total = value.data.size()
        render value as JSON
    }
}
