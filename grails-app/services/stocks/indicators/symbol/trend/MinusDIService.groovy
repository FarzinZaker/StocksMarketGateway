package stocks.indicators.symbol.trend

import com.tictactec.ta.lib.Core
import com.tictactec.ta.lib.MInteger
import stocks.indicators.IndicatorServiceBase
import stocks.tse.Symbol
import stocks.util.TypeCast

class MinusDIService implements IndicatorServiceBase<Symbol, Integer> {

    def tradesDataService

    @Override
    Boolean getEnabled() {
        false
    }

    @Override
    List<Integer> getCommonParameters() {
        [7, 14, 28]
    }

    @Override
    Double calculate(Symbol item, Integer parameter, String adjustmentType, List series, Date date) {
        if ([series.size(), highSeries.size(), lowSeries.size()].min() < parameter)
            return 0
        def core = new Core()
        def beginIndex = new MInteger()
        def endIndex = new MInteger()
        def result = new double[parameter]
        core.minusDI(0, parameter - 1, TypeCast.toDoubleArray(series.collect {
            it.maxPrice
        }), TypeCast.toDoubleArray(series.collect { it.minPrice }), TypeCast.toDoubleArray(series.collect {
            it.closingPrice
        }), parameter, beginIndex, endIndex, result)
        result?.toList()?.first()
    }

    @Override
    Map bulkCalculate(Symbol item, Integer parameter, String adjustmentType, List series) {
        def core = new Core()
        def beginIndex = new MInteger()
        def endIndex = new MInteger()
        def result = new double[series.size()]
        core.minusDI(0, series.size() - 1, TypeCast.toDoubleArray(series.collect {
            it.maxPrice
        }), TypeCast.toDoubleArray(series.collect { it.minPrice }), TypeCast.toDoubleArray(series.collect {
            it.closingPrice
        }), parameter, beginIndex, endIndex, result)
        [
                series    : series,
                indicators: result
        ]
    }
}
