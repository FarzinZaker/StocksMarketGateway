package stocks.tse

class IndustrySubgroup {

    String code
    String name
    Date date
    IndustryGroup industryGroup
    String industryGroupCode

    Date creationDate
    Date modificationDate

    static mapping = {
        table 'tse_industry_subgroup'
    }

    static constraints = {
        code unique: true
        name nullable: true
        date nullable: true
        industryGroup nullable: true
        industryGroupCode nullable: true
    }
}
