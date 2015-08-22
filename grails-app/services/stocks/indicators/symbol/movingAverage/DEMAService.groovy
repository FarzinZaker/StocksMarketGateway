package stocks.indicators.symbol.movingAverage

import com.tictactec.ta.lib.Core
import com.tictactec.ta.lib.MInteger
import eu.verdelhan.ta4j.indicators.AbstractIndicator
import eu.verdelhan.ta4j.indicators.simple.ClosePriceIndicator
import eu.verdelhan.ta4j.indicators.trackers.DoubleEMAIndicator
import stocks.indicators.IndicatorServiceBase
import stocks.tse.Symbol
import stocks.util.TypeCast

class DEMAService implements IndicatorServiceBase<Symbol, Integer> {

    def tradesDataService

    @Override
    Boolean getEnabled() {
        false
    }

    @Override
    List<Integer> getCommonParameters() {
        [3, 7, 26, 50, 200]
    }

    @Override
    Double calculate(Symbol item, Integer parameter, String adjustmentType, List series, Date date = new Date()) {
        if (series.size() < parameter * 2 - 1)
            return 0
        def core = new Core()
        def beginIndex = new MInteger()
        def endIndex = new MInteger()
        def result = new double[parameter]
        core.dema(0, parameter * 2 - 2, TypeCast.toDoubleArray(series.collect {
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
        core.dema(0, series.size() - 1, TypeCast.toDoubleArray(series.collect {
            it.closingPrice
        }), parameter, beginIndex, endIndex, result)
        [
                series: series,
                indicators: result
        ]
    }
}
