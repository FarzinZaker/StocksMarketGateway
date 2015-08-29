package stocks.filters.symbol.movingAverage

import grails.util.Holders
import org.springframework.web.servlet.i18n.SessionLocaleResolver
import stocks.User
import stocks.filters.FilterServiceBase
import stocks.filters.IncludeFilterService
import stocks.filters.Operators
import stocks.indicators.symbol.movingAverage.EMA
import stocks.tse.AdjustmentHelper
import stocks.tse.Symbol
import stocks.util.ClassResolver

class EMAFilterService implements IncludeFilterService {

    def tradesDataService
    def lowLevelDataService
    def messageSource
    def indicatorCompareService

    @Override
    Map getEnabled() {
        [
                screener: true,
                backTest: true
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
                    return indicatorCompareService.indicatorUpperThanIndicator(EMA, parameter, targetIndicator, targetParameter, date, dailyTrades, indicators)
                else
                    return indicatorCompareService.indicatorUpperThanPrice(EMA, parameter, date, dailyTrades, indicators)
            case Operators.LOWER_THAN:
                if (targetIndicator)
                    return indicatorCompareService.indicatorLowerThanIndicator(EMA, parameter, targetIndicator, targetParameter, date, dailyTrades, indicators)
                else
                    return indicatorCompareService.indicatorLowerThanPrice(EMA, parameter, date, dailyTrades, indicators)
            case Operators.CROSSING_TO_UP:
                if (targetIndicator)
                    return indicatorCompareService.indicatorCrossUpIndicator(EMA, parameter, targetIndicator, targetParameter, date, dailyTrades, indicators)
                else
                    return indicatorCompareService.indicatorCrossUpPrice(EMA, parameter, date, dailyTrades, indicators)
            case Operators.CROSSING_TO_DOWN:
                if (targetIndicator)
                    return indicatorCompareService.indicatorCrossDownIndicator(EMA, parameter, targetIndicator, targetParameter, date, dailyTrades, indicators)
                else
                    return indicatorCompareService.indicatorCrossDownPrice(EMA, parameter, date, dailyTrades, indicators)
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
                        sourceClass    : EMA.canonicalName,
                        sourceParameter: parameter,
                        targetClass    : targetIndicator,
                        targetParameter: targetParameter,
                        adjustmentType : adjustmentType
                ])
                break
            case Operators.LOWER_THAN:
                idList = lowLevelDataService.executeFunction('IND_LOWER_THAN_IND_FILTER', [
                        sourceClass    : EMA.canonicalName,
                        sourceParameter: parameter,
                        targetClass    : targetIndicator,
                        targetParameter: targetParameter,
                        adjustmentType : adjustmentType
                ])
                break
            case Operators.CROSSING_TO_UP:
                idList = lowLevelDataService.executeFunction('IND_CROSS_UP_IND_FILTER', [
                        sourceClass    : EMA.class.canonicalName,
                        sourceParameter: parameter,
                        targetClass    : targetIndicator,
                        targetParameter: targetParameter,
                        adjustmentType : adjustmentType
                ])
                break
            case Operators.CROSSING_TO_DOWN:
                idList = lowLevelDataService.executeFunction('IND_CROSS_DOWN_IND_FILTER', [
                        sourceClass    : EMA.class.canonicalName,
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
