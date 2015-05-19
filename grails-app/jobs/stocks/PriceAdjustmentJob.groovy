package stocks

import stocks.tse.AdjustmentHelper
import stocks.tse.SymbolPriceAdjustment


class PriceAdjustmentJob {

    def lowLevelDataService
    def priceAdjustmentService

    static startDelay = 60000
    static timeout = 5000l
    static concurrent = false

    def execute() {
        def result = lowLevelDataService.executeFunction('SYM_SEL_ADJUSTMENT_CI_B', [:])
        if (result?.size()) {
            priceAdjustmentService.apply(AdjustmentHelper.TYPE_CAPITAL_INCREASE_PLUS_BROUGHT, [result[0].symbolId as Long])
        }

    }
}
