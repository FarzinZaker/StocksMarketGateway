package stocks.indicators.symbol.volatility

import com.tictactec.ta.lib.Core
import com.tictactec.ta.lib.MInteger
import stocks.indicators.IndicatorServiceBase
import stocks.tse.Symbol
import stocks.util.TypeCast

class BETAService implements IndicatorServiceBase<Symbol, Integer> {

    def tradesDataService

    @Override
    Boolean getEnabled() {
        false
    }

    @Override
    List<Integer> getCommonParameters() {
        [5]
    }

    @Override
    Double calculate(Symbol item, Integer parameter, String adjustmentType, List series, Date date) {
        if (series.size() < parameter)
            return 0
        def core = new Core()
        def beginIndex = new MInteger()
        def endIndex = new MInteger()
        def result = new double[parameter]
        core.beta(0, parameter - 1, TypeCast.toDoubleArray(series.collect {
            it.maxPrice
        }), TypeCast.toDoubleArray(series.collect { it.minPrice }), parameter, beginIndex, endIndex, result)
        result?.toList()?.first()
    }

    @Override
    Map bulkCalculate(Symbol item, Integer parameter, String adjustmentType, List series) {
        def core = new Core()
        def beginIndex = new MInteger()
        def endIndex = new MInteger()
        def result = new double[series.size()]
        core.beta(0, series.size() - 1, TypeCast.toDoubleArray(series.collect {
            it.maxPrice
        }), TypeCast.toDoubleArray(series.collect { it.minPrice }), parameter, beginIndex, endIndex, result)
        [
                series: series,
                indicators: result
        ]
    }
}
