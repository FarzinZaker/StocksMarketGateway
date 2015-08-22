package stocks.indicators

import stocks.tse.AdjustmentHelper
import stocks.tse.Symbol
import stocks.util.ClassResolver
import stocks.util.CollectionHelper

class SymbolIndicatorBulkService {

    static transactional = false

    def grailsApplication
    def lowLevelDataService
    def bulkDataService
    def indicatorSeries9Service
    def tradesDataService

    def bulkCalculateIndicator(Symbol symbol, IndicatorServiceBase serviceClass, parameter, seriesMap) {

        def className = serviceClass.class.canonicalName.substring(0, serviceClass.class.canonicalName.indexOf('Service'))
        def clazz = ClassResolver.loadDomainClassByName(className)
        def parameterString = parameter.class == ArrayList ? parameter.join(',') : parameter
        AdjustmentHelper.ENABLED_TYPES.each { adjustmentType ->
            def series = seriesMap[adjustmentType]
            def value = symbol ? serviceClass.bulkCalculate(symbol, parameter, adjustmentType, series) : [series: [], indicators: []]

            if (value.indicators?.size())
                value.indicators = CollectionHelper.moveZeroesToFirst(value.indicators as List)

            def dailyTrades = value.series
            def indicatorValues = value.indicators

            def loopCount = [dailyTrades.size(), indicatorValues.size()].min()
            def indicatorList = []
            for (def i = 1; i < loopCount; i++) {

                def indicator = clazz.newInstance()
                indicator.parameter = parameterString
                indicator.value = indicatorValues[loopCount - i] == 0 ? null : indicatorValues[loopCount - i]
                indicator.symbol = symbol
                indicator.dayNumber = 1
                indicator.calculationDate = dailyTrades[loopCount - i].dailySnapshot ?: dailyTrades[loopCount - i].date
                indicator.adjustmentType = adjustmentType
                bulkDataService.save(indicator)
                indicatorList << indicator

            }
            if (indicatorList.size())
                indicatorSeries9Service.write(indicatorList)

        }

    }

    def bulkCalculateIndicator(Symbol symbol) {

        def seriesMap = [:]
        AdjustmentHelper.ENABLED_TYPES.each {adjustmentType ->
            seriesMap.put(adjustmentType, tradesDataService.getAllPriceSeriesForIndicators(symbol, adjustmentType))
        }

        grailsApplication.getArtefacts('Service').findAll {
            it.fullName.startsWith("stocks.indicators.symbol.")
        }.sort { it.fullName }.each { serviceClass ->
            def service = ClassResolver.loadServiceByName(serviceClass.fullName) as IndicatorServiceBase
            if (service.enabled) {
                service.commonParameters.each { parameter ->
                    bulkCalculateIndicator(symbol, service, parameter, seriesMap)
                }
            }
        }
    }
    def bulkCalculateSingleIndicator(Symbol symbol,serviceClass ) {

        def seriesMap = [:]
        AdjustmentHelper.ENABLED_TYPES.each {adjustmentType ->
            seriesMap.put(adjustmentType, tradesDataService.getAllPriceSeries(symbol, adjustmentType))
        }

        def service = ClassResolver.loadServiceByName(serviceClass.name ) as IndicatorServiceBase
        if (service.enabled) {
            service.commonParameters.each { parameter ->
                bulkCalculateIndicator(symbol, service, parameter, seriesMap)
            }
        }

    }

    def recalculateIndicators(Symbol symbol) {
        IndicatorBase.executeUpdate("delete IndicatorBase i where i.symbol.id = :symbolId", [symbolId: symbol.id])
        bulkCalculateIndicator(symbol)
    }
}
