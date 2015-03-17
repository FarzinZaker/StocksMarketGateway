package stocks.tse.event

import stocks.tse.Company

class CompanyEvent {

    Integer marketIdentifier
    String code
    String name
    String internalCode
    String persianName

    Date creationDate
    Company data

    static mapping = {
        table 'tse_company_ev'
    }

    static constraints = {
        marketIdentifier nullable: true, parameterIndex: 0
        code xmlNodeName: 'CIsin'
        name nullable: true, xmlNodeName: 'LVal30'
        internalCode nullable: true, xmlNodeName: 'NInsCode'
        persianName nullable: true, xmlNodeName: 'LVal18AFC'
        data nullable: true
    }
}
