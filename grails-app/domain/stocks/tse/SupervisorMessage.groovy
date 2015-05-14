package stocks.tse

class SupervisorMessage {

    Date date
    String title
    String description

    Date creationDate
    Date modificationDate


    static notify = true

    static mapping = {
        table 'tse_supervisor_message'
        description type: 'text'
        date column: 'dat'
    }

    static constraints = {
        date xmlNodeName: 'DEven', token: true
        title nullable: true, query: true, token: true
        description nullable: true, query: true, token: true
    }
}
