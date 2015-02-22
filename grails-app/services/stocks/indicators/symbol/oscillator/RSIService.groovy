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
    Double calculate(Symbol item, Integer parameter, Date date = new Date()) {

        def closeSeries = tradesDataService.getClosingPriceSeries(item, parameter + 1, date)
        def core = new Core()
        def beginIndex = new MInteger()
        def endIndex = new MInteger()
        def result = new double[parameter]
        core.rsi(0, parameter, TypeCast.toDoubleArray(closeSeries), parameter, beginIndex, endIndex, result)
        result?.toList()?.first()
    }
}
