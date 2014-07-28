package stocks.tse

class Company {

    Integer marketIdentifier
    String code
    String name
    String internalCode
    String persianName

    Date creationDate
    Date modificationDate

    static mapping = {
        table 'tse_company'
    }

    static constraints = {
        marketIdentifier nullable: true, parameterIndex: 0
        code unique: true, xmlNodeName: 'CIsin'
        name nullable: true, xmlNodeName: 'LVal30'
        internalCode nullable: true, xmlNodeName: 'NInsCode'
        persianName nullable: true, xmlNodeName: 'LVal18AFC'
    }
}
