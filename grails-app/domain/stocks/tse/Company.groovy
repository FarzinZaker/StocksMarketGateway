package stocks.tse

class Company {

    static searchable = true

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
        marketIdentifier nullable: true
        code unique: true
        name nullable: true
        internalCode nullable: true
        persianName nullable: true
    }
}
