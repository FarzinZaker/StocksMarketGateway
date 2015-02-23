package stocks.filters.symbol.market

import org.grails.datastore.mapping.query.Query
import org.grails.datastore.mapping.query.Query.Criterion
import org.grails.datastore.mapping.query.Restrictions
import stocks.User
import stocks.filters.ExcludeFilterService
import stocks.filters.IncludeFilterService
import stocks.filters.Operators
import stocks.filters.QueryFilterService
import stocks.portfolio.Portfolio
import stocks.portfolio.PortfolioItem

import javax.naming.OperationNotSupportedException

class BasketFilterService implements IncludeFilterService, ExcludeFilterService {

    def springSecurityService

    @Override
    Boolean getEnabled() {
        true
    }

    @Override
    ArrayList<String> getOperators() {
        return [
                Operators.MEMBER_OF,
                Operators.NOT_MEMBER_OF
        ]
    }

    @Override
    String getFilterParamsTemplate() {
        null
    }

    @Override
    def getFilterParamsModel() {
        null
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
    String[] formatQueryValue(Object value, String operator) {
        [value.collect { Portfolio.get(it as Long).name }.join('، ')]
    }

    @Override
    List<Long> getExcludeList(String parameter, String operator, Object value) {
        if (operator == Operators.NOT_MEMBER_OF)
            return PortfolioItem.findAllByPortfolio(Portfolio.get(value.find() as Long)).collect {
                it.symbol?.id
            }
        null
    }

    @Override
    List<Long> getIncludeList(String parameter, String operator, Object value) {
        if (operator == Operators.MEMBER_OF)
            return PortfolioItem.findAllByPortfolio(Portfolio.get(value.find() as Long)).collect {
                it.symbol?.id
            }
        null
    }
}
