package stocks

import stocks.indicators.IndicatorServiceBase
import stocks.indicators.SymbolIndicatorService
import stocks.tse.Symbol
import stocks.tse.SymbolDailyTrade
import stocks.util.ClassResolver


class IndicatorJob {
//
    static startDelay = 60000
    static timeout = 100l
    static concurrent = false

    def symbolIndicatorService
    def grailsApplication
    def lowLevelDataService

    //slow
//    def execute() {
//
//        grailsApplication.getArtefacts('Service').findAll {
//            it.fullName.startsWith("stocks.indicators.symbol.")
//        }.each { serviceClass ->
//            def service = ClassResolver.loadServiceByName(serviceClass.fullName) as IndicatorServiceBase
//            if (service.enabled) {
//                service.commonParameters.each { parameter ->
//                    50.times {
//                        def dailyTrade = SymbolDailyTrade.executeQuery("from SymbolDailyTrade t where not exists (select id from ${serviceClass.fullName.split('\\.').last().replace('Service', '')} i where i.parameter = '${parameter.class == ArrayList ? parameter.join(',') : parameter}' and i.dailyTrade.id = t.id) order by t.date", [max: 1]).find()
//                        if (dailyTrade) {
//                            symbolIndicatorService.calculateIndicator(dailyTrade, service, parameter)
//                        }
//                    }
//                }
//            }
//        }
//    }

    //bulk
    def execute() {
        grailsApplication.getArtefacts('Service').findAll {
            it.fullName.startsWith("stocks.indicators.symbol.")
        }.each { serviceClass ->
            def service = ClassResolver.loadServiceByName(serviceClass.fullName) as IndicatorServiceBase
            if (service.enabled) {
                service.commonParameters.each { parameter ->
                    def symbol = findNextSymbol(serviceClass, parameter)
                    while (symbol) {
                        symbolIndicatorService.bulkCalculateIndicator(symbol, service, parameter)
                        symbol = findNextSymbol(serviceClass, parameter)
                    }
                }
            }
        }
    }

    def findNextSymbol(serviceClass, parameter){
        Symbol.executeQuery("from Symbol s where exists (select id from SymbolDailyTrade t where t.symbol.id = s.id) and not exists (select id from ${serviceClass.fullName.split('\\.').last().replace('Service', '')} i where i.parameter = '${parameter.class == ArrayList ? parameter.join(',') : parameter}' and i.symbol.id = s.id)", [max: 1]).find()
    }
}
