package stocks.filters

import org.grails.datastore.mapping.query.Query

/**
 * Created by farzin on 16/02/2015.
 */
interface IncludeFilterService extends FilterServiceBase {

    List<Long> getIncludeList(String parameter, String operator, value)
}
