package stocks.filters.symbol.volatility

import org.grails.datastore.mapping.query.Query
import org.grails.datastore.mapping.query.Restrictions
import stocks.User
import stocks.filters.IncludeFilterService
import stocks.filters.Operators
import stocks.filters.QueryFilterService
import stocks.indicators.symbol.volatility.TRange

import java.text.NumberFormat

class TRangeFilterService implements IncludeFilterService {

    def lowLevelDataService

    @Override
    Boolean getEnabled() {
        false
    }

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
        [value: 10]
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
    String[] formatQueryValue(Object value, String operator) {
        [NumberFormat.instance.format(value.first() as Double)]
    }

    @Override
    List<Long> getIncludeList(String parameter, String operator, Object value) {
        def idList = []
        def targetValue = value.first()

        switch (operator) {
            case Operators.UPPER_THAN:
                idList = lowLevelDataService.executeStoredProcedure('indicator_upper_than_value_filter', [
                        sourceClass    : TRange.canonicalName,
                        sourceParameter: parameter,
                        targetValue    : targetValue as Double
                ])
                break
            case Operators.LOWER_THAN:
                idList = lowLevelDataService.executeStoredProcedure('indicator_lower_than_value_filter', [
                        sourceClass    : TRange.canonicalName,
                        sourceParameter: parameter,
                        targetValue    : targetValue as Double
                ])
                break
            case Operators.GO_UPPER_THAN:
                idList = lowLevelDataService.executeStoredProcedure('indicator_go_upper_than_value_filter', [
                        sourceClass    : TRange.canonicalName,
                        sourceParameter: parameter,
                        targetValue    : targetValue as Double
                ])
                break
            case Operators.GO_LOWER_THAN:
                idList = lowLevelDataService.executeStoredProcedure('indicator_go_lower_than_value_filter', [
                        sourceClass    : TRange.canonicalName,
                        sourceParameter: parameter,
                        targetValue    : targetValue as Double
                ])
                break
        }

        idList?.collect {
            it?.values()?.first()?.toLong()
        }
    }
}
