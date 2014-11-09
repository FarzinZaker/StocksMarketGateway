package stocks.alerting

class Schedule {

    static auditable = true

    Integer intervalStep


    Date dateCreated
    Date lastUpdated

    String type

    static hasMany = [days: ScheduleDay]

    static constraints = {
        type nullable: true, inList: ['eventBased', 'periodic', 'specificTime']
    }

    static mapping = {
        table 'alerting_schedule'
    }
}
