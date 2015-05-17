package stocks.tse

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

    public static String getDefaultType() {
        TYPE_CAPITAL_INCREASE_PLUS_BROUGHT
    }
}
