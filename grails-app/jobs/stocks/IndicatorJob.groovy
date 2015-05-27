package stocks

import grails.converters.JSON
import stocks.indicators.IndicatorServiceBase
import stocks.tse.Symbol
import stocks.tse.SymbolDailyTrade
import stocks.util.ClassResolver


class IndicatorJob {
//
    static startDelay = 60000
    static timeout = 100l
    static concurrent = false

    def symbolIndicatorService
    def symbolIndicatorBulkService
    def grailsApplication
    def lowLevelDataService

    //slow
//    def execute() {

//      if (grailsApplication.config.jobsDisabled)
//          return
//        grailsApplication.getArtefacts('Service').findAll {
//            it.fullName.startsWith("stocks.indicators.symbol.")
//        }.each { serviceClass ->
//            def service = ClassResolver.loadServiceByName(serviceClass.fullName) as IndicatorServiceBase
//            if (service.enabled) {
//                service.commonParameters.each { parameter ->
//                    for (def i = 0; i < 50; i++) {
//                        def dailyTrade = SymbolDailyTrade.executeQuery("from SymbolDailyTrade t where not exists (select id from ${serviceClass.fullName.split('\\.').last().replace('Service', '')} i where i.parameter = '${parameter.class == ArrayList ? parameter.join(',') : parameter}' and i.dailyTrade.id = t.id) and t.dailySnapshot is not null order by t.dailySnapshot", [max: 1]).find()
//                        if (dailyTrade) {
//                            symbolIndicatorService.calculateIndicator(dailyTrade, service, parameter)
//                        } else
//                            break
//                    }
//                }
//            }
//        }
//    }

//    bulk
    def execute() {
        if (grailsApplication.config.jobsDisabled)
            return

        println()
        println "-----------------------------------------"
        println "---- calculating indicators started ----"
//        println "-----------------------------------------"
        println()
        def symbol = findNextSymbol(getLastState())
        println "--------- ${symbol.persianName} ---------"
        if(symbol) {
            symbolIndicatorBulkService.bulkCalculateIndicator(symbol)
            logState(symbol?.id)
        }
        else{
            println()
//            println "-----------------------------------------"
            println "---- calculating indicators finished ----"
            println "-----------------------------------------"
            println()
        }
    }

    def findNextSymbol(Long minId) {
        Symbol.executeQuery("from Symbol s where exists (from SymbolDailyTrade t where t.symbol.id = s.id) and not exists (from IndicatorBase i where i.symbol.id = s.id and i.online = false) and s.id > :id", [id: minId, max: 1]).find()
    }

    def logState(Long symbolId) {
        def data = [symbolId: symbolId]
        def serviceName = 'indicator-bulk-calculate'
        DataServiceState.executeUpdate("update DataServiceState s set s.isLastState = false where s.serviceName = :serviceName", [serviceName: serviceName])

        DataServiceState state = new DataServiceState()
        state.serviceName = serviceName
        state.data = data as JSON
        state.save(flush: true)
    }

    Long getLastState() {
        def serviceName = 'indicator-bulk-calculate'
        def data = DataServiceState.findByServiceNameAndIsLastState(serviceName, true)?.data
        data ? JSON.parse(data)?.symbolId ?: 0 : 0
    }
}
