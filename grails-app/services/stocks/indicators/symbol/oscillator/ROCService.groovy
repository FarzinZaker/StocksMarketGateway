package stocks.indicators.symbol.oscillator

import com.tictactec.ta.lib.Core
import com.tictactec.ta.lib.MInteger
import eu.verdelhan.ta4j.indicators.AbstractIndicator
import stocks.indicators.IndicatorServiceBase
import stocks.tse.Symbol
import stocks.util.TypeCast

class ROCService implements IndicatorServiceBase<Symbol, Integer> {

    def tradesDataService

    @Override
    Boolean getEnabled() {
        true
    }

    @Override
    List<Integer> getCommonParameters() {
        [10]
    }

    @Override
    Double calculate(Symbol item, Integer parameter, String adjustmentType, List series, Date date = new Date()) {
        if (series.size() < parameter + 1)
            return 0
        series=series.subList(series.size()-parameter-1,series.size())
        def core = new Core()
        def beginIndex = new MInteger()
        def endIndex = new MInteger()
        def result = new double[parameter]
        core.rocR(0, parameter, TypeCast.toDoubleArray(series.collect {
            it.lastTradePrice
        }), parameter, beginIndex, endIndex, result)
        (1-(1/result.find()))*100
    }

    @Override
    Map bulkCalculate(Symbol item, Integer parameter, String adjustmentType, List series) {
        def core = new Core()
        def beginIndex = new MInteger()
        def endIndex = new MInteger()
        def result = new double[series.size()]
        core.rocR(0, series.size() - 1, TypeCast.toDoubleArray(series.collect {
            it.lastTradePrice
        }), parameter, beginIndex, endIndex, result)
        [
                series    : series,
                indicators: result.collect { it?(1-(1/it))*100:0}
        ]
    }
}
