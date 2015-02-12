package stocks.indicators

import stocks.tse.Symbol

class IndicatorBase {

    Symbol symbol
    Double value
    String parameter

    Date calculationDate

    static mapping = {
        table 'indicators'
//        symbol index: 'idx_symbol_parameter'
        parameter index: 'idx_indicator_parameter'
        value index: 'idx_value'
        calculationDate index: 'idx_calculation_date'
    }

    static constraints = {
    }

    def beforeInsert() {
        calculationDate = (calculationDate ?: new Date()).clearTime()
    }
}
