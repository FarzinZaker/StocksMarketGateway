package stocks.indicators

import stocks.tse.Symbol
import stocks.tse.SymbolDailyTrade

class IndicatorBase {

    Symbol symbol
    Double value
    String parameter
    Integer dayNumber
    SymbolDailyTrade dailyTrade

    Date calculationDate

    static mapping = {
        table 'indicators'
//        dayNumber index: 'idx_indicator_symbol_parameter_index'
//        parameter index: 'idx_indicator_symbol_parameter_index, idx_indicator_parameter_trade'
//        symbol index: 'idx_indicator_symbol_parameter_index'
//        dailyTrade index: 'idx_indicator_parameter_trade'
//        value index: 'idx_value'
//        calculationDate index: 'idx_calculation_date'
    }

    static constraints = {
    }

    def beforeInsert() {
        calculationDate = (calculationDate ?: new Date()).clearTime()
    }
}
