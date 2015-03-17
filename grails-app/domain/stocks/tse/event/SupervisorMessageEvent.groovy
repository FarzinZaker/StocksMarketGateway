package stocks.tse.event

import stocks.tse.SupervisorMessage

class SupervisorMessageEvent {

    Date date
    String title
    String description

    Date creationDate
    SupervisorMessage data

    static mapping = {
        table 'tse_supervisor_message_ev'
        description type: 'text'
        date column: 'dat'
    }

    static constraints = {
        date xmlNodeName: 'DEven', locale: 'en', timeXmlNode: 'HEven'
        title nullable: true, xmlNodeName: 'TseTitle'
        description nullable: true, xmlNodeName: 'TseDesc'
        data nullable: true
    }
}
