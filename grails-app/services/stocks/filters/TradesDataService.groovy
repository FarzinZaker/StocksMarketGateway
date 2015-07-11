package stocks.filters

import groovy.time.TimeCategory
import stocks.tse.Symbol
import stocks.tse.SymbolAdjustedDailyTrade
import stocks.tse.SymbolDailyTrade

class TradesDataService {

    def adjustedPriceSeriesService
    List getPriceSeries(Symbol symbol, String adjustmentType, Integer daysCount = 200, Date maxDate = null) {

//        if(!maxDate)
//            maxDate = new Date()
//        def startDate = maxDate
//        use(TimeCategory){
//            startDate = startDate - daysCount.days
//        }
//        adjustedPriceSeriesService.dailyTradeList(symbol.id, startDate, maxDate, '1d', adjustmentType)

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
}
