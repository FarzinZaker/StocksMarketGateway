package stocks.filters

import stocks.tse.Symbol
import stocks.tse.SymbolAdjustedDailyTrade
import stocks.tse.SymbolDailyTrade

class TradesDataService {
    List<SymbolDailyTrade> getPriceSeries(Symbol symbol, String adjustmentType, Integer daysCount = 200, Date maxDate = null) {

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
