package stocks.filters.symbol.value

import org.grails.datastore.mapping.query.Query
import org.grails.datastore.mapping.query.Restrictions
import stocks.User
import stocks.filters.Operators
import stocks.filters.QueryFilterServiceBase
import stocks.tse.SymbolDailyTrade

import javax.naming.OperationNotSupportedException
import java.text.NumberFormat

class VolumeFilterService implements QueryFilterServiceBase {

    @Override
    ArrayList<String> getOperators() {
        [
                Operators.GREATER_THAN,
                Operators.GREATER_THAN_OR_EQUAL,
                Operators.LESS_THAN,
                Operators.LESS_THAN_OR_EQUAL
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
        def lastSymbolDailyTrades = SymbolDailyTrade.createCriteria().list {
            projections {
                groupProperty('symbol')
                max('id')
            }
        }.collect { it.last() }
        switch (operator) {
            case Operators.GREATER_THAN:
                return Restrictions.in('id', SymbolDailyTrade.findAllByTotalTradeVolumeGreaterThanAndIdInList(value.find() as Integer, lastSymbolDailyTrades).collect {
                    it.symbol?.id
                })
            case Operators.GREATER_THAN_OR_EQUAL:
                return Restrictions.in('id', SymbolDailyTrade.findAllByTotalTradeVolumeGreaterThanEqualsAndIdInList(value.find() as Integer, lastSymbolDailyTrades).collect {
                    it.symbol?.id
                })
            case Operators.LESS_THAN:
                return Restrictions.in('id', SymbolDailyTrade.findAllByTotalTradeVolumeLessThanAndIdInList(value.find() as Integer, lastSymbolDailyTrades).collect {
                    it.symbol?.id
                })
            case Operators.LESS_THAN_OR_EQUAL:
                return Restrictions.in('id', SymbolDailyTrade.findAllByTotalTradeVolumeLessThanEqualsAndIdInList(value.find() as Integer, lastSymbolDailyTrades).collect {
                    it.symbol?.id
                })
            default:
                throw new OperationNotSupportedException("Invalid operator for ${this.class.simpleName}: ${operator}")
        }
    }
}
