package stocks.indicators.symbol.volatility

import com.tictactec.ta.lib.Core
import com.tictactec.ta.lib.MInteger
import stocks.indicators.IndicatorServiceBase
import stocks.tse.Symbol
import stocks.util.TypeCast

class NATRService implements IndicatorServiceBase<Symbol, Integer> {

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

        def series = tradesDataService.getPriceSeries(item, parameter, date)
        if (series.size() < parameter)
            return 0
        def core = new Core()
        def beginIndex = new MInteger()
        def endIndex = new MInteger()
        def result = new double[parameter]
        core.natr(0, parameter - 1, TypeCast.toDoubleArray(series.collect {
            it.maxPrice
        }), TypeCast.toDoubleArray(series.collect { it.minPrice }), TypeCast.toDoubleArray(series.collect {
            it.closingPrice
        }), parameter, beginIndex, endIndex, result)
        result?.toList()?.first()
    }

    @Override
    Map<String, List> bulkCalculate(Symbol item, Integer parameter) {

        def series = tradesDataService.getPriceSeries(item)
        def core = new Core()
        def beginIndex = new MInteger()
        def endIndex = new MInteger()
        def result = new double[series.size()]
        core.natr(0, series.size() - 1, TypeCast.toDoubleArray(series.collect {
            it.maxPrice
        }), TypeCast.toDoubleArray(series.collect { it.minPrice }), TypeCast.toDoubleArray(series.collect {
            it.closingPrice
        }), parameter, beginIndex, endIndex, result)
        [
                series: series,
                indicators: result?.toList()
        ]
    }
}
