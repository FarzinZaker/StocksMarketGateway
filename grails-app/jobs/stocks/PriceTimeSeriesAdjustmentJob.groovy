package stocks

import stocks.tse.AdjustmentHelper
import stocks.tse.SymbolDailyTrade


class PriceTimeSeriesAdjustmentJob {

    def priceSeriesAdjustmentService

    static startDelay = 60000
    static timeout = 100l
//    def cronExpression = "0 0 1 * * ?"
    static concurrent = false

    def execute() {
return
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
