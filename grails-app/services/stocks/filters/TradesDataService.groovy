package stocks.filters

import groovy.time.TimeCategory
import stocks.tse.Symbol
import stocks.tse.SymbolAdjustedDailyTrade
import stocks.tse.SymbolDailyTrade

class TradesDataService {

    def adjustedPriceSeries9Service
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
    List getAllPriceSeries(Symbol symbol, String adjustmentType,Date maxDate = null) {

        if(!maxDate)
            maxDate = new Date()
        def startDate = maxDate
        use(TimeCategory){
            startDate = startDate - 1000
        }
        adjustedPriceSeries9Service.dailyTradeList(symbol.id, startDate, maxDate, '', adjustmentType)
    }
    List getAllPriceSeriesForIndicators(Symbol symbol, String adjustmentType,Date maxDate = null) {

        if(!maxDate)
            maxDate = new Date()
        def startDate = maxDate
        use(TimeCategory){
            startDate = startDate - 1000
        }
        adjustedPriceSeries9Service.dailyTradeListForIndicators(symbol.id, startDate, maxDate, '', adjustmentType)
    }
}
