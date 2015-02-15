package stocks.filters.symbol.trend

import org.grails.datastore.mapping.query.Query
import org.grails.datastore.mapping.query.Restrictions
import stocks.User
import stocks.filters.Operators
import stocks.filters.QueryFilterServiceBase
import stocks.indicators.symbol.trend.PSAR

import java.text.NumberFormat

class PSARFilterService implements QueryFilterServiceBase {

    def lowLevelDataService

    @Override
    ArrayList<String> getOperators() {

        [
                Operators.UPPER_THAN,
                Operators.LOWER_THAN,
                Operators.GO_UPPER_THAN,
                Operators.GO_LOWER_THAN
        ]
    }

    @Override
    String getFilterParamsTemplate() {
        'parameters'
    }

    @Override
    def getFilterParamsModel() {
        [value: [0.02, 0.2].join(',')]
    }

    @Override
    String getValueTemplate(String operator) {
        'value'
    }

    @Override
    def getValueModel(User user, String operator) {
        [:]
    }

    @Override
    String formatQueryValue(Object value) {
        value.collect { NumberFormat.instance.format(it as Double) }.join('، ')
    }

    @Override
    Query.Criterion getCriteria(String parameter, String operator, Object value) {
        def idList = []
        def targetValue = value.first()

        switch (operator) {
            case Operators.UPPER_THAN:
                idList = lowLevelDataService.executeStoredProcedure('indicator_upper_than_value_filter', [
                        sourceClass    : PSAR.canonicalName,
                        sourceParameter: parameter,
                        targetValue    : targetValue as Double
                ])
                break
            case Operators.LOWER_THAN:
                idList = lowLevelDataService.executeStoredProcedure('indicator_lower_than_value_filter', [
                        sourceClass    : PSAR.canonicalName,
                        sourceParameter: parameter,
                        targetValue    : targetValue as Double
                ])
                break
            case Operators.GO_UPPER_THAN:
                idList = lowLevelDataService.executeStoredProcedure('indicator_go_upper_than_value_filter', [
                        sourceClass    : PSAR.canonicalName,
                        sourceParameter: parameter,
                        targetValue    : targetValue as Double
                ])
                break
            case Operators.GO_LOWER_THAN:
                idList = lowLevelDataService.executeStoredProcedure('indicator_go_lower_than_value_filter', [
                        sourceClass    : PSAR.canonicalName,
                        sourceParameter: parameter,
                        targetValue    : targetValue as Double
                ])
                break
        }

        return Restrictions.in('id', idList?.collect {
            it?.toLong()
        }?.findAll { it } ?: [])
    }
}