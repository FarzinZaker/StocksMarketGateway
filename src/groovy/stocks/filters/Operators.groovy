package stocks.filters

/**
 * Created by farzin on 19/01/2015.
 */
enum Operators {

    final static String EQUAL = 'equal'
    final static String NOT_EQUAL = 'not-equal'
    final static String IN_LIST = 'in-list'
    final static String NOT_IN_LISt = 'not-in-list'
    final static String MEMBER_OF = 'member-of'
    final static String NOT_MEMBER_OF = 'not-member-of'
    final static String GREATER_THAN = 'greater-than'
//    final static String GREATER_THAN_OR_EQUAL = 'greater-than-or-equal'
    final static String LESS_THAN = 'less-than'
//    final static String LESS_THAN_OR_EQUAL = 'less-than-or-equal'
    final static String INCREASE_PERCENT_COMPARE_TO_PREVIOUS_DAY_GREATER_THAN = 'increase_percent_compare_to_previous_day_greater_than'
    final static String DECREASE_PERCENT_COMPARE_TO_PREVIOUS_DAY_GREATER_THAN = 'decrease_percent_compare_to_previous_day_greater_than'
    final static String INCREASE_PERCENT_COMPARE_TO_FIRST_PRICE_GREATER_THAN = 'increase_percent_compare_to_first_price_greater_than'
    final static String DECREASE_PERCENT_COMPARE_TO_FIRST_PRICE_GREATER_THAN = 'decrease_percent_compare_to_first_price_greater_than'
    final static String INCREASE_PERCENT_COMPARE_TO_AVERAGE_GREATER_THAN = 'increase_percent_compare_to_average_greater_than'
    final static String DECREASE_PERCENT_COMPARE_TO_AVERAGE_GREATER_THAN = 'decrease_percent_compare_to_average_greater_than'
    final static String UPPER_THAN = 'upper-than'
    final static String LOWER_THAN = 'lower-than'
    final static String CROSSING_TO_UP = 'crossing-to-up'
    final static String CROSSING_TO_DOWN = 'crossing-to-down'
    final static String GO_UPPER_THAN = 'go-upper-than'
    final static String GO_LOWER_THAN = 'go-lower-than'

}