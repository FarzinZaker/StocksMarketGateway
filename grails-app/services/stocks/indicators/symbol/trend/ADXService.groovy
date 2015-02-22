package stocks.indicators.symbol.trend

import com.tictactec.ta.lib.Core
import com.tictactec.ta.lib.MInteger
import stocks.indicators.IndicatorServiceBase
import stocks.tse.Symbol
import stocks.util.TypeCast

class ADXService implements IndicatorServiceBase<Symbol, Integer> {

    def tradesDataService

    @Override
    Boolean getEnabled() {
        false
    }

    @Override
    List<Integer> getCommonParameters() {
        [7, 14, 28]
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
        core.adx(0, parameter - 1, TypeCast.toDoubleArray(highSeries), TypeCast.toDoubleArray(lowSeries), TypeCast.toDoubleArray(closeSeries), parameter, beginIndex, endIndex, result)
        result?.toList()?.first()
    }
}
