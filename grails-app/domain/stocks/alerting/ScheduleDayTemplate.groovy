package stocks.alerting

import org.joda.time.LocalTime
import org.joda.time.contrib.hibernate.PersistentLocalTimeAsTime

class ScheduleDayTemplate {

    static auditable = true

    String day
    Integer minStartTimeInMinute
    Integer maxEndTimeInMinute
    Integer suggestedStartTimeInMinute
    Integer suggestedEndTimeInMinute

    ScheduleTemplate scheduleTemplate

    static belongsTo = [ScheduleTemplate]


    static mapping = {
        table 'alerting_schedule_day_template'
    }

    static constraints = {
        day inList: ['saturday', 'sunday', 'monday', 'tuesday', 'wednesday', 'thursday', 'friday']
    }
}
