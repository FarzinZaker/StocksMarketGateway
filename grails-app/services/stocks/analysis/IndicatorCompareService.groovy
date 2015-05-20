package stocks.analysis

import groovy.time.TimeCategory
import stocks.indicators.IndicatorBase
import stocks.indicators.symbol.movingAverage.SMA
import stocks.tse.AdjustmentHelper
import stocks.tse.Symbol
import stocks.tse.SymbolAdjustedDailyTrade
import stocks.tse.SymbolDailyTrade

class IndicatorCompareService {

    Boolean indicatorCrossDownValue(Symbol symbol, Class<IndicatorBase> sourceIndicator, String sourceParameter, Double value, Date date = new Date()) {
        getIndicatorValue(symbol, sourceIndicator, sourceParameter, date, 1) < value &&
                (getIndicatorValue(symbol, sourceIndicator, sourceParameter, date, 2) > value ||
                        getIndicatorValue(symbol, sourceIndicator, sourceParameter, date, 3) > value)
    }

    Boolean indicatorCrossUpValue(Symbol symbol, Class<IndicatorBase> sourceIndicator, String sourceParameter, Double value, Date date = new Date()) {
        getIndicatorValue(symbol, sourceIndicator, sourceParameter, date, 1) > value &&
                (getIndicatorValue(symbol, sourceIndicator, sourceParameter, date, 2) < value ||
                        getIndicatorValue(symbol, sourceIndicator, sourceParameter, date, 3) < value)
    }

    Boolean indicatorCrossDownIndicator(Symbol symbol, Class<IndicatorBase> sourceIndicator, String sourceParameter, Class<IndicatorBase> targetIndicator, String targetParameter, Date date = new Date()) {
        getIndicatorValue(symbol, sourceIndicator, sourceParameter, date, 1) < getIndicatorValue(symbol, targetIndicator, targetParameter, date, 1) &&
                (getIndicatorValue(symbol, sourceIndicator, sourceParameter, date, 2) > getIndicatorValue(symbol, targetIndicator, targetParameter, date, 2) ||
                        getIndicatorValue(symbol, sourceIndicator, sourceParameter, date, 3) > getIndicatorValue(symbol, targetIndicator, targetParameter, date, 3))
    }

    Boolean indicatorCrossUpIndicator(Symbol symbol, Class<IndicatorBase> sourceIndicator, String sourceParameter, Class<IndicatorBase> targetIndicator, String targetParameter, Date date = new Date()) {
        getIndicatorValue(symbol, sourceIndicator, sourceParameter, date, 1) > getIndicatorValue(symbol, targetIndicator, targetParameter, date, 1) &&
                (getIndicatorValue(symbol, sourceIndicator, sourceParameter, date, 2) < getIndicatorValue(symbol, targetIndicator, targetParameter, date, 2) ||
                        getIndicatorValue(symbol, sourceIndicator, sourceParameter, date, 3) < getIndicatorValue(symbol, targetIndicator, targetParameter, date, 3))
    }

    Boolean indicatorCrossDownPrice(Symbol symbol, Class<IndicatorBase> sourceIndicator, String sourceParameter, Date date = new Date()) {
        getIndicatorValue(symbol, sourceIndicator, sourceParameter, date, 1) < getPrice(symbol, date, 1) &&
                (getIndicatorValue(symbol, sourceIndicator, sourceParameter, date, 2) > getPrice(symbol, date, 2) ||
                        getIndicatorValue(symbol, sourceIndicator, sourceParameter, date, 3) > getPrice(symbol, date, 3))
    }

    Boolean indicatorCrossUpPrice(Symbol symbol, Class<IndicatorBase> sourceIndicator, String sourceParameter, Date date = new Date()) {
        getIndicatorValue(symbol, sourceIndicator, sourceParameter, date, 1) > getPrice(symbol, date, 1) &&
                (getIndicatorValue(symbol, sourceIndicator, sourceParameter, date, 2) < getPrice(symbol, date, 2) ||
                        getIndicatorValue(symbol, sourceIndicator, sourceParameter, date, 3) < getPrice(symbol, date, 3))
    }

    Boolean indicatorLowerThanValue(Symbol symbol, Class<IndicatorBase> sourceIndicator, String sourceParameter, Double value, Date date = new Date()) {
        getIndicatorValue(symbol, sourceIndicator, sourceParameter, date, 1) < value
    }

    Boolean indicatorUpperThanValue(Symbol symbol, Class<IndicatorBase> sourceIndicator, String sourceParameter, Double value, Date date = new Date()) {
        getIndicatorValue(symbol, sourceIndicator, sourceParameter, date, 1) > value
    }

    Boolean indicatorLowerThanIndicator(Symbol symbol, Class<IndicatorBase> sourceIndicator, String sourceParameter, Class<IndicatorBase> targetIndicator, String targetParameter, Date date = new Date()) {
        getIndicatorValue(symbol, sourceIndicator, sourceParameter, date, 1) < getIndicatorValue(symbol, targetIndicator, targetParameter, date, 1)
    }

    Boolean indicatorUpperThanIndicator(Symbol symbol, Class<IndicatorBase> sourceIndicator, String sourceParameter, Class<IndicatorBase> targetIndicator, String targetParameter, Date date = new Date()) {
        getIndicatorValue(symbol, sourceIndicator, sourceParameter, date, 1) > getIndicatorValue(symbol, targetIndicator, targetParameter, date, 1)
    }

    Boolean indicatorLowerThanPrice(Symbol symbol, Class<IndicatorBase> sourceIndicator, String sourceParameter, Date date = new Date()) {
        getIndicatorValue(symbol, sourceIndicator, sourceParameter, date, 1) < getPrice(symbol, date, 1)
    }

    Boolean indicatorUpperThanPrice(Symbol symbol, Class<IndicatorBase> sourceIndicator, String sourceParameter, Date date = new Date()) {
        getIndicatorValue(symbol, sourceIndicator, sourceParameter, date, 1) > getPrice(symbol, date, 1)
    }

    Boolean priceLowerThanValue(Symbol symbol, Double value, Date date = new Date()) {
        getPrice(symbol, date, 1) < value
    }

    Boolean priceUpperThanValue(Symbol symbol, Double value, Date date = new Date()) {
        getPrice(symbol, date, 1) > value
    }

    Boolean volumeLowerThanValue(Symbol symbol, Double value, Date date = new Date()) {
        getVolume(symbol, date, 1) < value
    }

    Boolean volumeUpperThanValue(Symbol symbol, Double value, Date date = new Date()) {
        getVolume(symbol, date, 1) > value
    }

    Boolean priceNegativeChangeCompareToFirstPriceGreaterThan(Symbol symbol, Double percent, Date date = new Date()) {
        def dailyTrade = getDailyTrade(symbol, date, 1)
        (dailyTrade.firstTradePrice - dailyTrade.closingPrice) / dailyTrade.firstTradePrice >= percent
    }

    Boolean priceNegativeChangeCompareToPreviousDayGreaterThan(Symbol symbol, Double percent, Date date = new Date()) {
        def dailyTrade = getDailyTrade(symbol, date, 1)
        (dailyTrade.yesterdayPrice - dailyTrade.closingPrice) / dailyTrade.yesterdayPrice >= percent
    }

    Boolean pricePositiveChangeCompareToFirstPriceGreaterThan(Symbol symbol, Double percent, Date date = new Date()) {
        def dailyTrade = getDailyTrade(symbol, date, 1)
        (dailyTrade.closingPrice - dailyTrade.firstTradePrice) / dailyTrade.firstTradePrice >= percent
    }

    Boolean pricePositiveChangeCompareToPreviousDayGreaterThan(Symbol symbol, Double percent, Date date = new Date()) {
        def dailyTrade = getDailyTrade(symbol, date, 1)
        (dailyTrade.closingPrice - dailyTrade.yesterdayPrice) / dailyTrade.yesterdayPrice >= percent
    }

    Boolean volumeNegativeChangeCompareToAverageGreaterThan(Symbol symbol, Double percent, Integer days, Date date = new Date()) {
        1 - getVolume(symbol, date, 1) / getAverageVolume(symbol, days, date) >= percent
    }

    Boolean volumePositiveChangeCompareToAverageGreaterThan(Symbol symbol, Double percent, Integer days, Date date = new Date()) {
        getVolume(symbol, date, 1) / getAverageVolume(symbol, days, date) - 1 >= percent
    }

    Double getIndicatorValue(Symbol symbol, Class<IndicatorBase> indicator, String parameter, Date date, Integer index) {
        def list = indicator.createCriteria().list {
            eq('symbol', symbol)
            eq('adjustmentType', AdjustmentHelper.defaultType)
            eq('parameter', parameter)
            lte('calculationDate', date)
            order('calculationDate', ORDER_DESCENDING)
            maxResults(index)
        }
        list?.isEmpty() ? 0 : (list?.last()?.value ?: 0)
    }

    Double getPrice(Symbol symbol, Date date, Integer index) {
        def list = SymbolAdjustedDailyTrade.createCriteria().list {
            eq('symbol', symbol)
            eq('adjustmentType', AdjustmentHelper.defaultType)
            lte('date', date)
            order('date', ORDER_DESCENDING)
            maxResults(index)
        }
        list?.isEmpty() ? 0 : (list?.last()?.closingPrice ?: 0)
    }

    Double getVolume(Symbol symbol, Date date, Integer index) {
        def list = SymbolAdjustedDailyTrade.createCriteria().list {
            eq('symbol', symbol)
            eq('adjustmentType', AdjustmentHelper.defaultType)
            lte('date', date)
            order('date', ORDER_DESCENDING)
            maxResults(index)
        }
        list?.isEmpty() ? 0 : (list?.last()?.totalTradeVolume ?: 0)
    }

    Double getAverageVolume(Symbol symbol, dayCount, Date date) {
        def startDay
        use(TimeCategory) {
            date = date - 1.days
            startDay = date - dayCount.days
        }
        SymbolAdjustedDailyTrade.createCriteria().list {
            eq('symbol', symbol)
            eq('adjustmentType', AdjustmentHelper.defaultType)
            lte('date', date)
            projections {
                avg('totalTradeVolume')
            }
        }?.find() ?: 0
    }

    SymbolAdjustedDailyTrade getDailyTrade(Symbol symbol, Date date, Integer index) {
        def list = SymbolAdjustedDailyTrade.createCriteria().list {
            eq('symbol', symbol)
            eq('adjustmentType', AdjustmentHelper.defaultType)
            lte('date', date)
            order('date', ORDER_DESCENDING)
            maxResults(index)
        }
        list?.isEmpty() ? null : list?.last()
    }
}
