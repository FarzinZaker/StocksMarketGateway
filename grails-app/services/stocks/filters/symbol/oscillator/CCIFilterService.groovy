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
        [value: 14]
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
            case Operators.GO_UPPER_THAN:
                idList = lowLevelDataService.executeStoredProcedure('indicator_go_upper_than_value_filter', [
                        sourceClass    : CCI.canonicalName,
                        sourceParameter: parameter,
                        targetValue    : targetValue as Double
                ])
                break
            case Operators.GO_LOWER_THAN:
                idList = lowLevelDataService.executeStoredProcedure('indicator_go_lower_than_value_filter', [
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
