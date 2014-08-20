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
        code unique: true
        date nullable: true
        settlementDate nullable: true
        initialMargin nullable: true
        minMargin nullable: true
        settlementPrice nullable: true
        maxBrokerOpenPositions nullable: true
        maxClientOpenPositions nullable: true
        maxMarketOpenPositions nullable: true
        closingPrice nullable: true
        adjustedClosingPrice nullable: true
        dayClosingPrice nullable: true
        beginDate nullable: true
        endDate nullable: true
        quantity nullable: true
        adjustedQuantity nullable: true
        description nullable: true
        company nullable: true
        companyCode nullable: true
        maxOrderCount nullable: true
    }
}
