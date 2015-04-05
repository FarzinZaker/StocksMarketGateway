package stocks.filters.symbol.market

import org.grails.datastore.mapping.query.Query
import org.grails.datastore.mapping.query.Restrictions
import stocks.User
import stocks.filters.ExcludeFilterService
import stocks.filters.IncludeFilterService
import stocks.filters.Operators
import stocks.filters.QueryFilterService
import stocks.tse.Index
import stocks.tse.IndexSymbol
import stocks.tse.Symbol

import javax.naming.OperationNotSupportedException

class IndexFilterService implements IncludeFilterService, ExcludeFilterService {

    @Override
    Map getEnabled() {
        [
                screener: true,
                backTest: false
        ]
    }

    @Override
    ArrayList<String> getOperators() {
        return [
                Operators.IN_LIST,
                Operators.NOT_IN_LISt
        ]
    }

    @Override
    String getFilterParamsTemplate() {
        return null
    }

    @Override
    def getFilterParamsModel() {
        return null
    }

    @Override
    String getValueTemplate(String operator) {
        'multiSelect'
    }

    @Override
    def getValueModel(User user, String operator, String place) {
        [
                indexes: Index.findAll().collect {
                    [
                            id  : it.id,
                            name: it.toString()
                    ]
                }.sort { it.name }
        ]
    }

    @Override
    String[] formatQueryValue(Object value, String operator) {
        [value.collect { Index.get(it as Long) }*.persianName.join('، ')]
    }

    @Override
    Boolean check(Symbol symbol, String parameter, String operator, Object value, Date date) {
        false
    }

    @Override
    List<Long> getExcludeList(String parameter, String operator, Object value) {
        if (operator == Operators.NOT_IN_LISt)
            return IndexSymbol.findAllByIndex(Index.get(value.find() as Long)).collect {
                it.symbol?.id
            }
        null
    }

    @Override
    List<Long> getIncludeList(String parameter, String operator, Object value) {
        if (operator == Operators.IN_LIST)
            return IndexSymbol.findAllByIndex(Index.get(value.find() as Long)).collect {
                it.symbol?.id
            }
        null
    }
}
