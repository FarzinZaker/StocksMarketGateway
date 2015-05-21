package stocks.indicators

import stocks.tse.AdjustmentHelper
import stocks.tse.Symbol
import stocks.tse.SymbolAdjustedDailyTrade
import stocks.tse.SymbolDailyTrade
import stocks.util.ClassResolver

class SymbolIndicatorService {

    def grailsApplication
    def lowLevelDataService

    def calculateIndicator(SymbolDailyTrade dailyTrade, IndicatorServiceBase serviceClass, parameter, Boolean online = false) {

        def symbol = Symbol.get(dailyTrade.symbolId) ?: Symbol.findByPersianCode(dailyTrade.symbolPersianCode)
        def className = serviceClass.class.canonicalName.substring(0, serviceClass.class.canonicalName.indexOf('Service'))
        def parameterString = parameter.class == ArrayList ? parameter.join(',') : parameter
        def clazz = ClassResolver.loadDomainClassByName(className)
        AdjustmentHelper.ENABLED_TYPES.each { adjustmentType ->
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
            indicator.dailyTrade = SymbolAdjustedDailyTrade.findByDailyTradeAndAdjustmentType(dailyTrade, adjustmentType)
            indicator.calculationDate = dailyTrade.date
            indicator.save(flush: true)
            if (symbol && !online)
                clazz.executeUpdate("update ${className.split('\\.').last()} i set i.dayNumber = i.dayNumber + 1 where i.symbol.id = ${indicator.symbolId} and i.parameter = '${parameterString}' and i.adjustmentType = '${adjustmentType}' and i.id != ${indicator.id}")
        }

    }


}
