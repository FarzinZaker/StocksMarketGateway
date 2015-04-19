package stocks.filters.symbol.movingAverage
import grails.util.Holders
import org.springframework.web.servlet.i18n.SessionLocaleResolver
import stocks.User
import stocks.filters.FilterServiceBase
import stocks.filters.IncludeFilterService
import stocks.filters.Operators
import stocks.indicators.symbol.movingAverage.DEMA
import stocks.tse.Symbol
import stocks.util.ClassResolver

class DEMAFilterService implements IncludeFilterService {

    def tradesDataService
    def lowLevelDataService
    def messageSource
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
        [value: 21]
    }

    @Override
    String getValueTemplate(String operator) {
        'select'
    }

    @Override
    def getValueModel(User user, String operator, String place) {
        [
                list: Holders.grailsApplication.getArtefacts('Service').findAll {
                    it.packageName == 'stocks.filters.symbol.movingAverage'
                }.findAll {
                    (ClassResolver.loadServiceByName(it.fullName) as FilterServiceBase).enabled."${place}"
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
            [messageSource.getMessage(value.first()[0].split('\\.').last().replace('FilterService', ''), null, Locale.ENGLISH)]
    }

    @Override
    Boolean check(Symbol symbol, String parameter, String operator, Object value, Date date) {
        def targetIndicatorName = value.first()[0].replace('FilterService', '').replace('.filters', '.indicators') as String
        def targetIndicator = targetIndicatorName != 'Price' ? ClassResolver.loadDomainClassByName(targetIndicatorName) : null
        def targetParameter = value.first()[1] as String

        switch (operator) {
            case Operators.UPPER_THAN:
                if (targetIndicator)
                    return indicatorCompareService.indicatorUpperThanIndicator(symbol, DEMA, parameter, targetIndicator, targetParameter, date)
                else
                    return indicatorCompareService.indicatorUpperThanPrice(symbol, DEMA, parameter, date)
            case Operators.LOWER_THAN:
                if (targetIndicator)
                    return indicatorCompareService.indicatorLowerThanIndicator(symbol, DEMA, parameter, targetIndicator, targetParameter, date)
                else
                    return indicatorCompareService.indicatorLowerThanPrice(symbol, DEMA, parameter, date)
            case Operators.CROSSING_TO_UP:
                if (targetIndicator)
                    return indicatorCompareService.indicatorCrossUpIndicator(symbol, DEMA, parameter, targetIndicator, targetParameter, date)
                else
                    return indicatorCompareService.indicatorCrossUpPrice(symbol, DEMA, parameter, date)
            case Operators.CROSSING_TO_DOWN:
                if (targetIndicator)
                    return indicatorCompareService.indicatorCrossDownIndicator(symbol, DEMA, parameter, targetIndicator, targetParameter, date)
                else
                    return indicatorCompareService.indicatorCrossDownPrice(symbol, DEMA, parameter, date)
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
                idList = lowLevelDataService.executeFunction('IND_UPPER_THAN_IND_FILTER', [
                        sourceClass    : DEMA.canonicalName,
                        sourceParameter: parameter,
                        targetClass    : targetIndicator,
                        targetParameter: targetParameter
                ])
                break
            case Operators.LOWER_THAN:
                idList = lowLevelDataService.executeFunction('IND_LOWER_THAN_IND_FILTER', [
                        sourceClass    : DEMA.canonicalName,
                        sourceParameter: parameter,
                        targetClass    : targetIndicator,
                        targetParameter: targetParameter
                ])
                break
            case Operators.CROSSING_TO_UP:
                idList = lowLevelDataService.executeFunction('IND_CROSS_UP_IND_FILTER', [
                        sourceClass    : DEMA.class.canonicalName,
                        sourceParameter: parameter,
                        targetClass    : targetIndicator,
                        targetParameter: targetParameter
                ])
                break
            case Operators.CROSSING_TO_DOWN:
                idList = lowLevelDataService.executeFunction('IND_CROSS_DOWN_IND_FILTER', [
                        sourceClass    : DEMA.class.canonicalName,
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
