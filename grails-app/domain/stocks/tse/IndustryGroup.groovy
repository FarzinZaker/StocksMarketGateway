package stocks.tse

class IndustryGroup {

    String code
    String name

    Date creationDate
    Date modificationDate

    static mapping = {
        table 'tse_industry_group'
    }

    static constraints = {
        code unique: true
        name nullable: true
    }
}
