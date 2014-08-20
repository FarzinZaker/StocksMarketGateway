package stocks.alerting

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import org.joda.time.LocalTime
import stocks.RoleHelper
import stocks.User

@Secured([RoleHelper.ROLE_ADMIN])
class ScheduleTemplateController {

    def springSecurityService

    def index() {}

    def build() {
        def scheduleTemplateInstance
        if (params.id)
            scheduleTemplateInstance = ScheduleTemplate.get(params.id)
        else
            scheduleTemplateInstance = new ScheduleTemplate(intervalSteps: [15])

        [scheduleTemplateInstance: scheduleTemplateInstance]
    }

    def save() {
        def scheduleTemplate = new ScheduleTemplate()
        if (params.id) {
            scheduleTemplate = ScheduleTemplate.get(params.id)
        } else {
            scheduleTemplate = new ScheduleTemplate()
            scheduleTemplate.owner = springSecurityService.currentUser as User ?: User.findByUsername('admin')
        }

        scheduleTemplate.title = params.title
        scheduleTemplate.eventBasedNotificationEnabled = params.eventBasedNotificationEnabled == 'on'
        scheduleTemplate.periodicNotificationEnabled = params.periodicNotificationEnabled == 'on'
        if (scheduleTemplate.periodicNotificationEnabled) {
            scheduleTemplate.intervalSteps = params.intervalSteps instanceof String[] ? params.intervalSteps.collect {
                it.toInteger()
            } : [params.intervalSteps.toInteger()]
        } else
            scheduleTemplate.intervalSteps = [15]
        scheduleTemplate.save()

        ScheduleDayTemplate.findAllByScheduleTemplate(scheduleTemplate).each { it.delete() }

        if (scheduleTemplate.periodicNotificationEnabled) {
            ScheduleDayTemplate.constraints.day.inList.each { day ->
                if (params."${day}_enabled" == 'on') {
                    def scheduleDayTemplate = new ScheduleDayTemplate(day: day)
                    scheduleDayTemplate.minStartTimeInMinute = params."${day}_allowedTimeRangeStart".toInteger()
                    scheduleDayTemplate.maxEndTimeInMinute = params."${day}_allowedTimeRangeEnd".toInteger()
                    scheduleDayTemplate.suggestedStartTimeInMinute = params."${day}_suggestedTimeRangeStart".toInteger()
                    scheduleDayTemplate.suggestedEndTimeInMinute = params."${day}_suggestedTimeRangeEnd".toInteger()
                    scheduleDayTemplate.scheduleTemplate = scheduleTemplate
                    scheduleDayTemplate.save()
                }
            }
        }

        redirect(action: 'list')
    }

    def list() {

    }

    def jsonList() {
        def value = [:]
        def parameters = [offset: params.skip, max: params.pageSize, sort: params["sort[0][field]"] ?: "lastUpdated", order: params["sort[0][dir]"] ?: "desc"]

        value.data = (params.search ?
                ScheduleTemplate.findAllByIdInListAndDeleted(ScheduleTemplate.search(params.search?.toString()).results.collect {
                    it.id
                }, false, parameters) :
                ScheduleTemplate.findAllByDeleted(false, parameters)).collect {
            [
                    id                 : it.id,
                    title              : it.title,
                    intervalStepsString: it.intervalStepsString,
                    owner              : it.owner.toString(),
                    dateCreated        : format.jalaliDate(date: it.dateCreated, hm: 'true'),
                    lastUpdated        : format.jalaliDate(date: it.lastUpdated, hm: 'true')
            ]
        }
        value.total = ScheduleTemplate.count().toString()
        render value as JSON

        render value as JSON
    }

    def delete() {

        def scheduleTemplate = ScheduleTemplate.get(params.id)
        scheduleTemplate.deleted = true
        render(scheduleTemplate.save() ? '1' : '0')
    }
}
