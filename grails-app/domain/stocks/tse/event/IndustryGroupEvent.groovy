package stocks.tse.event

import stocks.tse.IndustryGroup

class IndustryGroupEvent {

    String code
    String name

    Date creationDate
    IndustryGroup data

    static mapping = {
        table 'tse_industry_group_ev'
    }

    static constraints = {
        code xmlNodeName: 'CSecVal'
        name nullable: true, xmlNodeName: 'LSecVal'
        data nullable: true
    }
}
