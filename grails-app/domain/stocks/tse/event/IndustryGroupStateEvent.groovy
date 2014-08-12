package stocks.tse.event

import stocks.tse.IndustryGroupState

class IndustryGroupStateEvent {

    String industryGroupIdentifier
    Date date
    String state
    Boolean isLast

    Date creationDate
    IndustryGroupState data

    static mapping = {
        table 'tse_industry_group_state_event'
    }

    static constraints = {
        industryGroupIdentifier nullable: true, xmlNodeName: 'CIdGrc'
        date nullable: true, xmlNodeName: 'DEven', locale: 'en', timeXmlNode: 'HEVen'
        state nullable: true, xmlNodeName: 'CEtaGrc'
        isLast nullable: true, xmlNodeName: 'Last'
        data nullable: true
    }
}
