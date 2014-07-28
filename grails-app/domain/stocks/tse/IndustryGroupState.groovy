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
        industryGroupIdentifier nullable: true, xmlNodeName: 'CIdGrc'
        date nullable: true, xmlNodeName: 'DEven', locale: 'en', timeXmlNode: 'HEVen'
        state nullable: true, xmlNodeName: 'CEtaGrc'
        isLast nullable: true, xmlNodeName: 'Last'
    }
}
