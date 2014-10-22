package stocks.tse.event

import stocks.tse.IndustryGroup
import stocks.tse.IndustrySubgroup

class IndustrySubgroupEvent {

    String code
    String name
    Date date
    IndustryGroup industryGroup
    String industryGroupCode

    Date creationDate
    IndustrySubgroup data

    static mapping = {
        table 'tse_industry_subgroup_event'
    }

    static constraints = {
        code xmlNodeName: 'CSoSecVal'
        name nullable: true, xmlNodeName: 'LSoSecVal'
        date nullable: true, xmlNodeName: 'DEven', locale: 'en'
        industryGroup nullable: true, xmlNodeName: 'CSecVal', fkColumn: 'Code'
        industryGroupCode nullable: true, xmlNodeName: 'CSecVal'
        data nullable: true
    }
}
