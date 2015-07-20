package stocks.indicators

import stocks.tse.AdjustmentHelper
import stocks.tse.Symbol
import stocks.tse.SymbolAdjustedDailyTrade
import stocks.tse.SymbolDailyTrade
import stocks.util.ClassResolver

class SymbolIndicatorService {

    static transactional = false

    def grailsApplication
    def lowLevelDataService

    def calculateIndicator(SymbolDailyTrade dailyTrade, IndicatorServiceBase serviceClass, parameter) {

        IndicatorBase.withTransaction {
            def symbol = Symbol.get(dailyTrade.symbolId) ?: Symbol.findByPersianCode(dailyTrade.symbolPersianCode)
            def className = serviceClass.class.canonicalName.substring(0, serviceClass.class.canonicalName.indexOf('Service'))
            def parameterString = parameter.class == ArrayList ? parameter.join(',') : parameter
            def clazz = ClassResolver.loadDomainClassByName(className)
            AdjustmentHelper.ENABLED_TYPES.each { adjustmentType ->
                def value = symbol ? serviceClass.calculate(symbol, parameter, adjustmentType, dailyTrade.date) : 0
                def indicator = clazz.findBySymbolAndParameterAndAdjustmentTypeAndcalculationDateGreaterThanEquals(symbol, parameterString, adjustmentType, dailyTrade.date.clearTime())
                if (!indicator) {
                    indicator = clazz.newInstance()
                    indicator.parameter = parameterString
                    indicator.symbol = symbol
                    indicator.dayNumber = 1
                    indicator.adjustmentType = adjustmentType
                } else
                    clazz.executeUpdate("update ${className.split('\\.').last()} i set i.dayNumber = i.dayNumber + 1 where i.symbol.id = ${indicator.symbolId} and i.parameter = '${parameterString}' and i.adjustmentType = '${adjustmentType}'")
                indicator.value = value == 0 ? null : value
                indicator.calculationDate = dailyTrade.date
                indicator.save(flush: true)
            }
        }

    }


}
