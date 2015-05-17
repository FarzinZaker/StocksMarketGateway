package stocks.indicators

import stocks.tse.AdjustmentHelper
import stocks.tse.Symbol
import stocks.tse.SymbolDailyTrade
import stocks.util.ClassResolver
import stocks.util.CollectionHelper

class SymbolIndicatorService {

    def grailsApplication
    def lowLevelDataService

    def calculateIndicator(SymbolDailyTrade dailyTrade, IndicatorServiceBase serviceClass, parameter, Boolean online = false) {
        def symbol = Symbol.get(dailyTrade.symbolId) ?: Symbol.findByPersianCode(dailyTrade.symbolPersianCode)
        def className = serviceClass.class.canonicalName.substring(0, serviceClass.class.canonicalName.indexOf('Service'))
        def parameterString = parameter.class == ArrayList ? parameter.join(',') : parameter
        def clazz = ClassResolver.loadDomainClassByName(className)
        AdjustmentHelper.TYPES.each { adjustmentType ->
            def value = symbol ? serviceClass.calculate(symbol, parameter, adjustmentType, dailyTrade.date) : 0
            def indicator = null
            if (online) {
                indicator = clazz.findBySymbolAndParameterAndAdjustmentTypeAndOnline(symbol, parameterString, adjustmentType, true)
            }
            if (!indicator) {
                indicator = clazz.newInstance()
                indicator.parameter = parameterString
                indicator.symbol = symbol
                indicator.online = online
                indicator.dayNumber = online ? 0 : 1
                indicator.adjustmentType = adjustmentType
            }
            indicator.value = value == 0 ? null : value
            indicator.dailyTrade = dailyTrade
            indicator.calculationDate = dailyTrade.date
            indicator.save(flush: true)
            if (symbol && !online)
                clazz.executeUpdate("update ${className.split('\\.').last()} i set i.dayNumber = i.dayNumber + 1 where i.symbol.id = ${indicator.symbolId} and i.parameter = '${parameterString}' and i.adjustmentType = '${adjustmentType}' and i.id != ${indicator.id}")
        }

    }

    def bulkCalculateIndicator(Symbol symbol, IndicatorServiceBase serviceClass, parameter) {

        def className = serviceClass.class.canonicalName.substring(0, serviceClass.class.canonicalName.indexOf('Service'))
        def clazz = ClassResolver.loadDomainClassByName(className)
        def parameterString = parameter.class == ArrayList ? parameter.join(',') : parameter
        AdjustmentHelper.TYPES.each { adjustmentType ->
            def value = symbol ? serviceClass.bulkCalculate(symbol, parameter, adjustmentType) : [series: [], indicators: []]

            if (value.indicators?.size())
                value.indicators = CollectionHelper.moveZeroesToFirst(value.indicators as List)

            def dailyTrades = value.series.reverse()
            def indicatorValues = value.indicators

            def loopCount = [dailyTrades.size(), indicatorValues.size()].min()
            for (def i = 1; i < loopCount; i++) {

                def indicator = clazz.newInstance()
                indicator.dailyTrade = dailyTrades[loopCount - i]
                indicator.parameter = parameterString
                indicator.value = indicatorValues[loopCount - i] == 0 ? null : indicatorValues[loopCount - i]
                indicator.symbol = symbol
                indicator.dayNumber = 1
                indicator.calculationDate = dailyTrades[loopCount - i].dailySnapshot
                indicator.online = false
                indicator.save(flush: i == loopCount - 1)

            }
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
