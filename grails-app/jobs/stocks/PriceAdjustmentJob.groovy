package stocks

import stocks.tse.SymbolPriceAdjustment


class PriceAdjustmentJob {
    def priceAdjustmentService

    static startDelay = 60000
    static timeout = 5000l
    static concurrent = false

    def execute() {

        def undo = true

        if (undo) {
            def priceAdjustment = SymbolPriceAdjustment.createCriteria().list {
                eq('applied', true)
                order('date', ORDER_ASCENDING)
                maxResults(1)
            }?.find()
            if (priceAdjustment)
                priceAdjustmentService.undo(priceAdjustment)
        } else {
            def priceAdjustment = SymbolPriceAdjustment.createCriteria().list {
                eq('applied', false)
                order('date', ORDER_ASCENDING)
                maxResults(1)
            }?.find()
            if (priceAdjustment)
                priceAdjustmentService.apply(priceAdjustment)
        }
    }
}
