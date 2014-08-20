package stocks.tse

class IndustryGroupState {

    String industryGroupIdentifier
    Date date
    String state
    Boolean isLast

    Date creationDate
    Date modificationDate

    static mapping = {
        table 'tse_industry_group_state'
    }

    static constraints = {
        industryGroupIdentifier nullable: true
        date nullable: true
        state nullable: true
        isLast nullable: true
    }
}
