package stocks.indicators

import eu.verdelhan.ta4j.TADecimal
import stocks.tse.Symbol
import stocks.tse.SymbolDailyTrade
import stocks.util.ClassResolver

class SymbolIndicatorService {

    def grailsApplication

    def calculateIndicators(Symbol symbol, Date calculationDate = new Date()) {
        grailsApplication.getArtefacts('Service').findAll {
            it.fullName.startsWith("stocks.indicators.symbol.")
        }.each {
            def service = ClassResolver.loadServiceByName(it.fullName) as IndicatorServiceBase
            service.commonParameters.each { parameter ->
                def value = service.calculate(symbol, parameter, calculationDate)
                if (value != null) {
                    def domain = grailsApplication.getDomainClass(it.fullName.replace('Service', '')).clazz
                    def object = domain.newInstance() as IndicatorBase
                    object.symbol = symbol
                    object.calculationDate = calculationDate
                    object.parameter = parameter
                    object.value = value
                    object.save(flush: true)
                }
            }
        }
    }
}
