package stocks.filters.symbol.movingAverage
import grails.util.Holders
import org.springframework.web.servlet.i18n.SessionLocaleResolver
import stocks.User
import stocks.filters.FilterServiceBase
import stocks.filters.IncludeFilterService
import stocks.filters.Operators
import stocks.indicators.symbol.movingAverage.WMA
import stocks.tse.Symbol
import stocks.util.ClassResolver

class WMAFilterService implements IncludeFilterService {

    def tradesDataService
    def lowLevelDataService
    def messageSource
    SessionLocaleResolver localeResolver
    def indicatorCompareService

    @Override
    Map getEnabled() {
        [
                screener: false,
                backTest: false
        ]
    }

    @Override
    ArrayList<String> getOperators() {

        [
                Operators.UPPER_THAN,
                Operators.LOWER_THAN,
                Operators.CROSSING_TO_UP,
                Operators.CROSSING_TO_DOWN
        ]
    }

    @Override
    String getFilterParamsTemplate() {
        'parameters'
    }

    @Override
    def getFilterParamsModel() {
        [value: 7]
    }

    @Override
    String getValueTemplate(String operator) {
        'select'
    }

    @Override
    def getValueModel(User user, String operator) {
        [
                list: Holders.grailsApplication.getArtefacts('Service').findAll {
                    it.packageName == 'stocks.filters.symbol.movingAverage'
                }.findAll {
                    (ClassResolver.loadServiceByName(it.fullName) as FilterServiceBase).enabled
                }.collect {
                    [
                            text : it.name.replace('Filter', ''),
                            value: it.fullName
                    ]
                }
        ]
    }

    @Override
    String[] formatQueryValue(Object value, String operator) {
        def parameter = value.first()[1]
        if (parameter && parameter != '')
            ["${value.first()[0].split('\\.').last().replace('FilterService', '')} (${parameter})"]
        else
            [messageSource.getMessage(value.first()[0].split('\\.').last().replace('FilterService', ''), null, localeResolver.defaultLocale)]
    }

    @Override
    Boolean check(Symbol symbol, String parameter, String operator, Object value, Date date) {
        def targetIndicatorName = value.first()[0].replace('FilterService', '').replace('.filters', '.indicators') as String
        def targetIndicator = targetIndicatorName != 'Price' ? ClassResolver.loadDomainClassByName(targetIndicatorName) : null
        def targetParameter = value.first()[1] as String

        switch (operator) {
            case Operators.UPPER_THAN:
                if (targetIndicator)
                    return indicatorCompareService.indicatorUpperThanIndicator(symbol, WMA, parameter, targetIndicator, targetParameter, date)
                else
                    return indicatorCompareService.indicatorUpperThanPrice(symbol, WMA, parameter, date)
            case Operators.LOWER_THAN:
                if (targetIndicator)
                    return indicatorCompareService.indicatorLowerThanIndicator(symbol, WMA, parameter, targetIndicator, targetParameter, date)
                else
                    return indicatorCompareService.indicatorLowerThanPrice(symbol, WMA, parameter, date)
            case Operators.CROSSING_TO_UP:
                if (targetIndicator)
                    return indicatorCompareService.indicatorCrossUpIndicator(symbol, WMA, parameter, targetIndicator, targetParameter, date)
                else
                    return indicatorCompareService.indicatorCrossUpPrice(symbol, WMA, parameter, date)
            case Operators.CROSSING_TO_DOWN:
                if (targetIndicator)
                    return indicatorCompareService.indicatorCrossDownIndicator(symbol, WMA, parameter, targetIndicator, targetParameter, date)
                else
                    return indicatorCompareService.indicatorCrossDownPrice(symbol, WMA, parameter, date)
        }
        false
    }

    @Override
    List<Long> getIncludeList(String parameter, String operator, Object value) {

        def idList = []
        def targetIndicator = value.first()[0].replace('FilterService', '').replace('.filters', '.indicators')
        def targetParameter = value.first()[1]

        switch (operator) {
            case Operators.UPPER_THAN:
                idList = lowLevelDataService.executeStoredProcedure('indicator_upper_than_indicator_filter', [
                        sourceClass    : WMA.canonicalName,
                        sourceParameter: parameter,
                        targetClass    : targetIndicator,
                        targetParameter: targetParameter
                ])
                break
            case Operators.LOWER_THAN:
                idList = lowLevelDataService.executeStoredProcedure('indicator_lower_than_indicator_filter', [
                        sourceClass    : WMA.canonicalName,
                        sourceParameter: parameter,
                        targetClass    : targetIndicator,
                        targetParameter: targetParameter
                ])
                break
            case Operators.CROSSING_TO_UP:
                idList = lowLevelDataService.executeStoredProcedure('indicator_cross_up_indicator_filter', [
                        sourceClass    : WMA.class.canonicalName,
                        sourceParameter: parameter,
                        targetClass    : targetIndicator,
                        targetParameter: targetParameter
                ])
                break
            case Operators.CROSSING_TO_DOWN:
                idList = lowLevelDataService.executeStoredProcedure('indicator_cross_down_indicator_filter', [
                        sourceClass    : WMA.class.canonicalName,
                        sourceParameter: parameter,
                        targetClass    : targetIndicator,
                        targetParameter: targetParameter
                ])
                break
        }

        idList?.collect {
            it?.values()?.first()?.toLong()
        }
    }
}
