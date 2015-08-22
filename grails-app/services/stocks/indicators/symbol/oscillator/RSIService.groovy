package stocks.indicators.symbol.oscillator

import com.tictactec.ta.lib.Core
import com.tictactec.ta.lib.MInteger
import eu.verdelhan.ta4j.indicators.AbstractIndicator
import stocks.indicators.IndicatorServiceBase
import stocks.tse.Symbol
import stocks.util.TypeCast

class RSIService implements IndicatorServiceBase<Symbol, Integer> {

    def tradesDataService

    @Override
    Boolean getEnabled() {
        true
    }

    @Override
    List<Integer> getCommonParameters() {
        [14, 9]
    }

    @Override
    Double calculate(Symbol item, Integer parameter, String adjustmentType, List series, Date date = new Date()) {
        if (series.size() < parameter + 1)
            return 0
        def core = new Core()
        def beginIndex = new MInteger()
        def endIndex = new MInteger()
        def result = new double[series.size()]
        core.rsi(0, series.size()-1, TypeCast.toDoubleArray(series.collect {
            it.lastTradePrice
        }), parameter, beginIndex, endIndex, result)
        result[series.size()-parameter-1]
    }

    @Override
    Map bulkCalculate(Symbol item, Integer parameter, String adjustmentType, List series) {
        def core = new Core()
        def beginIndex = new MInteger()
        def endIndex = new MInteger()
        def result = new double[series.size()]
        core.rsi(0, series.size() - 1, TypeCast.toDoubleArray(series.collect {
            it.lastTradePrice
        }), parameter, beginIndex, endIndex, result)
        [
                series: series,
                indicators: result
        ]
    }
}
