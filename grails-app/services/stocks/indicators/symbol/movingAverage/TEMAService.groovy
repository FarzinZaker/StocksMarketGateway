package stocks.indicators.symbol.movingAverage

import com.tictactec.ta.lib.Core
import com.tictactec.ta.lib.MInteger
import eu.verdelhan.ta4j.indicators.AbstractIndicator
import eu.verdelhan.ta4j.indicators.simple.ClosePriceIndicator
import eu.verdelhan.ta4j.indicators.trackers.TripleEMAIndicator
import stocks.indicators.IndicatorServiceBase
import stocks.tse.Symbol
import stocks.util.TypeCast

class TEMAService implements IndicatorServiceBase<Symbol, Integer> {

    def tradesDataService

    @Override
    Boolean getEnabled() {
        false
    }

    @Override
    List<Integer> getCommonParameters() {
        [7,3,26,50,200]
    }

    @Override
    Double calculate(Symbol item, Integer parameter, Date date = new Date()) {

        def series = tradesDataService.getClosingPriceSeries(item, parameter, date)
        def core = new Core()
        def beginIndex = new MInteger()
        def endIndex = new MInteger()
        def result = new double[parameter]
        core.tema(0, parameter - 1, TypeCast.toDoubleArray(series), parameter, beginIndex, endIndex, result)
        result?.toList()?.first()
    }
}
