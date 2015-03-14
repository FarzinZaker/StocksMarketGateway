package stocks

import grails.converters.JSON
import groovy.time.TimeCategory
import stocks.tse.Symbol
import stocks.tse.SymbolDailyTrade

class DataController {

    def ohlcv() {
        def symbol = Symbol.get(params.id as Long)
        def startDate = params.start ? new Date(params.start as Long) : null
        def endDate = params.start ? new Date(params.end as Long) : new Date()
        if (!startDate)
            use(TimeCategory) {
                startDate = new Date() - 1.years
            }

        render(
                SymbolDailyTrade.findAllBySymbolAndDailySnapshotBetween(symbol, startDate, endDate).sort {
                    it.dailySnapshot.time
                }.collect {
                    [
                            it.dailySnapshot.time,
                            it.firstTradePrice,
                            it.maxPrice,
                            it.minPrice,
                            it.closingPrice,
                            it.totalTradeVolume
                    ]
                } as JSON)
    }
}
