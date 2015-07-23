package stocks.tse.persist

import fi.joensuu.joyds1.calendar.JalaliCalendar
import stocks.indicators.IndicatorServiceBase
import stocks.tse.AdjustmentHelper
import stocks.tse.SymbolAdjustedDailyTrade
import stocks.tse.SymbolDailyTrade
import stocks.tse.TSEPersistService
import stocks.tse.event.SymbolDailyTradeEvent
import stocks.util.ClassResolver

class SymbolDailyTradePersistService extends TSEPersistService<SymbolDailyTrade, SymbolDailyTradeEvent> {
    static transactional = false
    def bulkDataService

    def grailsApplication

    @Override
    protected getSampleObject() {
        new SymbolDailyTrade()
    }

    @Override
    protected List<String> getPropertyExcludeList() {
        ['oldClosingPrice', 'indicatorsCalculated']
    }

    @Override
    protected void beforeCreate(SymbolDailyTradeEvent event) {

    }

    @Override
    protected void afterCreate(SymbolDailyTradeEvent event, SymbolDailyTrade data) {
        saveAdjustedDailyTrades(data)
        calculateOnlineIndicators(data)
    }

    @Override
    protected void beforeUpdate(SymbolDailyTradeEvent event, SymbolDailyTrade data) {
    }

    @Override
    protected void afterUpdate(SymbolDailyTradeEvent event, SymbolDailyTrade data) {
        saveAdjustedDailyTrades(data)
        calculateOnlineIndicators(data)
    }

    def saveAdjustedDailyTrades(SymbolDailyTrade data) {
        def date = data.date
        date = date.clearTime()

        AdjustmentHelper.TYPES.each { type ->
            def adjustedDailyTrade = SymbolAdjustedDailyTrade.findBySymbolAndAdjustmentTypeAndDate(data.symbol, type, data.date.clearTime())
            if (!adjustedDailyTrade) {
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

            adjustedDailyTrade.dailySnapshot = date
            def calendar = Calendar.getInstance() as GregorianCalendar
            calendar.setTime(date)
            def jc = new JalaliCalendar(calendar)
            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY)
                adjustedDailyTrade.weeklySnapshot = date
            if (jc.getDay() == jc.getLastDayOfMonth(jc.getYear(), jc.getMonth()))
                adjustedDailyTrade.monthlySnapshot = date
            bulkDataService.save(adjustedDailyTrade)
//            SymbolDailyTrade.withTransaction {
//                adjustedDailyTrade.save(flush: true)
//            }
            println('adjusted daily trade saved')
        }
    }

    def calculateOnlineIndicators(SymbolDailyTrade dailyTrade) {
        def events
        SymbolDailyTradeEvent.withTransaction {
            events = SymbolDailyTradeEvent.createCriteria().list {
                eq('data', dailyTrade)
                order('creationDate', ORDER_DESCENDING)
                maxResults(2)
            }?.toArray()
        }
        if (!events || events.size() < 2 || !eventsAreEqual(events[0] as SymbolDailyTradeEvent, events[1] as SymbolDailyTradeEvent)) {
            SymbolDailyTrade.withTransaction {
                def dt = SymbolDailyTrade.get(dailyTrade.id)
                dt.indicatorsCalculated = false
                dt.save(flush: true)
            }
            println('new daily trade scheduled for indicators')
        }
    }

    def eventsAreEqual(SymbolDailyTradeEvent ev1, SymbolDailyTradeEvent ev2) {
        if (ev1.closingPrice != ev2.closingPrice
                || ev1.lastTradePrice != ev2.lastTradePrice
                || ev1.firstTradePrice != ev2.firstTradePrice
                || ev1.minPrice != ev2.minPrice
                || ev1.maxPrice != ev2.maxPrice)
            return false
        return true
    }
}
