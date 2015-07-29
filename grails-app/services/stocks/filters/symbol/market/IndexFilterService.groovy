package stocks.filters.symbol.market

import net.sf.json.JSONArray
import stocks.User
import stocks.filters.ExcludeFilterService
import stocks.filters.IncludeFilterService
import stocks.filters.Operators
import stocks.tse.Index
import stocks.tse.IndexSymbol
import stocks.tse.Symbol
import stocks.util.CollectionHelper

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
                            name: it.persianName
                    ]
                }.sort { it.name }
        ]
    }

    @Override
    String[] formatQueryValue(Object value, String operator) {
        [value.collect { Index.get(it as Long) }*.persianName.join('، ')]
    }

    @Override
    Boolean check(Symbol symbol, String parameter, String operator, Object value, Date date, String adjustmentType) {
        false
    }

    @Override
    List<Long> getExcludeList(String parameter, String operator, Object value, String adjustmentType) {
        if (operator == Operators.NOT_IN_LISt)
            return CollectionHelper.getConjunction((value as JSONArray).toList().collect{ val ->
                IndexSymbol.createCriteria().list{
                    index{
                        idEq(val as Long)
                    }
                    projections{
                        symbol {
                            property('id')
                        }
                    }
                }
            } as ArrayList<ArrayList>)
        null
    }

    @Override
    List<Long> getIncludeList(String parameter, String operator, Object value, String adjustmentType) {
        if (operator == Operators.IN_LIST)

            return CollectionHelper.getConjunction((value as JSONArray).toList().collect{ val ->
                IndexSymbol.createCriteria().list{
                    index{
                        idEq(val as Long)
                    }
                    projections{
                        symbol {
                            property('id')
                        }
                    }
                }
            } as ArrayList<ArrayList>)
        null
    }
}
