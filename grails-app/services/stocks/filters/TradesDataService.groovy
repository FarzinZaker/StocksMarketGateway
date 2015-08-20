package stocks.filters

import groovy.time.TimeCategory
import stocks.tse.Symbol
import stocks.tse.SymbolAdjustedDailyTrade
import stocks.tse.SymbolDailyTrade

class TradesDataService {

    def adjustedPriceSeries9Service
    def indicatorSeries9Service

    List getPriceSeries(Symbol symbol, String adjustmentType, Integer daysCount = 200, Date maxDate = null) {

        maxDate = maxDate?.clearTime()
        SymbolAdjustedDailyTrade.createCriteria().list {
            eq('symbol', symbol)
            eq('adjustmentType', adjustmentType)
            isNotNull('dailySnapshot')
            if (maxDate) {
                lte('dailySnapshot', maxDate)
            }
            order('dailySnapshot', ORDER_DESCENDING)
            maxResults(daysCount)
        }.sort { it.dailySnapshot }
    }

    List getAllPriceSeries(Symbol symbol, String adjustmentType, Date maxDate = null) {
        adjustedPriceSeries9Service.dailyTradeList(symbol.id, null, maxDate, '', adjustmentType)
    }
    List getAllPriceSeriesFromDate(Symbol symbol, String adjustmentType,Date minDate=null, Date maxDate = null) {
        adjustedPriceSeries9Service.dailyTradeList(symbol.id, minDate, maxDate, '', adjustmentType)
    }

    Double getLastIndicatorPrice(symbolId, indicator, parameter, date, adjustmentType) {
        indicatorSeries9Service.lastIndicator(symbolId, indicator, parameter, date, adjustmentType)
    }
    def getLastSymbolPrice(symbolId, date, adjustmentType) {
        adjustedPriceSeries9Service.lastDailyTrade(symbolId,date, adjustmentType)
    }

}
