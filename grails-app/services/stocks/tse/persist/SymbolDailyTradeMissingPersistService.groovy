package stocks.tse.persist

import fi.joensuu.joyds1.calendar.JalaliCalendar
import stocks.tse.AdjustmentHelper
import stocks.tse.SymbolAdjustedDailyTrade
import stocks.tse.SymbolDailyTrade
import stocks.tse.TSEPersistService
import stocks.tse.event.SymbolDailyTradeEvent

class SymbolDailyTradeMissingPersistService extends TSEPersistService<SymbolDailyTrade, SymbolDailyTradeEvent> {

    @Override
    protected getSampleObject() {
        return new SymbolDailyTrade()
    }

    @Override
    protected List<String> getPropertyExcludeList(){
        ['oldClosingPrice', 'indicatorsCalculated']
    }

    @Override
    protected void beforeCreate(SymbolDailyTradeEvent event) {
    }

    @Override
    protected void afterCreate(SymbolDailyTradeEvent event, SymbolDailyTrade data) {
        def date = data.date
        date = date.clearTime()

        AdjustmentHelper.TYPES.each {type ->
            def adjustedDailyTrade = SymbolAdjustedDailyTrade.findBySymbolAndAdjustmentTypeAndDate(data.symbol, type, data.date.clearTime())
            if(!adjustedDailyTrade) {
                adjustedDailyTrade = new SymbolAdjustedDailyTrade()
                adjustedDailyTrade.symbol = data.symbol
                adjustedDailyTrade.symbolInternalCode = data.symbolInternalCode
                adjustedDailyTrade.symbolPersianCode = data.symbolPersianCode
                adjustedDailyTrade.symbolPersianName = data.symbolPersianName
                adjustedDailyTrade.adjustmentType = type
                adjustedDailyTrade.date = data.date.clearTime()
            }
            adjustedDailyTrade.closingPrice = data.closingPrice
            adjustedDailyTrade.creationDate = new Date()
            adjustedDailyTrade.dailyTrade = data
            adjustedDailyTrade.firstTradePrice = data.firstTradePrice
            adjustedDailyTrade.lastTradePrice = data.lastTradePrice
            adjustedDailyTrade.maxPrice = data.maxPrice
            adjustedDailyTrade.minPrice = data.minPrice
            adjustedDailyTrade.modificationDate = new Date()
            adjustedDailyTrade.priceChange = data.priceChange
            adjustedDailyTrade.totalTradeCount = data.totalTradeCount
            adjustedDailyTrade.totalTradeValue = data.totalTradeValue
            adjustedDailyTrade.totalTradeVolume = data.totalTradeVolume
            adjustedDailyTrade.yesterdayPrice = data.yesterdayPrice

            SymbolDailyTrade.withTransaction {
                adjustedDailyTrade.save(flush: true)
            }
        }
    }

    @Override
    protected void beforeUpdate(SymbolDailyTradeEvent event, SymbolDailyTrade data) {
        false
    }

    @Override
    protected void afterUpdate(SymbolDailyTradeEvent event, SymbolDailyTrade data) {

    }
}
