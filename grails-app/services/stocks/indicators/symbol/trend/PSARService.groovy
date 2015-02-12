package stocks.indicators.symbol.trend

import com.tictactec.ta.lib.Core
import com.tictactec.ta.lib.MInteger
import stocks.indicators.IndicatorServiceBase
import stocks.tse.Symbol
import stocks.util.TypeCast

class PSARService extends IndicatorServiceBase<Symbol, List<Integer>> {

    def tradesDataService

    @Override
    List<List<Integer>> getCommonParameters() {
        [[0.02,0.2]]
    }

    @Override
    Double calculate(Symbol item, List<Integer> parameter, Date date) {

        def highSeries = tradesDataService.getMaxPriceSeries(item, date)
        def lowSeries = tradesDataService.getMinPriceSeries(item, date)
        def core = new Core()
        def beginIndex = new MInteger()
        def endIndex = new MInteger()
        def result = new double[[highSeries.size(), lowSeries.size()].max()]
        core.sar(0, [highSeries.size(), lowSeries.size()].min() - 1, TypeCast.toDoubleArray(highSeries), TypeCast.toDoubleArray(lowSeries), parameter[0], parameter[1], beginIndex, endIndex, result)
        result?.toList()?.first()
    }
}
