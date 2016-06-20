package stocks

import grails.converters.JSON
import grails.util.Environment
import stocks.tse.AdjustmentHelper
import stocks.tse.SymbolAdjustmentQueue
import stocks.tse.SymbolDailyTrade


class PriceTimeSeriesAdjustmentJob {

    static startDelay = 60000
    static timeout = 100l
    static concurrent = false

    def priceSeriesAdjustmentService
    def grailsApplication


    def execute() {

//        if (grailsApplication.config.jobsDisabled)
//            return

//        if (Environment.current == Environment.DEVELOPMENT)
//            return

        def count = SymbolAdjustmentQueue.countByApplied(false)
        if (count > 0) {
            log.error "[9] remaining adjustments: ${count}"

            def symbolAdjustmentQueue = SymbolAdjustmentQueue.createCriteria().list {
                eq('applied', false)
                order('lastUpdated', ORDER_ASCENDING)
                maxResults(1)
            }?.find()
            if (symbolAdjustmentQueue) {
                priceSeriesAdjustmentService.apply(AdjustmentHelper.TYPE_CAPITAL_INCREASE_PLUS_BROUGHT, [symbolAdjustmentQueue.symbolId])
                symbolAdjustmentQueue.applied = true
                symbolAdjustmentQueue.save(flush: true)
            }
        }
    }

}
