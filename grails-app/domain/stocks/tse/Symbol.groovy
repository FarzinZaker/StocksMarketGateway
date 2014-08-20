package stocks.tse

class Symbol {

    String code
    Long internalCode
    String shortCode
    String name
    Company company
    String companyCode
    String companyName
    String companySmallCode
    String persianCode
    String persianName
    Double nominalValue
    Long stockCount
    Date changeDate
    Integer todayChangeType
    String status
    String groupCode
    Date firstTradeDate
    String valueUnit
    String marketCode
    Board board
    String boardCode
    IndustryGroup industryGroup
    String industryGroupCode
    IndustrySubgroup industrySubgroup
    String industrySubgroupCode
    Integer settlementDelay
    Integer maxAllowedValue
    Integer minAllowedValue
    Integer baseVolume
    String type
    Double minTradableValueUnit
    Integer minTradableStockCount
    Integer marketIdentifier
    Integer minOrderVolume
    Integer maxOrderVolume
    Date date

    Date creationDate
    Date modificationDate

    static mapping = {
        table 'tse_symbol'
    }

    static constraints = {
        code unique: true
        internalCode nullable: true
        shortCode nullable: true
        name nullable: true
        company nullable: true
        companyName nullable: true
        companyCode nullable: true
        companySmallCode nullable: true
        persianCode nullable: true
        persianName nullable: true
        nominalValue nullable: true
        stockCount nullable: true
        changeDate nullable: true
        todayChangeType nullable: true
        status nullable: true, inList: ['A', 'I', 'O']
        groupCode nullable: true
        firstTradeDate nullable: true
        valueUnit nullable: true, inList: ['0', '1']
        marketCode nullable: true, inList:['NO', 'OL', 'BK', 'BY', 'ID', 'UI']
        board nullable: true
        boardCode nullable: true
        industryGroup nullable: true
        industryGroupCode nullable: true
        industrySubgroup nullable: true
        industrySubgroupCode nullable: true
        settlementDelay nullable: true
        maxAllowedValue nullable: true
        minAllowedValue nullable: true
        baseVolume nullable: true
        type nullable: true, inList: ['263', '300', '301', '302', '303', '304', '306', '248', '068', '400', '403', '500']
        minTradableValueUnit nullable: true
        minTradableStockCount nullable: true
        marketIdentifier nullable: true, inList: [0, 1, 2, 3, 4, 5]
        minOrderVolume nullable: true
        maxOrderVolume nullable: true
        date nullable: true
    }
}
