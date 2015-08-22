package stocks.indicators.symbol.trend

import com.tictactec.ta.lib.Core
import com.tictactec.ta.lib.MInteger
import stocks.indicators.IndicatorServiceBase
import stocks.tse.Symbol
import stocks.util.TypeCast

class MACDService implements IndicatorServiceBase<Symbol, List<Integer>> {

    def tradesDataService

    @Override
    Boolean getEnabled() {
        true
    }

    @Override
    List<List<Integer>> getCommonParameters() {
        [[12, 26, 9]]
    }

    @Override
    Double calculate(Symbol item, List<Integer> parameter, String adjustmentType, List series, Date date) {
        if (series.size() < parameter.max() * 2)
            return 0
        def core = new Core()
        def beginIndex = new MInteger()
        def endIndex = new MInteger()
        def result = new double[series.size()]
        def histogram = new double[series.size()]
        def signal = new double[series.size()]
        core.macd(0,series.size()-1, TypeCast.toDoubleArray(series.collect {
            it.lastTradePrice
        }), parameter[0], parameter[1], parameter[2], beginIndex, endIndex, result, signal, histogram)
        result[endIndex.value-1]
    }

    @Override
    Map bulkCalculate(Symbol item, List<Integer> parameter, String adjustmentType, List series) {
        def core = new Core()
        def beginIndex = new MInteger()
        def endIndex = new MInteger()
        def result = new double[series.size()]
        def histogram = new double[series.size()]
        def signal = new double[series.size()]
        core.macd(0, series.size() - 1, TypeCast.toDoubleArray(series.collect {
            it.lastTradePrice
        }), parameter[0], parameter[1], parameter[2], beginIndex, endIndex, result, signal, histogram)
        [
                series    : series,
                indicators: result
        ]
    }
}
