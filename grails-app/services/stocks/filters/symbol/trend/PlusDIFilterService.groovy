package stocks.filters.symbol.trend

import org.grails.datastore.mapping.query.Query
import stocks.User
import stocks.filters.Operators
import stocks.filters.QueryFilterServiceBase

import java.text.NumberFormat

class PlusDIFilterService implements QueryFilterServiceBase {

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
        [value:14]
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
        return null
    }
}
