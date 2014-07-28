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
        code unique: true, xmlNodeName: 'CSoSecVal'
        name nullable: true, xmlNodeName: 'LSoSecVal'
        date nullable: true, xmlNodeName: 'DEven', locale: 'en'
        industryGroup nullable: true, xmlNodeName: 'CSecVal', fkColumn: 'Code'
        industryGroupCode nullable: true, xmlNodeName: 'CSecVal'
    }
}
