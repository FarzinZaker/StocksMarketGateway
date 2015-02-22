package stocks.indicators

import eu.verdelhan.ta4j.TADecimal
import grails.util.Environment
import stocks.tse.Symbol
import stocks.tse.SymbolDailyTrade
import stocks.util.ClassResolver

class SymbolIndicatorService {

    def grailsApplication
    def lowLevelDataService

    def calculateIndicator(SymbolDailyTrade dailyTrade, IndicatorServiceBase serviceClass, parameter) {
        def symbol = Symbol.get(dailyTrade.symbolId)
//        if (!symbol) {
//            symbol = Symbol.findByPersianCode(dailyTrade.symbolPersianCode)
//            if (symbol) {
//                dailyTrade.symbol = symbol
//                dailyTrade.save(flush: true)
//            }
//        }
        def value = symbol ? serviceClass.calculate(symbol, parameter, dailyTrade.date) : 0
        lowLevelDataService.executeStoredProcedure('indicator_insert',
                [
                        daily_trade_id  : dailyTrade.id,
                        parameter       : parameter.class == ArrayList ? parameter.join(',') : parameter,
                        symbol_id       : symbol?.id ?: 0,
                        value           : value,
                        'class'         : serviceClass.class.canonicalName.substring(0, serviceClass.class.canonicalName.indexOf('Service')),
                        calculation_date: dailyTrade.date
                ]
        )
    }

//    def calculateIndicators(Symbol symbol, Date calculationDate = new Date()) {
//        def result = ""
//        grailsApplication.getArtefacts('Service').findAll {
//            it.fullName.startsWith("stocks.indicators.symbol.")
//        }.each {
//            def service = ClassResolver.loadServiceByName(it.fullName) as IndicatorServiceBase
//            service.commonParameters.each { parameter ->
//                def value = service.calculate(symbol, parameter, calculationDate)
//                if (Environment.current == Environment.DEVELOPMENT)
//                    result += "<span style='color:${value == 0 ? 'red' : 'gray'}'>${service.class.simpleName}(${parameter}):${value}</span><br/>"
//                if (value != null) {
//                    def domain = grailsApplication.getDomainClass(it.fullName.replace('Service', '')).clazz
//                    def object = domain.newInstance() as IndicatorBase
//                    object.symbol = symbol
//                    object.calculationDate = calculationDate
//                    object.parameter = parameter
//                    object.value = value
//                    object.save(flush: true)
//                }
//            }
//        }
//        result
//    }
}
