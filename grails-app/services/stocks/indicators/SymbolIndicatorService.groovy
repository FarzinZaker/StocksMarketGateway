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
        def symbol = Symbol.get(dailyTrade.symbolId) ?: Symbol.findByPersianCode(dailyTrade.symbolPersianCode)
//        if (!symbol) {
//            symbol = Symbol.findByPersianCode(dailyTrade.symbolPersianCode)
//            if (symbol) {
//                dailyTrade.symbol = symbol
//                dailyTrade.save(flush: true)
//            }
//        }
        def value = symbol ? serviceClass.calculate(symbol, parameter, dailyTrade.date) : 0
        def className = serviceClass.class.canonicalName.substring(0, serviceClass.class.canonicalName.indexOf('Service'))
        def clazz = ClassResolver.loadDomainClassByName(className)
        def parameterString = parameter.class == ArrayList ? parameter.join(',') : parameter
        def indicator = clazz.newInstance()
        indicator.dailyTrade = dailyTrade
        indicator.parameter = parameterString
        indicator.value = value
        indicator.symbol = dailyTrade.symbol ?: Symbol.findByPersianCode(dailyTrade.symbolPersianCode)
        indicator.dayNumber = 1
        indicator.calculationDate = dailyTrade.date
        indicator.save(flush: true)
        if (symbol)
            clazz.executeUpdate("update ${className.split('\\.').last()} i set i.dayNumber = i.dayNumber + 1 where i.symbol.id = ${indicator.symbolId} and i.parameter = '${parameterString}' and i.id != ${symbol.id}")

    }
}
