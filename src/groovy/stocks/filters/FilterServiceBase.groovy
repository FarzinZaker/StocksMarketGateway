package stocks.filters

import stocks.User

/**
 * Created by farzin on 19/01/2015.
 */
interface FilterServiceBase {

    ArrayList<String> getOperators();

    String getFilterParamsTemplate()

    def getFilterParamsModel()

    String getValueTemplate(String operator)

    def getValueModel(User user, String operator)

    String formatQueryValue(value)
}