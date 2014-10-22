package stocks.tse.event

import stocks.tse.Board
import stocks.tse.Company
import stocks.tse.IndustryGroup
import stocks.tse.IndustrySubgroup
import stocks.tse.Symbol

class SymbolEvent {

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
    Symbol data

    static mapping = {
        table 'tse_symbol_event'
    }

    static constraints = {
        code xmlNodeName: 'InstrumentID'
        internalCode nullable: true, xmlNodeName: 'InsCode'
        shortCode nullable: true, xmlNodeName: 'CValMne'
        name nullable: true, xmlNodeName: 'LVal18'
        company nullable: true, xmlNodeName: 'CIsin', fkColumn: 'Code'
        companyName nullable: true, xmlNodeName: 'LSoc30'
        companyCode nullable: true, xmlNodeName: 'CIsin'
        companySmallCode nullable: true, xmlNodeName: 'CSocCSAC'
        persianCode nullable: true, xmlNodeName: 'LVal18AFC'
        persianName nullable: true, xmlNodeName: 'LVal30'
        nominalValue nullable: true, xmlNodeName: 'QNmVlo'
        stockCount nullable: true, xmlNodeName: 'ZTitad'
        changeDate nullable: true, xmlNodeName: 'DESop', locale: 'en'
        todayChangeType nullable: true, xmlNodeName: 'YOPSJ'
        status nullable: true, inList: ['A', 'I', 'O'], xmlNodeName: 'CGdSVal'
        groupCode nullable: true, xmlNodeName: 'CGrValCot'
        firstTradeDate nullable: true, xmlNodeName: 'DInMar', locale: 'en'
        valueUnit nullable: true, inList: ['0', '1'], xmlNodeName: 'YUniExpP'
        marketCode nullable: true, inList:['NO', 'OL', 'BK', 'BY', 'ID', 'UI'], xmlNodeName: 'YMarNSC'
        board nullable: true, xmlNodeName: 'CComVal', fkColumn: 'Code'
        boardCode nullable: true, xmlNodeName: 'CComVal'
        industryGroup nullable: true, xmlNodeName: 'CSecVal', fkColumn: 'Code'
        industryGroupCode nullable: true, xmlNodeName: 'CSoSecVal'
        industrySubgroup nullable: true, xmlNodeName: 'CSoSecVal', fkColumn: 'Code'
        industrySubgroupCode nullable: true, xmlNodeName: 'CSoSecVal'
        settlementDelay nullable: true, xmlNodeName: 'YDeComp'
        maxAllowedValue nullable: true, xmlNodeName: 'PSaiSMaxOkValMdv'
        minAllowedValue nullable: true, xmlNodeName: 'PSaiSMinOkValMdv'
        baseVolume nullable: true, xmlNodeName: 'BaseVol'
        type nullable: true, inList: ['263', '300', '301', '302', '303', '304', '306', '248', '068', '400', '403', '500'], xmlNodeName: 'YVal'
        minTradableValueUnit nullable: true, xmlNodeName: 'QPasCotFxeVal'
        minTradableStockCount nullable: true, xmlNodeName: 'QQtTranMarVal'
        marketIdentifier nullable: true, inList: [0, 1, 2, 3, 4, 5], xmlNodeName: 'Flow'
        minOrderVolume nullable: true, xmlNodeName: 'QtitMinSaiOmProd'
        maxOrderVolume nullable: true, xmlNodeName: 'QtitMaxSaiOmProd'
        date nullable: true, xmlNodeName: 'DEVen', locale: 'en'
        data nullable: true
    }
}
