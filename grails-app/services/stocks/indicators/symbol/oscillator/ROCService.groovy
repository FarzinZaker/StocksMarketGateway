package stocks.indicators.symbol.oscillator

import com.tictactec.ta.lib.Core
import com.tictactec.ta.lib.MInteger
import eu.verdelhan.ta4j.indicators.AbstractIndicator
import stocks.indicators.IndicatorServiceBase
import stocks.tse.Symbol
import stocks.util.TypeCast

class ROCService extends IndicatorServiceBase<Symbol, Integer> {

    def tradesDataService

    @Override
    List<Integer> getCommonParameters() {
        [3, 7, 13, 26, 50, 200]
    }

    @Override
    Double calculate(Symbol item, Integer parameter, Date date = new Date()) {

        def closeSeries = tradesDataService.getClosingPriceSeries(item, parameter, date)
        def core = new Core()
        def beginIndex = new MInteger()
        def endIndex = new MInteger()
        def result = new double[parameter]
        core.roc(0, parameter - 1, TypeCast.toDoubleArray(closeSeries), parameter, beginIndex, endIndex, result)
        result?.toList()?.first()
    }
}