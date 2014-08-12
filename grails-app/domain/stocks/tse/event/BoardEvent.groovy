package stocks.tse.event

import stocks.tse.Board

class BoardEvent {

    Integer code
    String name

    Date creationDate
    Board data

    static mapping = {
        table 'tse_board_event'
    }

    static constraints = {
        code unique: true, xmlNodeName: 'CComVal'
        name nullable: true, xmlNodeName: 'LBoard'

        data nullable: true
    }
}

