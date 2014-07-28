package stocks.tse

class SymbolState {

    Integer marketIdentifier
    Symbol symbol
    Long symbolInternalCode
    String symbolPersianCode
    String symbolPersianName
    Board board
    String boardName
    String description
    String state

    Date creationDate
    Date modificationDate

    static mapping = {
        table 'tse_symbol_state'
    }

    static constraints = {
        marketIdentifier nullable: true, parameterIndex: 0
        symbol nullable: true, xmlNodeName: 'inscode', fkColumn: 'InternalCode'
        symbolInternalCode nullable: true, xmlNodeName: 'inscode'
        symbolPersianCode nullable: true, xmlNodeName: 'LVal18AFC'
        symbolPersianName nullable: true, xmlNodeName: 'LVal30'
        board nullable: true, xmlNodeName: 'LBoard', fkColumn: 'Name'
        boardName nullable: true, xmlNodeName: 'LBoard'
        description nullable: true, xmlNodeName: 'StateTypeDesc'
        state nullable: true, inList: ['I', 'A', 'AG', 'AS', 'AR', 'IG', 'IS', 'IR'], xmlNodeName: 'CEtaVal'
    }
}
