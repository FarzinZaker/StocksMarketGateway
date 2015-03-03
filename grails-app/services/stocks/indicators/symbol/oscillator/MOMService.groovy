package stocks.indicators.symbol.oscillator

import com.tictactec.ta.lib.Core
import com.tictactec.ta.lib.MInteger
import eu.verdelhan.ta4j.indicators.AbstractIndicator
import stocks.indicators.IndicatorServiceBase
import stocks.tse.Symbol
import stocks.util.TypeCast

class MOMService implements IndicatorServiceBase<Symbol, Integer> {

    def tradesDataService

    @Override
    Boolean getEnabled() {
        true
    }

    @Override
    List<Integer> getCommonParameters() {
        [3, 7, 13, 26, 50, 200]
    }

    @Override
    Double calculate(Symbol item, Integer parameter, Date date = new Date()) {

        def closeSeries = tradesDataService.getClosingPriceSeries(item, parameter + 1, date)
        if (closeSeries.size() < parameter + 1)
            return 0
        def core = new Core()
        def beginIndex = new MInteger()
        def endIndex = new MInteger()
        def result = new double[parameter]
        core.mom(0, parameter, TypeCast.toDoubleArray(closeSeries), parameter, beginIndex, endIndex, result)
        result?.toList()?.first()
    }
}
