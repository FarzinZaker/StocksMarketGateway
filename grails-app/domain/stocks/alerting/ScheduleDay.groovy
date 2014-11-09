package stocks.alerting

class ScheduleDay {

    static auditable = true

    String day
    Integer startTimeInMinute = -1
    Integer endTimeInMinute = -1

    Schedule schedule

    static belongsTo = [Schedule]


    static mapping = {
        table 'alerting_schedule_day'
    }

    static constraints = {
        day inList: ['saturday', 'sunday', 'monday', 'tuesday', 'wednesday', 'thursday', 'friday']
    }
}
