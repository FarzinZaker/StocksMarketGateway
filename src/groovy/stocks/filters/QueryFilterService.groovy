package stocks.filters

import org.grails.datastore.mapping.query.Query.Criterion

/**
 * Created by farzin on 19/01/2015.
 */
interface QueryFilterService extends FilterServiceBase {

    Criterion getCriteria(String parameter, String operator, value, String adjustmentType)

}