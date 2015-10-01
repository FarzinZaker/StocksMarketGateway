package stocks.tse.persist

import fi.joensuu.joyds1.calendar.JalaliCalendar
import groovy.transform.Synchronized
import stocks.filters.FilterService
import stocks.indicators.IndicatorServiceBase
import stocks.tse.AdjustmentHelper
import stocks.tse.SymbolAdjustedDailyTrade
import stocks.tse.SymbolAdjustmentQueue
import stocks.tse.SymbolDailyTrade
import stocks.tse.TSEPersistService
import stocks.tse.event.SymbolDailyTradeEvent
import stocks.util.ClassResolver

import java.util.logging.Filter

class SymbolDailyTradePersistService extends TSEPersistService<SymbolDailyTrade, SymbolDailyTradeEvent> {
    static transactional = false
    def bulkDataService

    def grailsApplication
    def adjustedPriceSeries9Service

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
        checkForAdjustments(data)
        calculateOnlineIndicators(data)
        updateFilterService(data)
    }

    @Override
    protected void beforeUpdate(SymbolDailyTradeEvent event, SymbolDailyTrade data) {
    }

    @Override
    protected void afterUpdate(SymbolDailyTradeEvent event, SymbolDailyTrade data) {
        saveAdjustedDailyTrades(data)
        checkForAdjustments(data)
        calculateOnlineIndicators(data)
        updateFilterService(data)
    }

    @Synchronized
    private static void updateFilterService(SymbolDailyTrade data) {
        if (!FilterService.lastTradingDate || FilterService.lastTradingDate.clearTime() < data.date.clearTime())
            FilterService.recentlyTradedSymbols = [data.symbol?.id]
        else if (!FilterService.recentlyTradedSymbols.contains(data.symbol?.id))
            FilterService.recentlyTradedSymbols.add(data.symbol?.id)
        FilterService.lastTradingDate = data.date
    }

    def checkForAdjustments(SymbolDailyTrade data) {

        if(data.symbolPersianCode.contains('???'))
            return

        def yesterdayDailyTrade = SymbolDailyTrade.createCriteria().list {
            lt('date', data.date.clearTime())
            order('date', ORDER_DESCENDING)
            maxResults(1)
        }?.find()
        if (yesterdayDailyTrade && data.yesterdayPrice != yesterdayDailyTrade.closingPrice) {
            def symbolAdjustmentQueue = SymbolAdjustmentQueue.findBySymbolAndAdjustmentType(data.symbol, AdjustmentHelper.TYPE_CAPITAL_INCREASE_PLUS_BROUGHT)
            if (!symbolAdjustmentQueue) {
                symbolAdjustmentQueue = new SymbolAdjustmentQueue()
                symbolAdjustmentQueue.symbol = data.symbol
                symbolAdjustmentQueue.adjustmentType = AdjustmentHelper.TYPE_CAPITAL_INCREASE_PLUS_BROUGHT
                bulkDataService.save(symbolAdjustmentQueue)
            } else {
                if (symbolAdjustmentQueue.applied) {
                    symbolAdjustmentQueue.applied = false
                    bulkDataService.save(symbolAdjustmentQueue)
                }
            }
        }
    }

    def saveAdjustedDailyTrades(SymbolDailyTrade data) {

        adjustedPriceSeries9Service.write([data], AdjustmentHelper.ENABLED_TYPES)

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

            bulkDataService.save(adjustedDailyTrade)
        }
    }

    def calculateOnlineIndicators(SymbolDailyTrade dailyTrade) {
        SymbolDailyTradeEvent event
        SymbolDailyTradeEvent.withTransaction {
            event = SymbolDailyTradeEvent.createCriteria().list {
                eq('data', dailyTrade)
                order('creationDate', ORDER_DESCENDING)
                maxResults(1)
            }?.find()
        }
        if (!event || !dataIsChanged(dailyTrade, event)) {
            SymbolDailyTrade.withTransaction {
                def dt = SymbolDailyTrade.get(dailyTrade.id)
                dt.indicatorsCalculated = false
                dt.save(flush: true)
            }
            println('new daily trade scheduled for indicators: ' + dailyTrade?.id)
        } else {
//            println('daily trade skipped')
        }
    }

    def dataIsChanged(SymbolDailyTrade data, SymbolDailyTradeEvent event) {
        if (data.closingPrice != event.closingPrice
                || data.lastTradePrice != event.lastTradePrice
                || data.firstTradePrice != event.firstTradePrice
                || data.minPrice != event.minPrice
                || data.maxPrice != event.maxPrice)
            return false
        return true
    }
}
