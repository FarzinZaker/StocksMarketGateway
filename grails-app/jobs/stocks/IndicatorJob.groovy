package stocks

import stocks.indicators.SymbolIndicatorService
import stocks.tse.Symbol
import stocks.tse.SymbolDailyTrade


class IndicatorJob {

    static startDelay = 60000
    static timeout = 100l
    static concurrent = false

    def symbolIndicatorService

    def execute() {

        def dailyTrade = SymbolDailyTrade.createCriteria().list {
            or {
                isNull('indicatorsCalculated')
                eq('indicatorsCalculated', false)
            }
            isNotNull('symbol')
            order('date', ORDER_DESCENDING)
            maxResults(1)
        }?.find()
        if(dailyTrade) {
            symbolIndicatorService.calculateIndicators(Symbol.get(dailyTrade.symbolId), dailyTrade.date)
            dailyTrade.indicatorsCalculated = true
            dailyTrade.save(flush: true)
        }
    }
}
