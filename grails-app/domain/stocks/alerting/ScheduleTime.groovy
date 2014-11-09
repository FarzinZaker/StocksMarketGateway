package stocks.alerting

class ScheduleTime {

    static auditable = true

    Integer timeInMinute

    Schedule schedule

    static belongsTo = [Schedule]


    static mapping = {
        table 'alerting_schedule_time'
    }

    static constraints = {
    }
}
