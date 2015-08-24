package stocks

import grails.converters.JSON
import groovy.time.TimeCategory
import stocks.indicators.IndicatorServiceBase
import stocks.tse.Symbol
import stocks.tse.SymbolDailyTrade
import stocks.util.ClassResolver


class IndicatorJob {
//
    static startDelay = 50000
    static timeout = 100l
    static concurrent = false

    def symbolIndicatorService
    def symbolIndicatorBulkService
    def grailsApplication
    def lowLevelDataService


    def execute() {

//        return

        if (grailsApplication.config.jobsDisabled)
            return

        def startDate = new Date()
        use(TimeCategory) {
            startDate = startDate - 5.days
        }
        def dailyTrade = SymbolDailyTrade.createCriteria().list {
            eq('indicatorsCalculated', false)
            gt('date', startDate)
            order('modificationDate', ORDER_ASCENDING)
            maxResults(1)
        }?.find()

        def count = SymbolDailyTrade.createCriteria().count {
            eq('indicatorsCalculated', false)
            gt('date', startDate)
        }
        if(count > 0)
        log.warn( "remaining indicators:" + count)

        if (dailyTrade) {
            symbolIndicatorService.calculateIndicators(dailyTrade)
        }
    }


}
