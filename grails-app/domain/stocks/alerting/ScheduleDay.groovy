package stocks.alerting

class ScheduleDay {

    String day
    Integer startTimeInMinute
    Integer endTimeInMinute

    Schedule schedule

    static belongsTo = [Schedule]


    static mapping = {
        table 'alerting_schedule_day'
    }

    static constraints = {
        day inList: ['saturday', 'sunday', 'monday', 'tuesday', 'wednesday', 'thursday', 'friday']
    }
}
