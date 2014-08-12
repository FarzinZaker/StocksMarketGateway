package stocks.tse

class Future {

    String code
    Date date
    Date settlementDate
    Integer initialMargin
    Integer minMargin
    Integer settlementPrice
    Integer maxBrokerOpenPositions
    Integer maxClientOpenPositions
    Integer maxMarketOpenPositions
    Integer closingPrice
    Integer adjustedClosingPrice
    Integer dayClosingPrice
    Date beginDate
    Date endDate
    Integer quantity
    Integer adjustedQuantity
    String description
    Company company
    String companyCode
    Integer maxOrderCount

    Date creationDate
    Date modificationDate

    static mapping = {
        table 'tse_future'
    }

    static constraints = {
        code unique: true, xmlNodeName: 'CCode'
        date nullable: true, xmlNodeName: 'RecDate', locale: 'en'
        settlementDate nullable: true, xmlNodeName: 'SettlementDate', locale: 'en'
        initialMargin nullable: true, xmlNodeName: 'InitMargin'
        minMargin nullable: true, xmlNodeName: 'MinMargin'
        settlementPrice nullable: true, xmlNodeName: 'SettlementPrice'
        maxBrokerOpenPositions nullable: true, xmlNodeName: 'MaxBrokerOP'
        maxClientOpenPositions nullable: true, xmlNodeName: 'MaxClientOP'
        maxMarketOpenPositions nullable: true, xmlNodeName: 'MaxMarketOP'
        closingPrice nullable: true, xmlNodeName: 'ClosingPrice'
        adjustedClosingPrice nullable: true, xmlNodeName: 'AdjPrice'
        dayClosingPrice nullable: true, xmlNodeName: 'EditedPrice'
        beginDate nullable: true, xmlNodeName: 'BeginDate', locale: 'en'
        endDate nullable: true, xmlNodeName: 'EndDate', locale: 'en'
        quantity nullable: true, xmlNodeName: 'Quantity'
        adjustedQuantity nullable: true, xmlNodeName: 'AdjQuantity'
        description nullable: true, xmlNodeName: 'Desc'
        company nullable: true, xmlNodeName: 'Cisin', fkColumn: 'Code'
        companyCode nullable: true, xmlNodeName: 'Cisin'
        maxOrderCount nullable: true, xmlNodeName: 'MaxOrders'
    }
}
