package stocks.filters

import stocks.User
import stocks.tse.Symbol

/**
 * Created by farzin on 19/01/2015.
 */
interface FilterServiceBase {

    Map getEnabled()

    ArrayList<String> getOperators()

    String getFilterParamsTemplate()

    def getFilterParamsModel()

    String getValueTemplate(String operator)

    def getValueModel(User user, String operator, String place)

    String[] formatQueryValue(value, String operator)

    Boolean check(Symbol symbol, String parameter, String operator, value, Date date)
}