package stocks.filters.symbol.movingAverage
import grails.util.Holders
import org.springframework.web.servlet.i18n.SessionLocaleResolver
import stocks.User
import stocks.filters.FilterServiceBase
import stocks.filters.IncludeFilterService
import stocks.filters.Operators
import stocks.indicators.symbol.movingAverage.TEMA
import stocks.tse.AdjustmentHelper
import stocks.tse.Symbol
import stocks.util.ClassResolver

class TEMAFilterService implements IncludeFilterService {

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
            ["قیمت"]
    }

    @Override
    Boolean check(String parameter, String operator, value, Date date, List dailyTrades, List indicators) {
        def targetIndicatorName = value.sort {
            -it[0].size()
        }.first()[0].replace('FilterService', '').replace('.filters', '.indicators') as String
        def targetIndicator = targetIndicatorName != 'Price' ? ClassResolver.loadDomainClassByName(targetIndicatorName) : null
        def targetParameter = value.first()[1] as String

        switch (operator) {
            case Operators.UPPER_THAN:
                if (targetIndicator)
                    return indicatorCompareService.indicatorUpperThanIndicator(TEMA, parameter, targetIndicator, targetParameter, date, dailyTrades, indicators)
                else
                    return indicatorCompareService.indicatorUpperThanPrice(TEMA, parameter, date, dailyTrades, indicators)
            case Operators.LOWER_THAN:
                if (targetIndicator)
                    return indicatorCompareService.indicatorLowerThanIndicator(TEMA, parameter, targetIndicator, targetParameter, date, dailyTrades, indicators)
                else
                    return indicatorCompareService.indicatorLowerThanPrice(TEMA, parameter, date, dailyTrades, indicators)
            case Operators.CROSSING_TO_UP:
                if (targetIndicator)
                    return indicatorCompareService.indicatorCrossUpIndicator(TEMA, parameter, targetIndicator, targetParameter, date, dailyTrades, indicators)
                else
                    return indicatorCompareService.indicatorCrossUpPrice(TEMA, parameter, date, dailyTrades, indicators)
            case Operators.CROSSING_TO_DOWN:
                if (targetIndicator)
                    return indicatorCompareService.indicatorCrossDownIndicator(TEMA, parameter, targetIndicator, targetParameter, date, dailyTrades, indicators)
                else
                    return indicatorCompareService.indicatorCrossDownPrice(TEMA, parameter, date, dailyTrades, indicators)
        }
        false
    }

    @Override
    List<Long> getIncludeList(String parameter, String operator, Object value, String adjustmentType) {

        def idList = []
        def targetIndicator = value.sort{-it[0].size()}.first()[0].replace('FilterService', '').replace('.filters', '.indicators')
        def targetParameter = value.first()[1]

        switch (operator) {
            case Operators.UPPER_THAN:
                idList = lowLevelDataService.executeFunction('IND_UPPER_THAN_IND_FILTER', [
                        sourceClass    : TEMA.canonicalName,
                        sourceParameter: parameter,
                        targetClass    : targetIndicator,
                        targetParameter: targetParameter,
                        adjustmentType : adjustmentType
                ])
                break
            case Operators.LOWER_THAN:
                idList = lowLevelDataService.executeFunction('IND_LOWER_THAN_IND_FILTER', [
                        sourceClass    : TEMA.canonicalName,
                        sourceParameter: parameter,
                        targetClass    : targetIndicator,
                        targetParameter: targetParameter,
                        adjustmentType : adjustmentType
                ])
                break
            case Operators.CROSSING_TO_UP:
                idList = lowLevelDataService.executeFunction('IND_CROSS_UP_IND_FILTER', [
                        sourceClass    : TEMA.class.canonicalName,
                        sourceParameter: parameter,
                        targetClass    : targetIndicator,
                        targetParameter: targetParameter,
                        adjustmentType : adjustmentType
                ])
                break
            case Operators.CROSSING_TO_DOWN:
                idList = lowLevelDataService.executeFunction('IND_CROSS_DOWN_IND_FILTER', [
                        sourceClass    : TEMA.class.canonicalName,
                        sourceParameter: parameter,
                        targetClass    : targetIndicator,
                        targetParameter: targetParameter,
                        adjustmentType : adjustmentType
                ])
                break
        }

        idList?.collect {
            it?.values()?.first()?.toLong()
        }
    }
}
