package stocks.tse.data

import stocks.tse.Symbol
import stocks.tse.SymbolAverageTrade
import stocks.tse.SymbolDailyTrade
import stocks.tse.event.SymbolAverageTradeEvent

class SymbolAverageTradeDataService {
    static transactional = false
    def tseEventGateway
    def lowLevelDataService

    static schedules = [
            [
                    method : 'importData',
                    trigger: [
                            type      : 'Cron',
                            parameters: [cronExpression: '0 0 23 * * ?']
                    ]
            ]
    ]

    protected SymbolAverageTrade find(SymbolAverageTradeEvent object) {
        SymbolAverageTrade.findBySymbolAndDaysCount(object.symbol, object.daysCount)

    }


    public void importData() {

        def lastTradingDate = SymbolDailyTrade.createCriteria().list() {
            projections {
                max('date')
            }
        }.find()

        def symbols = SymbolDailyTrade.createCriteria().list {
            gte('date', lastTradingDate.clearTime())
            projections {
                property('symbol')
            }
        } as List<Symbol>

        symbols.each { Symbol symbol ->
            [5, 10, 30, 90, 180, 365].each { daysCount ->
                def event = new SymbolAverageTradeEvent()
                event.symbol = symbol
                event.daysCount = daysCount
                event.tradeVolume = (lowLevelDataService.executeFunctionWithNumericReturnValue('SYM_TRADE_VOL_AVG', [symId: symbol?.id, days: daysCount]) ?: 0) as Double
                event.tradeCount = (lowLevelDataService.executeFunctionWithNumericReturnValue('SYM_TRADE_COUNT_AVG', [symId: symbol?.id, days: daysCount]) ?: 0) as Double
                event.tradeValue = (lowLevelDataService.executeFunctionWithNumericReturnValue('SYM_TRADE_VALUE_AVG', [symId: symbol?.id, days: daysCount]) ?: 0) as Double

                event.data = find(event)
                tseEventGateway.send(event, this.class.canonicalName)
            }
        }
    }
}
