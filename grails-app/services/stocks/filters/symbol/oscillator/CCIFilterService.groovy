package stocks.filters.symbol.oscillator

import org.grails.datastore.mapping.query.Query
import org.grails.datastore.mapping.query.Restrictions
import stocks.User
import stocks.filters.IncludeFilterService
import stocks.filters.Operators
import stocks.filters.QueryFilterService
import stocks.indicators.symbol.oscillator.CCI

import java.text.NumberFormat

class CCIFilterService implements IncludeFilterService {

    def lowLevelDataService

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
        [value: 14]
    }

    @Override
    String getValueTemplate(String operator) {
        'value'
    }

    @Override
    def getValueModel(User user, String operator) {
        [value: 0]
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
                        sourceClass    : CCI.canonicalName,
                        sourceParameter: parameter,
                        targetValue    : targetValue as Double
                ])
                break
            case Operators.LOWER_THAN:
                idList = lowLevelDataService.executeStoredProcedure('indicator_lower_than_value_filter', [
                        sourceClass    : CCI.canonicalName,
                        sourceParameter: parameter,
                        targetValue    : targetValue as Double
                ])
                break
            case Operators.CROSSING_TO_UP:
                idList = lowLevelDataService.executeStoredProcedure('indicator_cross_up_value_filter', [
                        sourceClass    : CCI.canonicalName,
                        sourceParameter: parameter,
                        targetValue    : targetValue as Double
                ])
                break
            case Operators.CROSSING_TO_DOWN:
                idList = lowLevelDataService.executeStoredProcedure('indicator_cross_down_value_filter', [
                        sourceClass    : CCI.canonicalName,
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
