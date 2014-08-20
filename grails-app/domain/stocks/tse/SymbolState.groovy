package stocks.tse

class SymbolState {

//    Integer marketIdentifier
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
//        marketIdentifier nullable: true, parameterIndex: 0
        symbol nullable: true
        symbolInternalCode nullable: true
        symbolPersianCode nullable: true
        symbolPersianName nullable: true
        board nullable: true
        boardName nullable: true
        description nullable: true
        state nullable: true, inList: ['I', 'A', 'AG', 'AS', 'AR', 'IG', 'IS', 'IR']
    }
}
