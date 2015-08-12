package stocks.analysis

import groovy.time.TimeCategory
import stocks.indicators.IndicatorBase
import stocks.tse.Symbol

class IndicatorCompareService {

    def adjustedPriceSeriesService

    Boolean indicatorCrossDownValue(Class<IndicatorBase> sourceIndicator, String sourceParameter, Double value, Date date = new Date(), List dailyTrades, List indicators) {
        getIndicatorValue(sourceIndicator, sourceParameter, date, 1, indicators) < value &&
                (getIndicatorValue(sourceIndicator, sourceParameter, date, 2, indicators) > value ||
                        getIndicatorValue(sourceIndicator, sourceParameter, date, 3, indicators) > value)
    }

    Boolean indicatorCrossUpValue(Class<IndicatorBase> sourceIndicator, String sourceParameter, Double value, Date date = new Date(), List dailyTrades, List indicators) {
        getIndicatorValue(sourceIndicator, sourceParameter, date, 1, indicators) > value &&
                (getIndicatorValue(sourceIndicator, sourceParameter, date, 2, indicators) < value ||
                        getIndicatorValue(sourceIndicator, sourceParameter, date, 3, indicators) < value)
    }

    Boolean indicatorCrossDownIndicator(Class<IndicatorBase> sourceIndicator, String sourceParameter, Class<IndicatorBase> targetIndicator, String targetParameter, Date date = new Date(), List dailyTrades, List indicators) {
        getIndicatorValue(sourceIndicator, sourceParameter, date, 1, indicators) < getIndicatorValue(targetIndicator, targetParameter, date, 1, indicators) &&
                (getIndicatorValue(sourceIndicator, sourceParameter, date, 2, indicators) > getIndicatorValue(targetIndicator, targetParameter, date, 2, indicators) ||
                        getIndicatorValue(sourceIndicator, sourceParameter, date, 3, indicators) > getIndicatorValue(targetIndicator, targetParameter, date, 3, indicators))
    }

    Boolean indicatorCrossUpIndicator(Class<IndicatorBase> sourceIndicator, String sourceParameter, Class<IndicatorBase> targetIndicator, String targetParameter, Date date = new Date(), List dailyTrades, List indicators) {
        getIndicatorValue(sourceIndicator, sourceParameter, date, 1, indicators) > getIndicatorValue(targetIndicator, targetParameter, date, 1, indicators) &&
                (getIndicatorValue(sourceIndicator, sourceParameter, date, 2, indicators) < getIndicatorValue(targetIndicator, targetParameter, date, 2, indicators) ||
                        getIndicatorValue(sourceIndicator, sourceParameter, date, 3, indicators) < getIndicatorValue(targetIndicator, targetParameter, date, 3, indicators))
    }

    Boolean indicatorCrossDownPrice(Class<IndicatorBase> sourceIndicator, String sourceParameter, Date date = new Date(), List dailyTrades, List indicators) {
        getIndicatorValue(sourceIndicator, sourceParameter, date, 1, indicators) < getPrice(date, 1, dailyTrades) &&
                (getIndicatorValue(sourceIndicator, sourceParameter, date, 2, indicators) > getPrice(date, 2, dailyTrades) ||
                        getIndicatorValue(sourceIndicator, sourceParameter, date, 3, indicators) > getPrice(date, 3, dailyTrades))
    }

    Boolean indicatorCrossUpPrice(Class<IndicatorBase> sourceIndicator, String sourceParameter, Date date = new Date(), List dailyTrades, List indicators) {
        getIndicatorValue(sourceIndicator, sourceParameter, date, 1, indicators) > getPrice(date, 1, dailyTrades) &&
                (getIndicatorValue(sourceIndicator, sourceParameter, date, 2, indicators) < getPrice(date, 2, dailyTrades) ||
                        getIndicatorValue(sourceIndicator, sourceParameter, date, 3, indicators) < getPrice(date, 3, dailyTrades))
    }

    Boolean indicatorLowerThanValue(Class<IndicatorBase> sourceIndicator, String sourceParameter, Double value, Date date = new Date(), List dailyTrades, List indicators) {
        getIndicatorValue(sourceIndicator, sourceParameter, date, 1, indicators) < value
    }

    Boolean indicatorUpperThanValue(Class<IndicatorBase> sourceIndicator, String sourceParameter, Double value, Date date = new Date(), List dailyTrades, List indicators) {
        getIndicatorValue(sourceIndicator, sourceParameter, date, 1, indicators) > value
    }

    Boolean indicatorLowerThanIndicator(Class<IndicatorBase> sourceIndicator, String sourceParameter, Class<IndicatorBase> targetIndicator, String targetParameter, Date date = new Date(), List dailyTrades, List indicators) {
        getIndicatorValue(sourceIndicator, sourceParameter, date, 1, indicators) < getIndicatorValue(targetIndicator, targetParameter, date, 1, indicators)
    }

    Boolean indicatorUpperThanIndicator(Class<IndicatorBase> sourceIndicator, String sourceParameter, Class<IndicatorBase> targetIndicator, String targetParameter, Date date = new Date(), List dailyTrades, List indicators) {
        getIndicatorValue(sourceIndicator, sourceParameter, date, 1, indicators) > getIndicatorValue(targetIndicator, targetParameter, date, 1, indicators)
    }

    Boolean indicatorLowerThanPrice(Class<IndicatorBase> sourceIndicator, String sourceParameter, Date date = new Date(), List dailyTrades, List indicators) {
        getIndicatorValue(sourceIndicator, sourceParameter, date, 1, indicators) < getPrice(date, 1, indicators)
    }

    Boolean indicatorUpperThanPrice(Class<IndicatorBase> sourceIndicator, String sourceParameter, Date date = new Date(), List dailyTrades, List indicators) {
        getIndicatorValue(sourceIndicator, sourceParameter, date, 1, indicators) > getPrice(date, 1, indicators)
    }

    Boolean priceLowerThanValue(Double value, Date date = new Date(), List dailyTrades, List indicators) {
        getPrice(date, 1, dailyTrades) < value
    }

    Boolean priceUpperThanValue(Double value, Date date = new Date(), List dailyTrades, List indicators) {
        getPrice(date, 1, dailyTrades) > value
    }

    Boolean volumeLowerThanValue(Double value, Date date = new Date(), List dailyTrades, List indicators) {
        getVolume(date, 1, dailyTrades) < value
    }

    Boolean volumeUpperThanValue(Double value, Date date = new Date(), List dailyTrades, List indicators) {
        getVolume(date, 1, dailyTrades) > value
    }

    Boolean priceNegativeChangeCompareToFirstPriceGreaterThan(Double percent, Date date = new Date(), List dailyTrades, List indicators) {
        def dailyTrade = getDailyTrade(date, 1, dailyTrades)
        (dailyTrade.firstTradePrice - dailyTrade.closingPrice) / dailyTrade.firstTradePrice >= percent
    }

    Boolean priceNegativeChangeCompareToPreviousDayGreaterThan(Double percent, Date date = new Date(), List dailyTrades, List indicators) {
        def dailyTrade = getDailyTrade(date, 1, dailyTrades)
        (dailyTrade.yesterdayPrice - dailyTrade.closingPrice) / dailyTrade.yesterdayPrice >= percent
    }

    Boolean pricePositiveChangeCompareToFirstPriceGreaterThan(Double percent, Date date = new Date(), List dailyTrades, List indicators) {
        def dailyTrade = getDailyTrade(date, 1, dailyTrades)
        (dailyTrade.closingPrice - dailyTrade.firstTradePrice) / dailyTrade.firstTradePrice >= percent
    }

    Boolean pricePositiveChangeCompareToPreviousDayGreaterThan(Double percent, Date date = new Date(), List dailyTrades, List indicators) {
        def dailyTrade = getDailyTrade(date, 1, dailyTrades)
        (dailyTrade.closingPrice - dailyTrade.yesterdayPrice) / dailyTrade.yesterdayPrice >= percent
    }

    Boolean volumeNegativeChangeCompareToAverageGreaterThan(Double percent, Integer days, Date date = new Date(), List dailyTrades, List indicators) {
        1 - getVolume(date, 1, dailyTrades) / getAverageVolume(days, date, dailyTrades) >= percent
    }

    Boolean volumePositiveChangeCompareToAverageGreaterThan(Double percent, Integer days, Date date = new Date(), List dailyTrades, List indicators) {
        getVolume(date, 1, dailyTrades) / getAverageVolume(days, date, dailyTrades) - 1 >= percent
    }

    Double getIndicatorValue(Class<IndicatorBase> indicator, String parameter, Date date, Integer index, List indicators) {
        def list = indicators.find { it.clazz == indicator && it.parameter == parameter }.values.findAll { it.date <= date }
        if (list.size() < index)
            return 0
        list.sort { -it.date.time }[index - 1].value as Double
    }

    Double getPrice(Date date, Integer index, List dailyTrades) {
        def list = dailyTrades.findAll { it.date <= date }.collect {
            [date: it.date, value: it.closingPrice]
        }
        if (list.size() < index)
            return 0
        list.sort { -it.date.time }[index - 1].value as Double
    }

    Double getVolume(Date date, Integer index, List dailyTrades) {
        def list = dailyTrades.findAll { it.date <= date }.collect {
            [date: it.date, value: it.totalTradeVolume]
        }
        if (list.size() < index)
            return 0
        list.sort { -it.date.time }[index - 1].value as Double
    }

    Double getAverageVolume(Integer dayCount, Date date, List dailyTrades) {
        def list = dailyTrades.findAll { it.date <= date }.collect {
            [date: it.date, value: it.totalTradeVolume]
        }
        if (list.size() < 1)
            return 0
        def count = list.size() >= dayCount ? dayCount : list.size()
        list.sort { -it.date.time }[0..(count - 1)].sum { it.value } / count
    }

    def getDailyTrade(Date date, Integer index, List dailyTrades) {
        def list = dailyTrades.findAll { it.date <= date }

        if (list.size() < index)
            return [
                    'firstTradePrice' : 1,
                    'lastTradePrice'  : 1,
                    'closingPrice'    : 1,
                    'minPrice'        : 1,
                    'maxPrice'        : 1,
                    'totalTradeCount' : 1,
                    'totalTradeValue' : 1,
                    'totalTradeVolume': 1,
                    'yesterdayPrice'  : 1,
                    'priceChange'     : 1
            ]
        list.sort { -it.date.time }[index - 1]
    }
}
