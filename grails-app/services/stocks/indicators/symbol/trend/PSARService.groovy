package stocks.indicators.symbol.trend

import com.tictactec.ta.lib.Core
import com.tictactec.ta.lib.MInteger
import stocks.indicators.IndicatorServiceBase
import stocks.tse.Symbol
import stocks.util.TypeCast

class PSARService implements IndicatorServiceBase<Symbol, List<Integer>> {

    def tradesDataService

    @Override
    Boolean getEnabled() {
        false
    }

    @Override
    List<List<Integer>> getCommonParameters() {
        [[0.02, 0.2]]
    }

    @Override
    Double calculate(Symbol item, List<Integer> parameter, String adjustmentType, List series, Date date) {
        if (series.size() < parameter.max())
            return 0
        def core = new Core()
        def beginIndex = new MInteger()
        def endIndex = new MInteger()
        def result = new double[series.size()]
        core.sar(0, series.size() - 1, TypeCast.toDoubleArray(series.collect {
            it.maxPrice
        }), TypeCast.toDoubleArray(series.collect {
            it.minPrice
        }), parameter[0], parameter[1], beginIndex, endIndex, result)
        result?.toList()?.first()
    }

    @Override
    Map bulkCalculate(Symbol item, List<Integer> parameter, String adjustmentType, List series) {
        def core = new Core()
        def beginIndex = new MInteger()
        def endIndex = new MInteger()
        def result = new double[series.size()]
        core.sar(0, series.size() - 1, TypeCast.toDoubleArray(series.collect {
            it.maxPrice
        }), TypeCast.toDoubleArray(series.collect {
            it.minPrice
        }), parameter[0], parameter[1], beginIndex, endIndex, result)
        [
                series    : series,
                indicators: result
        ]
    }
}
