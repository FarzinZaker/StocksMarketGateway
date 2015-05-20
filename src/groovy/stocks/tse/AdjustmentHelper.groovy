package stocks.tse

import grails.util.Holders
import org.codehaus.groovy.grails.web.util.WebUtils

import javax.lang.model.util.Types

/**
 * Created by farzin on 17/05/2015.
 */
class AdjustmentHelper {

    public static String TYPE_NONE = 'none'
    public static String TYPE_CAPITAL_INCREASE = 'capitalIncrease'
    public static String TYPE_CAPITAL_INCREASE_PLUS_BROUGHT = 'capitalIncreasePlusBrought'
    public static String TYPE_CAPITAL_INCREASE_PLUS_DIVIDENDS = 'capitalIncreasePlusDividends'
    public static String TYPE_CAPITAL_INCREASE_PLUS_DIVIDENDS_PLUS_BROUGHT = 'capitalIncreasePlusDividendsPlusBrought'
    public static String TYPE_PERFORMANCE = 'performance'

    public
    static TYPES = [TYPE_NONE, TYPE_CAPITAL_INCREASE, TYPE_CAPITAL_INCREASE_PLUS_BROUGHT, TYPE_CAPITAL_INCREASE_PLUS_DIVIDENDS, TYPE_CAPITAL_INCREASE_PLUS_DIVIDENDS_PLUS_BROUGHT, TYPE_PERFORMANCE]
    public
    static ENABLED_TYPES = [TYPE_NONE, TYPE_CAPITAL_INCREASE_PLUS_BROUGHT]

    private static String globalAdjustmentTypeSession = 'globalAdjustmentType'

    public static String getDefaultType() {
        TYPE_CAPITAL_INCREASE_PLUS_BROUGHT
    }

    private static Boolean isGlobalAdjustmentTypeSet() {
        WebUtils.retrieveGrailsWebRequest().session[globalAdjustmentTypeSession] ? true : false
    }

    public static void initGlobalAdjustmentType() {
        if (!isGlobalAdjustmentTypeSet())
            setGlobalAdjustmentType(defaultType)
    }

    public static String getGlobalAdjustmentType() {
        def result = WebUtils.retrieveGrailsWebRequest().session[globalAdjustmentTypeSession]
        if (!result)
            result = defaultType
        result
    }

    public static void setGlobalAdjustmentType(String value) {
        WebUtils.retrieveGrailsWebRequest().session[globalAdjustmentTypeSession] = value
    }
}
