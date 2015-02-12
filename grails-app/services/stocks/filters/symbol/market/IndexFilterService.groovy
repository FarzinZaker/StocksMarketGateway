package stocks.filters.symbol.market

import org.grails.datastore.mapping.query.Query
import org.grails.datastore.mapping.query.Restrictions
import stocks.User
import stocks.filters.Operators
import stocks.filters.QueryFilterServiceBase
import stocks.tse.Index
import stocks.tse.IndexSymbol

import javax.naming.OperationNotSupportedException

class IndexFilterService implements QueryFilterServiceBase {
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
    def getValueModel(User user, String operator) {
        [
                indexes: Index.findAll().collect {
                    [
                            id  : it.id,
                            name: it.persianName
                    ]
                }
        ]
    }

    @Override
    String formatQueryValue(Object value) {
        value.collect { Index.get(it as Long) }*.persianName.join('، ')
    }

    @Override
    Query.Criterion getCriteria(String parameter, String operator, Object value) {
        switch (operator) {
            case Operators.IN_LIST:
                return Restrictions.in('id', IndexSymbol.findAllByIndex(Index.get(value.find() as Long)).collect {
                    it.symbol?.id
                })
            case Operators.NOT_IN_LISt:
                return new Query.Negation().add(Restrictions.in('id', IndexSymbol.findAllByIndex(Index.get(value.find() as Long)).collect {
                    it.symbol?.id
                }))
            default:
                throw new OperationNotSupportedException("Invalid operator for ${this.class.simpleName}: ${operator}")
        }
    }
}
