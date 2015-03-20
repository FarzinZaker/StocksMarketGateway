package stocks.tse

class SupervisorMessage {

    Date date
    String title
    String description

    Date creationDate
    Date modificationDate

    static mapping = {
        table 'tse_supervisor_message'
        description type: 'text'
        date column: 'dat'
    }

    static constraints = {
        date xmlNodeName: 'DEven'
        title nullable: true
        description nullable: true
    }
}
