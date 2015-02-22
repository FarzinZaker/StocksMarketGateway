package stocks.indicators.symbol.volatility

import com.tictactec.ta.lib.Core
import com.tictactec.ta.lib.MInteger
import stocks.indicators.IndicatorServiceBase
import stocks.tse.Symbol
import stocks.util.TypeCast

class ATRService implements IndicatorServiceBase<Symbol, Integer> {

    def tradesDataService

    @Override
    Boolean getEnabled() {
        false
    }

    @Override
    List<Integer> getCommonParameters() {
        [14]
    }

    @Override
    Double calculate(Symbol item, Integer parameter, Date date) {

        def closeSeries = tradesDataService.getClosingPriceSeries(item, parameter, date)
        def highSeries = tradesDataService.getMaxPriceSeries(item, parameter, date)
        def lowSeries = tradesDataService.getMinPriceSeries(item, parameter, date)
        def core = new Core()
        def beginIndex = new MInteger()
        def endIndex = new MInteger()
        def result = new double[parameter]
        core.atr(0, parameter - 1, TypeCast.toDoubleArray(highSeries), TypeCast.toDoubleArray(lowSeries), TypeCast.toDoubleArray(closeSeries), parameter, beginIndex, endIndex, result)
        result?.toList()?.first()
    }
}
