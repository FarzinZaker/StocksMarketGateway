package stocks.filters.symbol.movingAverage

import eu.verdelhan.ta4j.indicators.AbstractIndicator
import eu.verdelhan.ta4j.indicators.simple.PreCalculatedIndicator
import grails.util.Holders
import org.grails.datastore.mapping.query.Query
import org.grails.datastore.mapping.query.Restrictions
import stocks.User
import stocks.filters.Operators
import stocks.filters.QueryFilterServiceBase
import stocks.filters.TechnicalFilterServiceBase
import stocks.indicators.symbol.movingAverage.EMA
import stocks.tse.Symbol

class EMAFilterService implements QueryFilterServiceBase {

    def tradesDataService
    def lowLevelDataService

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
        [value:7]
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
                }.collect {
                    [
                            text : it.name.replace('Filter', ''),
                            value: it.fullName
                    ]
                }
        ]
    }

    @Override
    String formatQueryValue(Object value) {
        def parameter = value.first()[1]
        if (parameter && parameter != '')
            "${value.first()[0].split('\\.').last().replace('FilterService', '')} (${parameter})"
        else
            "${value.first()[0].split('\\.').last().replace('FilterService', '')}"
    }

    @Override
    Query.Criterion getCriteria(String parameter, String operator, Object value) {

        def idList = []
        def targetIndicator = value.first()[0].replace('FilterService', '').replace('.filters', '.indicators')
        def targetParameter = value.first()[1]

        switch (operator) {
            case Operators.UPPER_THAN:
                idList = lowLevelDataService.executeStoredProcedure('indicator_upper_than_indicator_filter', [
                        sourceClass    : EMA.canonicalName,
                        sourceParameter: parameter,
                        targetClass    : targetIndicator,
                        targetParameter: targetParameter
                ])
                break
            case Operators.LOWER_THAN:
                idList = lowLevelDataService.executeStoredProcedure('indicator_upper_than_indicator_filter', [
                        sourceClass    : targetIndicator,
                        sourceParameter: targetParameter,
                        targetClass    : EMA.class.canonicalName,
                        targetParameter: parameter
                ])
                break
            case Operators.CROSSING_TO_UP:
                idList = lowLevelDataService.executeStoredProcedure('indicator_cross_indicator_filter', [
                        sourceClass    : EMA.class.canonicalName,
                        sourceParameter: parameter,
                        targetClass    : targetIndicator,
                        targetParameter: targetParameter
                ])
                break
            case Operators.CROSSING_TO_DOWN:
                idList = lowLevelDataService.executeStoredProcedure('indicator_cross_indicator_filter', [
                        sourceClass    : targetIndicator,
                        sourceParameter: targetParameter,
                        targetClass    : EMA.class.canonicalName,
                        targetParameter: parameter
                ])
                break
        }


        return Restrictions.in('id', idList?.collect {
            it?.toLong()
        }?.findAll { it } ?: [])
    }
}
