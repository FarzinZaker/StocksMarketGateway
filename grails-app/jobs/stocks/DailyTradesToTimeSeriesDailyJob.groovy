package stocks

import stocks.tse.AdjustmentHelper
import stocks.tse.SymbolAdjustedDailyTrade
import stocks.tse.SymbolDailyTrade


class DailyTradesToTimeSeriesDailyJob {

    def cronExpression = "0 0 23 * * ?"

    def adjustedPriceSeriesService

    def execute() {
//        return

        def list = SymbolAdjustedDailyTrade.createCriteria().list {
            gte('date', new Date().clearTime())
        }
        if (list.size()) {
            adjustedPriceSeriesService.write(list, AdjustmentHelper.TYPES)
        }
    }
}
