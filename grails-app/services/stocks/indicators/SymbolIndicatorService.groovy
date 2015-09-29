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
    def indicatorSeries9Service
    def tradesDataService

    def calculateIndicators(SymbolDailyTrade dailyTrade){
        return

        if(dailyTrade.symbol) {


            def seriesMap = [:]
            AdjustmentHelper.ENABLED_TYPES.each { adjustmentType ->
                seriesMap.put(adjustmentType, tradesDataService.getAllPriceSeriesForIndicators(dailyTrade?.symbol, adjustmentType))
            }


            grailsApplication.getArtefacts('Service').findAll {
                it.fullName.startsWith("stocks.indicators.symbol.")
            }.each { serviceClass ->
                def service = ClassResolver.loadServiceByName(serviceClass.fullName) as IndicatorServiceBase
                if (service.enabled) {
                    service.commonParameters.each { parameter ->
                        calculateIndicator(dailyTrade, service, parameter, seriesMap)
                    }
                }
            }
        }
        try {
            SymbolDailyTrade.executeUpdate("update SymbolDailyTrade s set s.modificationDate = :modificationDate, s.indicatorsCalculated = :indicatorsCalculated where id = :id", [id: dailyTrade.id, modificationDate: new Date(), indicatorsCalculated: true])
        }
        catch (ignored) {
            println("indicator job: [exception] ${ignored.message}")
        }
    }

    def calculateIndicator(SymbolDailyTrade dailyTrade, IndicatorServiceBase serviceClass, parameter, Map<String, List> seriesMap) {

        IndicatorBase.withTransaction {
            def symbol = Symbol.get(dailyTrade.symbolId) ?: Symbol.findByPersianCode(dailyTrade.symbolPersianCode)
            def className = serviceClass.class.canonicalName.substring(0, serviceClass.class.canonicalName.indexOf('Service'))
            def parameterString = parameter.class == ArrayList ? parameter.join(',') : parameter
            def clazz = ClassResolver.loadDomainClassByName(className)
            AdjustmentHelper.ENABLED_TYPES.each { adjustmentType ->
                def value = symbol ? serviceClass.calculate(symbol, parameter, adjustmentType, seriesMap[adjustmentType], dailyTrade.date) : 0
                def indicator = clazz.findBySymbolAndParameterAndAdjustmentTypeAndcalculationDateGreaterThanEquals(symbol, parameterString, adjustmentType, dailyTrade.date.clearTime())
                if (!indicator) {
                    indicator = clazz.newInstance()
                    indicator.parameter = parameterString
                    indicator.symbol = symbol
                    indicator.dayNumber = 1
                    indicator.adjustmentType = adjustmentType
                }
                indicator.value = value == 0 ? null : value
                indicator.calculationDate = dailyTrade.date
                indicator.save(flush: true)

                clazz.executeUpdate("update ${className.split('\\.').last()} i set i.dayNumber = i.dayNumber + 1 where i.symbol.id = ${indicator.symbolId} and i.parameter = '${parameterString}' and i.adjustmentType = '${adjustmentType}' and i.id <> ${indicator.id}")


                indicatorSeries9Service.write(indicator as IndicatorBase)
            }
        }

    }


}
