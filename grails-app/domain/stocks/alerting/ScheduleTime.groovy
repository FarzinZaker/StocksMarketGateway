package stocks.alerting

class ScheduleTime {

    Integer timeInMinute

    Schedule schedule

    static belongsTo = [Schedule]


    static mapping = {
        table 'alerting_schedule_time'
    }

    static constraints = {
    }
}
