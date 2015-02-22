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
    Double calculate(Symbol item, Integer parameter, Date date = new Date()) {

        def series = tradesDataService.getClosingPriceSeries(item, parameter * 2 - 1, date)
        def core = new Core()
        def beginIndex = new MInteger()
        def endIndex = new MInteger()
        def result = new double[parameter]
        core.dema(0, parameter * 2 - 2, TypeCast.toDoubleArray(series), parameter, beginIndex, endIndex, result)
        result?.toList()?.first()
    }
}
