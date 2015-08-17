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

        println "remaining indicators:" + (SymbolDailyTrade.createCriteria().count {
            eq('indicatorsCalculated', false)
            gt('date', startDate)
        })

        if (dailyTrade) {
            grailsApplication.getArtefacts('Service').findAll {
                it.fullName.startsWith("stocks.indicators.symbol.")
            }.each { serviceClass ->
                def service = ClassResolver.loadServiceByName(serviceClass.fullName) as IndicatorServiceBase
                if (service.enabled) {
                    service.commonParameters.each { parameter ->
                        symbolIndicatorService.calculateIndicator(dailyTrade, service, parameter)
                    }
                }
            }
            try {
                SymbolDailyTrade.executeUpdate("update SymbolDailyTrade s set s.modificationDate = :modificationDate, s.indicatorsCalculated = :indicatorsCalculated where id = :id", [id: dailyTrade.id, modificationDate: new Date(), indicatorsCalculated: true])
//                dailyTrade = SymbolDailyTrade.get(dailyTrade.id)
//                dailyTrade.modificationDate = new Date()
//                dailyTrade.indicatorsCalculated = true
//                dailyTrade.merge(flush: true)
            }
            catch (ignored) {
                println("indicator job: [exception] ${ignored.message}")
            }
        }
    }


}
