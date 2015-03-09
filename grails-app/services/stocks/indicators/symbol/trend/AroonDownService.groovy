package stocks.indicators.symbol.trend

import com.tictactec.ta.lib.Core
import com.tictactec.ta.lib.MInteger
import stocks.indicators.IndicatorServiceBase
import stocks.tse.Symbol
import stocks.util.TypeCast

class AroonDownService implements IndicatorServiceBase<Symbol, Integer> {

    def tradesDataService

    @Override
    Boolean getEnabled() {
        false
    }

    @Override
    List<Integer> getCommonParameters() {
        [3, 7, 26, 50, 200]
    }

    @Override
    Double calculate(Symbol item, Integer parameter, Date date) {

        def series = tradesDataService.getPriceSeries(item, parameter, date)
        if (series.size() < parameter)
            return 0
        def core = new Core()
        def beginIndex = new MInteger()
        def endIndex = new MInteger()
        def result = new double[parameter]
        def ignore = new double[parameter]
        core.aroon(0, parameter - 1, TypeCast.toDoubleArray(series.collect {
            it.maxPrice
        }), TypeCast.toDoubleArray(series.collect { it.minPrice }), parameter, beginIndex, endIndex, result, ignore)
        result?.toList()?.first()
    }

    @Override
    Map<String, List> bulkCalculate(Symbol item, Integer parameter) {

        def series = tradesDataService.getPriceSeries(item)
        def core = new Core()
        def beginIndex = new MInteger()
        def endIndex = new MInteger()
        def result = new double[series.size()]
        def ignore = new double[series.size()]
        core.aroon(0, series.size() - 1, TypeCast.toDoubleArray(series.collect {
            it.maxPrice
        }), TypeCast.toDoubleArray(series.collect { it.minPrice }), parameter, beginIndex, endIndex, result, ignore)
        [
                series    : series,
                indicators: result?.toList()
        ]
    }
}
