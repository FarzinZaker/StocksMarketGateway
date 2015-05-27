package stocks

import stocks.tse.AdjustmentHelper
import stocks.tse.SymbolPriceAdjustment


class PriceAdjustmentJob {

    def lowLevelDataService
    def priceAdjustmentService
    def grailsApplication

    static startDelay = 60000
    static timeout = 3600000l
    static concurrent = false

    def execute() {
        if (grailsApplication.config.jobsDisabled)
            return

        def result = lowLevelDataService.executeFunction('SYM_SEL_ADJUSTMENT_CI_B', [:])
        if (result?.size()) {
            println "adjustment: ${result}"
            priceAdjustmentService.apply(AdjustmentHelper.TYPE_CAPITAL_INCREASE_PLUS_BROUGHT, [result[0].symbolId as Long])
        }

    }
}
