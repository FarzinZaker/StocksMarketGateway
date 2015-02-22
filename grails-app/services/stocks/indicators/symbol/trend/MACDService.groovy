package stocks.indicators.symbol.trend

import com.tictactec.ta.lib.Core
import com.tictactec.ta.lib.MInteger
import stocks.indicators.IndicatorServiceBase
import stocks.tse.Symbol
import stocks.util.TypeCast

class MACDService implements IndicatorServiceBase<Symbol, List<Integer>> {

    def tradesDataService

    @Override
    Boolean getEnabled() {
        true
    }

    @Override
    List<List<Integer>> getCommonParameters() {
        [[12, 26, 9]]
    }

    @Override
    Double calculate(Symbol item, List<Integer> parameter, Date date) {

        def closeSeries = tradesDataService.getClosingPriceSeries(item, parameter.max() * 2, date)
        def core = new Core()
        def beginIndex = new MInteger()
        def endIndex = new MInteger()
        def result = new double[closeSeries.size()]
        def histogram = new double[closeSeries.size()]
        def signal = new double[closeSeries.size()]
        core.macd(0, parameter.max() + 7, TypeCast.toDoubleArray(closeSeries), parameter[0], parameter[1], parameter[2], beginIndex, endIndex, result, signal, histogram)
        result?.toList()?.first()
    }
}
