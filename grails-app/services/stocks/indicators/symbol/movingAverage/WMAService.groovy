package stocks.indicators.symbol.movingAverage

import com.tictactec.ta.lib.Core
import com.tictactec.ta.lib.MInteger
import eu.verdelhan.ta4j.indicators.AbstractIndicator
import eu.verdelhan.ta4j.indicators.simple.ClosePriceIndicator
import eu.verdelhan.ta4j.indicators.trackers.WMAIndicator
import stocks.indicators.IndicatorServiceBase
import stocks.tse.Symbol
import stocks.util.TypeCast

class WMAService implements IndicatorServiceBase<Symbol, Integer> {

    def tradesDataService

    @Override
    Boolean getEnabled() {
        false
    }

    @Override
    List<Integer> getCommonParameters() {
        [7,3,26,50,200]
    }

    @Override
    Double calculate(Symbol item, Integer parameter, Date date = new Date()) {

        def series = tradesDataService.getPriceSeries(item, parameter, date)
        if (series.size() < parameter)
            return 0
        def core = new Core()
        def beginIndex = new MInteger()
        def endIndex = new MInteger()
        def result = new double[parameter]
        core.wma(0, parameter - 1, TypeCast.toDoubleArray(series.collect {
            it.closingPrice
        }), parameter, beginIndex, endIndex, result)
        result?.toList()?.first()
    }

    @Override
    Map bulkCalculate(Symbol item, Integer parameter) {

        def series = tradesDataService.getPriceSeries(item)
        def core = new Core()
        def beginIndex = new MInteger()
        def endIndex = new MInteger()
        def result = new double[series.size()]
        core.wma(0, series.size() - 1, TypeCast.toDoubleArray(series.collect {
            it.closingPrice
        }), parameter, beginIndex, endIndex, result)
        [
                series    : series,
                indicators: result
        ]
    }
}
