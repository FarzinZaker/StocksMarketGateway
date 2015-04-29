package stocks.indicators

import eu.verdelhan.ta4j.TADecimal
import grails.util.Environment
import stocks.tse.Symbol
import stocks.tse.SymbolDailyTrade
import stocks.util.ClassResolver
import stocks.util.CollectionHelper

class SymbolIndicatorService {

    def grailsApplication
    def lowLevelDataService

    def calculateIndicator(SymbolDailyTrade dailyTrade, IndicatorServiceBase serviceClass, parameter, Boolean online = false) {
        def symbol = Symbol.get(dailyTrade.symbolId) ?: Symbol.findByPersianCode(dailyTrade.symbolPersianCode)
        def value = symbol ? serviceClass.calculate(symbol, parameter, dailyTrade.date) : 0
        def className = serviceClass.class.canonicalName.substring(0, serviceClass.class.canonicalName.indexOf('Service'))
        def clazz = ClassResolver.loadDomainClassByName(className)
        def parameterString = parameter.class == ArrayList ? parameter.join(',') : parameter
        def indicator = null
        if (online) {
            indicator = clazz.findBySymbolAndParameterAndOnline(symbol, parameterString, true)
        }
        if(!indicator){
            indicator = clazz.newInstance()
            indicator.parameter = parameterString
            indicator.symbol = symbol
            indicator.online = online
            indicator.dayNumber = online ? 0 : 1
        }
        indicator.value = value
        indicator.dailyTrade = dailyTrade
        indicator.calculationDate = dailyTrade.date
        indicator.save(flush: true)
        if (symbol && !online)
            clazz.executeUpdate("update ${className.split('\\.').last()} i set i.dayNumber = i.dayNumber + 1 where i.symbol.id = ${indicator.symbolId} and i.parameter = '${parameterString}' and i.id != ${indicator.id}")

    }

    def bulkCalculateIndicator(Symbol symbol, IndicatorServiceBase serviceClass, parameter) {
        def value = symbol ? serviceClass.bulkCalculate(symbol, parameter) : [series: [], indicators: []]

        if (value.indicators?.size())
            value.indicators = CollectionHelper.moveZeroesToFirst(value.indicators as List)

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
            indicator.online = false
            indicator.save(flush: i == loopCount - 1)

        }

    }

    def bulkCalculateIndicator(Symbol symbol) {
        grailsApplication.getArtefacts('Service').findAll {
            it.fullName.startsWith("stocks.indicators.symbol.")
        }.sort { it.fullName }.each { serviceClass ->
            def service = ClassResolver.loadServiceByName(serviceClass.fullName) as IndicatorServiceBase
            if (service.enabled) {
                service.commonParameters.each { parameter ->
                    bulkCalculateIndicator(symbol, service, parameter)
                }
            }
        }
    }

    def recalculateIndicators(Symbol symbol) {
        IndicatorBase.executeUpdate("delete IndicatorBase i where i.symbol.id = :symbolId", [symbolId: symbol.id])
        bulkCalculateIndicator(symbol)
    }

}
