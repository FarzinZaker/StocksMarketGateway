package stocks.analysis

import groovy.time.TimeCategory
import stocks.indicators.IndicatorBase
import stocks.tse.Symbol

class IndicatorCompareService {

    def adjustedPriceSeriesService
    def indicatorSeriesService

    Boolean indicatorCrossDownValue(Symbol symbol, Class<IndicatorBase> sourceIndicator, String sourceParameter, Double value, Date date = new Date(), String adjustmentType) {
        getIndicatorValue(symbol, sourceIndicator, sourceParameter, date, 1, adjustmentType) < value &&
                (getIndicatorValue(symbol, sourceIndicator, sourceParameter, date, 2, adjustmentType) > value ||
                        getIndicatorValue(symbol, sourceIndicator, sourceParameter, date, 3, adjustmentType) > value)
    }

    Boolean indicatorCrossUpValue(Symbol symbol, Class<IndicatorBase> sourceIndicator, String sourceParameter, Double value, Date date = new Date(), String adjustmentType) {
        getIndicatorValue(symbol, sourceIndicator, sourceParameter, date, 1, adjustmentType) > value &&
                (getIndicatorValue(symbol, sourceIndicator, sourceParameter, date, 2, adjustmentType) < value ||
                        getIndicatorValue(symbol, sourceIndicator, sourceParameter, date, 3, adjustmentType) < value)
    }

    Boolean indicatorCrossDownIndicator(Symbol symbol, Class<IndicatorBase> sourceIndicator, String sourceParameter, Class<IndicatorBase> targetIndicator, String targetParameter, Date date = new Date(), String adjustmentType) {
        getIndicatorValue(symbol, sourceIndicator, sourceParameter, date, 1, adjustmentType) < getIndicatorValue(symbol, targetIndicator, targetParameter, date, 1, adjustmentType) &&
                (getIndicatorValue(symbol, sourceIndicator, sourceParameter, date, 2, adjustmentType) > getIndicatorValue(symbol, targetIndicator, targetParameter, date, 2, adjustmentType) ||
                        getIndicatorValue(symbol, sourceIndicator, sourceParameter, date, 3, adjustmentType) > getIndicatorValue(symbol, targetIndicator, targetParameter, date, 3, adjustmentType))
    }

    Boolean indicatorCrossUpIndicator(Symbol symbol, Class<IndicatorBase> sourceIndicator, String sourceParameter, Class<IndicatorBase> targetIndicator, String targetParameter, Date date = new Date(), String adjustmentType) {
        getIndicatorValue(symbol, sourceIndicator, sourceParameter, date, 1, adjustmentType) > getIndicatorValue(symbol, targetIndicator, targetParameter, date, 1, adjustmentType) &&
                (getIndicatorValue(symbol, sourceIndicator, sourceParameter, date, 2, adjustmentType) < getIndicatorValue(symbol, targetIndicator, targetParameter, date, 2, adjustmentType) ||
                        getIndicatorValue(symbol, sourceIndicator, sourceParameter, date, 3, adjustmentType) < getIndicatorValue(symbol, targetIndicator, targetParameter, date, 3, adjustmentType))
    }

    Boolean indicatorCrossDownPrice(Symbol symbol, Class<IndicatorBase> sourceIndicator, String sourceParameter, Date date = new Date(), String adjustmentType) {
        getIndicatorValue(symbol, sourceIndicator, sourceParameter, date, 1, adjustmentType) < getPrice(symbol, date, 1, adjustmentType) &&
                (getIndicatorValue(symbol, sourceIndicator, sourceParameter, date, 2, adjustmentType) > getPrice(symbol, date, 2, adjustmentType) ||
                        getIndicatorValue(symbol, sourceIndicator, sourceParameter, date, 3, adjustmentType) > getPrice(symbol, date, 3, adjustmentType))
    }

    Boolean indicatorCrossUpPrice(Symbol symbol, Class<IndicatorBase> sourceIndicator, String sourceParameter, Date date = new Date(), String adjustmentType) {
        getIndicatorValue(symbol, sourceIndicator, sourceParameter, date, 1, adjustmentType) > getPrice(symbol, date, 1, adjustmentType) &&
                (getIndicatorValue(symbol, sourceIndicator, sourceParameter, date, 2, adjustmentType) < getPrice(symbol, date, 2, adjustmentType) ||
                        getIndicatorValue(symbol, sourceIndicator, sourceParameter, date, 3, adjustmentType) < getPrice(symbol, date, 3, adjustmentType))
    }

    Boolean indicatorLowerThanValue(Symbol symbol, Class<IndicatorBase> sourceIndicator, String sourceParameter, Double value, Date date = new Date(), String adjustmentType) {
        getIndicatorValue(symbol, sourceIndicator, sourceParameter, date, 1, adjustmentType) < value
    }

    Boolean indicatorUpperThanValue(Symbol symbol, Class<IndicatorBase> sourceIndicator, String sourceParameter, Double value, Date date = new Date(), String adjustmentType) {
        getIndicatorValue(symbol, sourceIndicator, sourceParameter, date, 1, adjustmentType) > value
    }

    Boolean indicatorLowerThanIndicator(Symbol symbol, Class<IndicatorBase> sourceIndicator, String sourceParameter, Class<IndicatorBase> targetIndicator, String targetParameter, Date date = new Date(), String adjustmentType) {
        getIndicatorValue(symbol, sourceIndicator, sourceParameter, date, 1, adjustmentType) < getIndicatorValue(symbol, targetIndicator, targetParameter, date, 1, adjustmentType)
    }

    Boolean indicatorUpperThanIndicator(Symbol symbol, Class<IndicatorBase> sourceIndicator, String sourceParameter, Class<IndicatorBase> targetIndicator, String targetParameter, Date date = new Date(), String adjustmentType) {
        getIndicatorValue(symbol, sourceIndicator, sourceParameter, date, 1, adjustmentType) > getIndicatorValue(symbol, targetIndicator, targetParameter, date, 1, adjustmentType)
    }

    Boolean indicatorLowerThanPrice(Symbol symbol, Class<IndicatorBase> sourceIndicator, String sourceParameter, Date date = new Date(), String adjustmentType) {
        getIndicatorValue(symbol, sourceIndicator, sourceParameter, date, 1, adjustmentType) < getPrice(symbol, date, 1, adjustmentType)
    }

    Boolean indicatorUpperThanPrice(Symbol symbol, Class<IndicatorBase> sourceIndicator, String sourceParameter, Date date = new Date(), String adjustmentType) {
        getIndicatorValue(symbol, sourceIndicator, sourceParameter, date, 1, adjustmentType) > getPrice(symbol, date, 1, adjustmentType)
    }

    Boolean priceLowerThanValue(Symbol symbol, Double value, Date date = new Date(), String adjustmentType) {
        getPrice(symbol, date, 1, adjustmentType) < value
    }

    Boolean priceUpperThanValue(Symbol symbol, Double value, Date date = new Date(), String adjustmentType) {
        getPrice(symbol, date, 1, adjustmentType) > value
    }

    Boolean volumeLowerThanValue(Symbol symbol, Double value, Date date = new Date(), String adjustmentType) {
        getVolume(symbol, date, 1, adjustmentType) < value
    }

    Boolean volumeUpperThanValue(Symbol symbol, Double value, Date date = new Date(), String adjustmentType) {
        getVolume(symbol, date, 1, adjustmentType) > value
    }

    Boolean priceNegativeChangeCompareToFirstPriceGreaterThan(Symbol symbol, Double percent, Date date = new Date(), String adjustmentType) {
        def dailyTrade = getDailyTrade(symbol, date, 1, adjustmentType)
        (dailyTrade.firstTradePrice - dailyTrade.closingPrice) / dailyTrade.firstTradePrice >= percent
    }

    Boolean priceNegativeChangeCompareToPreviousDayGreaterThan(Symbol symbol, Double percent, Date date = new Date(), String adjustmentType) {
        def dailyTrade = getDailyTrade(symbol, date, 1, adjustmentType)
        (dailyTrade.yesterdayPrice - dailyTrade.closingPrice) / dailyTrade.yesterdayPrice >= percent
    }

    Boolean pricePositiveChangeCompareToFirstPriceGreaterThan(Symbol symbol, Double percent, Date date = new Date(), String adjustmentType) {
        def dailyTrade = getDailyTrade(symbol, date, 1, adjustmentType)
        (dailyTrade.closingPrice - dailyTrade.firstTradePrice) / dailyTrade.firstTradePrice >= percent
    }

    Boolean pricePositiveChangeCompareToPreviousDayGreaterThan(Symbol symbol, Double percent, Date date = new Date(), String adjustmentType) {
        def dailyTrade = getDailyTrade(symbol, date, 1, adjustmentType)
        (dailyTrade.closingPrice - dailyTrade.yesterdayPrice) / dailyTrade.yesterdayPrice >= percent
    }

    Boolean volumeNegativeChangeCompareToAverageGreaterThan(Symbol symbol, Double percent, Integer days, Date date = new Date(), String adjustmentType) {
        1 - getVolume(symbol, date, 1, adjustmentType) / getAverageVolume(symbol, days, date, adjustmentType) >= percent
    }

    Boolean volumePositiveChangeCompareToAverageGreaterThan(Symbol symbol, Double percent, Integer days, Date date = new Date(), String adjustmentType) {
        getVolume(symbol, date, 1, adjustmentType) / getAverageVolume(symbol, days, date, adjustmentType) - 1 >= percent
    }

    Double getIndicatorValue(Symbol symbol, Class<IndicatorBase> indicator, String parameter, Date date, Integer index, String adjustmentType) {
        def startDate = date
        use(TimeCategory){
            startDate = startDate - (10 * index).days
        }
        def list = indicatorSeriesService.indicatorList(symbol.id, indicator, parameter, startDate, date, '', adjustmentType)
        if (list.size() < index)
            return 0
        list.sort { -it.date.time }[index - 1].value as Double
    }

    Double getPrice(Symbol symbol, Date date, Integer index, String adjustmentType) {
        def startDate = date
        use(TimeCategory){
            startDate = startDate - (10 * index).days
        }
        def list = adjustedPriceSeriesService.closingPriceList(symbol.id, startDate, date, '', adjustmentType)
        if (list.size() < index)
            return 0
        list.sort { -it.date.time }[index - 1].value as Double
    }

    Double getVolume(Symbol symbol, Date date, Integer index, String adjustmentType) {
        def startDate = date
        use(TimeCategory){
            startDate = startDate - (10 * index).days
        }
        def list = adjustedPriceSeriesService.totalTradeVolumeList(symbol.id, startDate, date, '', adjustmentType)
        if (list.size() < index)
            return 0
        list.sort { -it.date.time }[index - 1].value as Double
    }

    Double getAverageVolume(Symbol symbol, Integer dayCount, Date date, String adjustmentType) {
        def startDate = date
        use(TimeCategory){
            startDate = startDate - (10 * dayCount).days
        }
        def list = adjustedPriceSeriesService.totalTradeVolumeList(symbol.id, startDate, date, '', adjustmentType)
        if (list.size() < 1)
            return 0
        def count = list.size() >= dayCount ? dayCount : list.size()
        list.sort { -it.date.time }[0..(count - 1)].sum { it.value } / count
    }

    def getDailyTrade(Symbol symbol, Date date, Integer index, String adjustmentType) {
        def startDate = date
        use(TimeCategory){
            startDate = startDate - (10 * index).days
        }
        def list = adjustedPriceSeriesService.dailyTradeList(symbol.id, startDate, date, '', adjustmentType)

        if (list.size() < index)
            return 0
        list.sort { -it.date.time }[index - 1]
    }
}
