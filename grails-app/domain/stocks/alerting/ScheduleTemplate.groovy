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
        table 'alerting_schedule_template'
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
