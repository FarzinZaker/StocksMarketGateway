package stocks.filters

import groovy.time.TimeCategory
import stocks.tse.Symbol
import stocks.tse.SymbolAdjustedDailyTrade
import stocks.tse.SymbolDailyTrade

class TradesDataService {

    def adjustedPriceSeries9Service
    def indicatorSeries9Service

    List getAllPriceSeries(Symbol symbol, String adjustmentType, Date maxDate = null) {
        adjustedPriceSeries9Service.dailyTradeList(symbol.id, null, maxDate, '', adjustmentType)
    }
    List getAllPriceSeriesForIndicators(Symbol symbol, String adjustmentType,Date maxDate = null) {

        if(!maxDate)
            maxDate = new Date()

        adjustedPriceSeries9Service.dailyTradeListForIndicators(symbol.id, null, maxDate, '', adjustmentType)
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
