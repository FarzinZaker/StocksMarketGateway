package stocks.indicators.symbol.movingAverage

import com.tictactec.ta.lib.Core
import com.tictactec.ta.lib.MInteger
import eu.verdelhan.ta4j.indicators.AbstractIndicator
import eu.verdelhan.ta4j.indicators.simple.ClosePriceIndicator
import eu.verdelhan.ta4j.indicators.trackers.SMAIndicator
import stocks.indicators.IndicatorServiceBase
import stocks.tse.Symbol
import stocks.util.TypeCast

class SMAService implements IndicatorServiceBase<Symbol, Integer> {

    def tradesDataService

    @Override
    public Boolean getEnabled(){
        true
    }

    @Override
    List<Integer> getCommonParameters() {
        [7, 13, 26, 50, 200]
    }

    @Override
    Double calculate(Symbol item, Integer parameter, Date date = new Date()) {

        def series = tradesDataService.getClosingPriceSeries(item, parameter, date)
        def core = new Core()
        def beginIndex = new MInteger()
        def endIndex = new MInteger()
        def result = new double[parameter]
        core.sma(0, parameter - 1, TypeCast.toDoubleArray(series), parameter, beginIndex, endIndex, result)
        result?.toList()?.first()
    }
}
