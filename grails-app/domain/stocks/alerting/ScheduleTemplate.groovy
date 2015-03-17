package stocks.alerting

import stocks.User

class ScheduleTemplate {

    static auditable = true

    static searchable = true

    String title
    String intervalStepsString
    Boolean eventBasedNotificationEnabled = true
    Boolean periodicNotificationEnabled = true
    Boolean specificTimeNotificationEnabled = true
    User owner

    Boolean deleted = false

    Date dateCreated
    Date lastUpdated

    static hasMany = [dayTemplates: ScheduleDayTemplate]

    static constraints = {
    }

    static mapping = {
        table 'alt_schedule_template'
        eventBasedNotificationEnabled column: 'event_based'
        periodicNotificationEnabled column: 'periodic'
        specificTimeNotificationEnabled column: 'specific'
    }

    static transients = ['intervalSteps']

    Integer[] getIntervalSteps() {
        if (!intervalStepsString || intervalStepsString == '')
            []
        else
            intervalStepsString.split(',').collect { it.toInteger() }
    }

    void setIntervalSteps(Integer[] value) {
        intervalStepsString = value.join(',')
    }
}
