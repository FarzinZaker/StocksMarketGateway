package stocks.tse

class Board {

    Integer code
    String name

    Date creationDate
    Date modificationDate

    static mapping = {
        table 'tse_board'
    }

    static constraints = {
        code unique: true, xmlNodeName: 'CComVal'
        name nullable: true, xmlNodeName: 'LBoard'
    }
}
