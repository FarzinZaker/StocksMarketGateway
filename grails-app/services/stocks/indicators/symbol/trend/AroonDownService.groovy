package stocks.indicators.symbol.trend

import com.tictactec.ta.lib.Core
import com.tictactec.ta.lib.MInteger
import stocks.indicators.IndicatorServiceBase
import stocks.tse.Symbol
import stocks.util.TypeCast

class AroonDownService extends IndicatorServiceBase<Symbol, Integer> {

    def tradesDataService

    @Override
    List<Integer> getCommonParameters() {
        [3, 7, 26, 50, 200]
    }

    @Override
    Double calculate(Symbol item, Integer parameter, Date date) {

        def highSeries = tradesDataService.getMaxPriceSeries(item, parameter, date)
        def lowSeries = tradesDataService.getMinPriceSeries(item, parameter, date)
        def core = new Core()
        def beginIndex = new MInteger()
        def endIndex = new MInteger()
        def result = new double[parameter]
        def ignore = new double[parameter]
        core.aroon(0, parameter - 1, TypeCast.toDoubleArray(highSeries), TypeCast.toDoubleArray(lowSeries), parameter, beginIndex, endIndex, result, ignore)
        result?.toList()?.first()
    }
}
