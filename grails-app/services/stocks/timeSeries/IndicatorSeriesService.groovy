package stocks.timeSeries

import groovy.time.TimeCategory
import stocks.indicators.IndicatorBase
import stocks.tse.AdjustmentHelper

class IndicatorSeriesService {

    def timeSeriesDBService

    def write(IndicatorBase indicator) {
        write([indicator])
    }

    def write(List<IndicatorBase> indicators) {

        def serie = new Serie()
        indicators.each { indicator ->
            if (indicator.symbolId) {
                serie.addPoint(new Point("indicator_${indicator.class.canonicalName.replace('.', '_').replace('stocks_indicators_symbol_', '')}_${indicator.parameter?.replace(',', '_')}_${indicator.adjustmentType}_${indicator.symbolId}")
                        .time(indicator.calculationDate)
                        .value(indicator.value))
            }

        }

        if (serie.points?.size())
            timeSeriesDBService.write(serie)
    }

    def indicatorList(Long symbolId, Class<IndicatorBase> indicator, String parameter, Date startDate = null, Date endDate = null, String groupingMode = '1d', String adjustmentType = null) {
        if (!startDate)
            use(TimeCategory) {
                startDate = new Date() - 20.years
            }
        if (!endDate)
            endDate = new Date()
        if (!adjustmentType)
            adjustmentType = AdjustmentHelper.defaultType
        def values
        if (groupingMode == '')
            values = timeSeriesDBService.query("SELECT value FROM indicator_${indicator.canonicalName.replace('.', '_').replace('stocks_indicators_symbol_', '')}_${parameter?.replace(',', '_')}_${adjustmentType}_${symbolId} WHERE time >= ${startDate.time * 1000}u and time <= ${endDate.time * 1000}u")[0]?.series?.values
        else
            values = timeSeriesDBService.query("SELECT LAST(value) FROM indicator_${indicator.canonicalName.replace('.', '_').replace('stocks_indicators_symbol_', '')}_${parameter?.replace(',', '_')}_${adjustmentType}_${symbolId} WHERE time >= ${startDate.time * 1000}u and time <= ${endDate.time * 1000}u GROUP BY time(${groupingMode})")[0]?.series?.values
        values ? values[0].findAll { it[1] }.collect {
            [date: Date.parse("yyyy-MM-dd'T'hh:mm:ss'Z'", it[0]), value: it[1] as Double]
        }.findAll { it.value } : []
    }

    Double lastIndicator(Long symbolId, Class<IndicatorBase> indicator, String parameter, Date endDate = null, String adjustmentType = null) {
        if (!endDate)
            endDate = new Date()
        if (!adjustmentType)
            adjustmentType = AdjustmentHelper.defaultType
        def values = timeSeriesDBService.query("SELECT LAST(value) FROM indicator_${indicator.canonicalName.replace('.', '_').replace('stocks_indicators_symbol_', '')}_${parameter?.replace(',', '_')}_${adjustmentType}_${symbolId} WHERE time <= ${endDate.time * 1000}u")[0]?.series?.values
        values ? values[0].find()[1] as Double : null
    }
}
