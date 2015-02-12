package stocks.filters

import java.util.logging.Filter

/**
 * Created by farzin on 19/01/2015.
 */
interface TechnicalFilterServiceBase<T> extends FilterServiceBase {

    List<T> apply(parameter, String operator, value, List<T> list)
}