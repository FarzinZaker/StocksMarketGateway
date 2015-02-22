package stocks.filters

/**
 * Created by farzin on 16/02/2015.
 */
interface ExcludeFilterService extends FilterServiceBase {

    List<Long> getExcludeList(String parameter, String operator, value)
}
