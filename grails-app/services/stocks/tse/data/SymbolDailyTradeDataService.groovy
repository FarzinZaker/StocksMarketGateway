package stocks.tse.data

import org.apache.axis.types.UnsignedByte
import stocks.tse.SymbolDailyTrade
import stocks.tse.TSEDataService
import stocks.tse.event.SymbolDailyTradeEvent

class SymbolDailyTradeDataService extends TSEDataService<SymbolDailyTrade, SymbolDailyTradeEvent> {
    static transactional = false

    def marketStatusService

    static schedules = [
            [
                    method : 'importData',
                    trigger: [
                            type      : 'Simple',
                            parameters: [repeatInterval: 30000l, startDelay: 60000]
                    ]
            ]
    ]


    @Override
    protected SymbolDailyTradeEvent getSampleEventObject() {
        new SymbolDailyTradeEvent()
    }

    @Override
    protected SymbolDailyTrade find(SymbolDailyTradeEvent object) {
        SymbolDailyTrade.findByDateGreaterThanEqualsAndSymbolInternalCode(object.date?.clearTime(), object.symbolInternalCode)
    }

    public void importData(Boolean ignoreMarketStatus = false){

//        if(!ignoreMarketStatus && marketStatusService.isCloseWithMargin(marketStatusService.MARKET_STOCK))
//            return
        (0..5).each { index ->
            Thread.startDaemon {
                importData('tradeLastDay',
                        [[new UnsignedByte(index)]]
                )
            }
        }

    }
}
