package stocks

import stocks.indicators.IndicatorServiceBase
import stocks.indicators.SymbolIndicatorService
import stocks.tse.Symbol
import stocks.tse.SymbolDailyTrade
import stocks.util.ClassResolver


class IndicatorJob {

    static startDelay = 60000
    static timeout = 100l
    static concurrent = false

    def symbolIndicatorService
    def grailsApplication
    def lowLevelDataService

    def execute() {

        grailsApplication.getArtefacts('Service').findAll {
            it.fullName.startsWith("stocks.indicators.symbol.")
        }.each { serviceClass ->
            def service = ClassResolver.loadServiceByName(serviceClass.fullName) as IndicatorServiceBase
            if (service.enabled) {
                service.commonParameters.each { parameter ->
//                    println(serviceClass.fullName)
//                    println(parameter)
//                    def id = lowLevelDataService.executeStoredProcedure('symbol_daily_trade_select_not_indexed',
//                            [
//                                    'class'    : serviceClass.fullName.replace('Service', ''),
//                                    'parameter': parameter.class == ArrayList ? parameter.join(',') : parameter
//                            ])?.first()?.values()?.first()?.toLong()
                    def id = SymbolDailyTrade.executeQuery("select id from SymbolDailyTrade t where not exists (select id from ${serviceClass.fullName.split('\\.').last().replace('Service', '')} i where i.parameter = '${parameter.class == ArrayList ? parameter.join(',') : parameter}' and i.dailyTrade.id = t.id) order by t.date", [max: 1]).find()
                    if (id) {
                        def dailyTrade = SymbolDailyTrade.get(id as Long)
                        symbolIndicatorService.calculateIndicator(dailyTrade, service, parameter)
                    }
                }
            }
        }

//        def dailyTrade = SymbolDailyTrade.createCriteria().list {
//            or {
//                isNull('indicatorsCalculated')
//                eq('indicatorsCalculated', false)
//            }
//            isNotNull('symbol')
//            order('date', ORDER_DESCENDING)
//            maxResults(1)
//        }?.find()
//        if (dailyTrade) {
//            symbolIndicatorService.calculateIndicators(Symbol.get(dailyTrade.symbolId), dailyTrade.date)
//            dailyTrade.indicatorsCalculated = true
//            dailyTrade.save(flush: true)
//        }
    }
}
