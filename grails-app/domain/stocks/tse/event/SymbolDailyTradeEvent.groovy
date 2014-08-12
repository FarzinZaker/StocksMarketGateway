package stocks.tse.event

import stocks.tse.Symbol
import stocks.tse.SymbolDailyTrade

class SymbolDailyTradeEvent {


//    Integer marketIdentifier
    Symbol symbol
    Long symbolInternalCode
    String symbolPersianCode
    String symbolPersianName
    Date date
    Integer totalTradeCount
    Integer totalTradeVolume
    Double totalTradeValue
    Double closingPrice
    Double firstTradePrice
    Double lastTradePrice
    Double priceChange
    Double minPrice
    Double maxPrice
    Double yesterdayPrice
    Boolean isLast

    Date creationDate
    SymbolDailyTrade data

    static mapping = {
        table 'tse_symbol_daily_trade_event'
    }

    static constraints = {
//        marketIdentifier nullable: true, parameterIndex: 0
        symbol nullable: true, xmlNodeName: 'InsCode', fkColumn: 'InternalCode'
        symbolInternalCode nullable: true, xmlNodeName: 'InsCode'
        symbolPersianCode nullable: true, xmlNodeName: 'LVal18AFC'
        date nullable: true, xmlNodeName: 'DEven', locale: 'en', timeXmlNode: 'HEven'
        totalTradeCount nullable: true, xmlNodeName: 'ZTotTran'
        totalTradeVolume nullable: true, xmlNodeName: 'QTotTran5J'
        totalTradeValue nullable: true, xmlNodeName: 'QTotCap'
        symbolPersianName nullable: true, xmlNodeName: 'LVal30'
        closingPrice nullable: true, xmlNodeName: 'PClosing'
        firstTradePrice nullable: true, xmlNodeName: 'PriceFirst'
        lastTradePrice nullable: true, xmlNodeName: 'PDrCotVal'
        priceChange nullable: true, xmlNodeName: 'PriceChange'
        minPrice nullable: true, xmlNodeName: 'PriceMin'
        maxPrice nullable: true, xmlNodeName: 'PriceMax'
        yesterdayPrice nullable: true, xmlNodeName: 'PriceYesterday'
        isLast nullable: true, xmlNodeName: 'Last'
        data nullable: true
    }
}
