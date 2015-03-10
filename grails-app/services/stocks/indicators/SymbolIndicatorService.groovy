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
            clazz.executeUpdate("update ${className.split('\\.').last()} i set i.dayNumber = i.dayNumber + 1 where i.symbol.id = ${indicator.symbolId} and i.parameter = '${parameterString}' and i.id != ${indicator.id}")

    }

    def bulkCalculateIndicator(Symbol symbol, IndicatorServiceBase serviceClass, parameter) {
        def value = symbol ? serviceClass.bulkCalculate(symbol, parameter) : [series: [], indicators: []]

        def className = serviceClass.class.canonicalName.substring(0, serviceClass.class.canonicalName.indexOf('Service'))
        def clazz = ClassResolver.loadDomainClassByName(className)
        def parameterString = parameter.class == ArrayList ? parameter.join(',') : parameter

        def dailyTrades = value.series.reverse()
        def indicatorValues = value.indicators

        def loopCount = [dailyTrades.size(), indicatorValues.size()].min()
        for (def i = 0; i < loopCount; i++) {

            def indicator = clazz.newInstance()
            indicator.dailyTrade = dailyTrades[i]
            indicator.parameter = parameterString
            indicator.value = indicatorValues[i]
            indicator.symbol = symbol
            indicator.dayNumber = i + 1
            indicator.calculationDate = dailyTrades[i].dailySnapshot
            indicator.save(flush: i == loopCount - 1)

        }

    }
}
