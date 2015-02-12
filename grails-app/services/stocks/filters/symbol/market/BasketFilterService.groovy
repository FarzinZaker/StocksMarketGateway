package stocks.filters.symbol.market

import org.grails.datastore.mapping.query.Query
import org.grails.datastore.mapping.query.Query.Criterion
import org.grails.datastore.mapping.query.Restrictions
import stocks.User
import stocks.filters.Operators
import stocks.filters.QueryFilterServiceBase
import stocks.portfolio.Portfolio
import stocks.portfolio.PortfolioItem

import javax.naming.OperationNotSupportedException

class BasketFilterService implements QueryFilterServiceBase {

    def springSecurityService

    @Override
    ArrayList<String> getOperators() {
        return [
                Operators.MEMBER_OF,
                Operators.NOT_MEMBER_OF
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
                baskets: Portfolio.findAllByOwnerAndDeleted(user, false).collect {
                    [
                            id  : it.id,
                            name: it.name
                    ]
                }
        ]
    }

    @Override
    String formatQueryValue(Object value) {
        value.collect { Portfolio.get(it as Long).name }.join('، ')
    }

    @Override
    Criterion getCriteria(String parameter, String operator, Object value) {
        switch (operator) {
            case Operators.MEMBER_OF:
                return Restrictions.in('id', PortfolioItem.findAllByPortfolio(Portfolio.get(value.find() as Long)).collect {
                    it.symbol?.id
                })
            case Operators.NOT_MEMBER_OF:
                return new Query.Negation().add(Restrictions.in('id', PortfolioItem.findAllByPortfolio(Portfolio.get(value.find() as Long)).collect {
                    it.symbol?.id
                }))
            default:
                throw new OperationNotSupportedException("Invalid operator for ${this.class.simpleName}: ${operator}")
        }
    }
}
