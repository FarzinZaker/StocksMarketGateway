package stocks

import stocks.tse.AdjustmentHelper
import stocks.tse.SymbolDailyTrade


class PriceTimeSeriesAdjustmentJob {

    def priceSeriesAdjustmentService

    def cronExpression = "0 0 2 * * ?"

    def execute() {
        def idList = SymbolDailyTrade.createCriteria().list{
            not {
                like('symbolPersianCode', '%تسه')
            }
            projections {
                symbol {
                    distinct('id')
                }
            }
        }

        for(def i = 0; i < idList.size(); i++)
            priceSeriesAdjustmentService.apply(AdjustmentHelper.TYPE_CAPITAL_INCREASE_PLUS_BROUGHT, [idList[i]])

    }
}
