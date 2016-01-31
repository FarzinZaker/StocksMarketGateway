package stocks

import grails.converters.JSON
import groovy.time.TimeCategory
import stocks.tse.Symbol
import stocks.tse.SymbolAdjustedDailyTrade

class DataController {

    def adjustedPriceSeries9Service

    def ohlcv() {
        def symbolId = params.id as Long
        def adjustmentType = params.adjustmentType as String
        def startDate = params.start ? new Date(params.start as Long) : null
        def endDate = params.end ? new Date(params.end as Long) : new Date()
        if (!startDate)
            use(TimeCategory) {
                startDate = new Date() - 1.years
            }

        render(
                adjustedPriceSeries9Service.dailyTradeList(symbolId, startDate, endDate, '', adjustmentType)
                        .collect {
                    [
                            it.date.time,
                            it.firstTradePrice,
                            it.maxPrice,
                            it.minPrice,
                            it.lastTradePrice,
                            it.totalTradeVolume
                    ]
                } as JSON)
    }
}
