package stocks.timeSeries

import groovy.time.TimeCategory
import stocks.indicators.IndicatorBase
import stocks.tse.AdjustmentHelper

class IndicatorSeries9Service {

    def timeSeriesDB9Service

    def write(IndicatorBase indicator) {
        write([indicator])
    }

    def write(List<IndicatorBase> indicators) {

        def serie = new Serie()
        indicators.each { indicator ->
            if (indicator.symbolId) {
                serie.addPoint(new Point(indicator.class.canonicalName.replace('.', '_').replace('stocks_indicators_symbol_', ''))
                        .tags([parameter:indicator.parameter?.replace(',', '_'), adjustmentType:indicator.adjustmentType, symbolId: indicator.symbolId])
                        .time(indicator.calculationDate)
                        .value(indicator.value))
            }

        }

        if (serie.points?.size())
            timeSeriesDB9Service.write(serie)
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
            values = timeSeriesDB9Service.query("SELECT value FROM ${indicator.canonicalName.replace('.', '_').replace('stocks_indicators_symbol_', '')} WHERE parameter = '${parameter?.replace(',', '_')}' AND adjustmentType = '${adjustmentType}' AND symbolId = '${symbolId}' AND time >= ${startDate.time * 1000}u and time <= ${endDate.time * 1000}u")[0]?.series?.values
        else
            values = timeSeriesDB9Service.query("SELECT LAST(value) FROM ${indicator.canonicalName.replace('.', '_').replace('stocks_indicators_symbol_', '')} WHERE parameter = '${parameter?.replace(',', '_')}' AND adjustmentType = '${adjustmentType}' AND symbolId = '${symbolId}' AND time >= ${startDate.time * 1000}u and time <= ${endDate.time * 1000}u GROUP BY time(${groupingMode})")[0]?.series?.values
        values ? values[0].findAll { it[1] }.collect {
            [date: Date.parse("yyyy-MM-dd'T'hh:mm:ss'Z'", it[0]), value: it[1] as Double]
        }.findAll { it.value } : []
    }

    Double lastIndicator(Long symbolId, Class<IndicatorBase> indicator, String parameter, Date endDate = null, String adjustmentType = null) {
        if (!endDate)
            endDate = new Date()
        if (!adjustmentType)
            adjustmentType = AdjustmentHelper.defaultType
        def values = timeSeriesDB9Service.query("SELECT LAST(value) FROM ${indicator.canonicalName.replace('.', '_').replace('stocks_indicators_symbol_', '')} WHERE parameter = '${parameter?.replace(',', '_')}' AND adjustmentType = '${adjustmentType}' AND symbolId = '${symbolId}' AND time <= ${endDate.time * 1000}u")[0]?.series?.values
        values ? values[0].find()[1] as Double : null
    }
}
