package stocks.filters.symbol.movingAverage

import grails.util.Holders
import org.grails.datastore.mapping.query.Query
import org.grails.datastore.mapping.query.Restrictions
import org.springframework.web.servlet.i18n.SessionLocaleResolver
import stocks.User
import stocks.filters.FilterServiceBase
import stocks.filters.IncludeFilterService
import stocks.filters.Operators
import stocks.filters.QueryFilterService
import stocks.indicators.symbol.movingAverage.SMA
import stocks.util.ClassResolver

class SMAFilterService implements IncludeFilterService {

    def tradesDataService
    def lowLevelDataService
    def messageSource
    SessionLocaleResolver localeResolver

    @Override
    Boolean getEnabled() {
        true
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
    List<Long> getIncludeList(String parameter, String operator, Object value) {
        def idList = []
        def targetIndicator = value.first()[0].replace('FilterService', '').replace('.filters', '.indicators')
        def targetParameter = value.first()[1]

        switch (operator) {
            case Operators.UPPER_THAN:
                idList = lowLevelDataService.executeStoredProcedure('indicator_upper_than_indicator_filter', [
                        sourceClass    : SMA.canonicalName,
                        sourceParameter: parameter,
                        targetClass    : targetIndicator,
                        targetParameter: targetParameter
                ])
                break
            case Operators.LOWER_THAN:
                idList = lowLevelDataService.executeStoredProcedure('indicator_upper_than_indicator_filter', [
                        sourceClass    : targetIndicator,
                        sourceParameter: targetParameter,
                        targetClass    : SMA.class.canonicalName,
                        targetParameter: parameter
                ])
                break
            case Operators.CROSSING_TO_UP:
                idList = lowLevelDataService.executeStoredProcedure('indicator_cross_up_indicator_filter', [
                        sourceClass    : SMA.class.canonicalName,
                        sourceParameter: parameter,
                        targetClass    : targetIndicator,
                        targetParameter: targetParameter
                ])
                break
            case Operators.CROSSING_TO_DOWN:
                idList = lowLevelDataService.executeStoredProcedure('indicator_cross_up_indicator_filter', [
                        sourceClass    : targetIndicator,
                        sourceParameter: targetParameter,
                        targetClass    : SMA.class.canonicalName,
                        targetParameter: parameter
                ])
                break
        }

        idList?.collect {
            it?.values()?.first()?.toLong()
        }
    }
}
