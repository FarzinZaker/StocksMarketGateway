package stocks.tse.data

import org.apache.axis.types.UnsignedByte
import stocks.tse.SymbolDailyTrade
import stocks.tse.TSEDataService
import stocks.tse.event.SymbolDailyTradeEvent

class SymbolDailyTradeDataService extends TSEDataService<SymbolDailyTrade, SymbolDailyTradeEvent> {
    static transactional = false
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

    public void importData(){
        importData('tradeLastDay',
                [
                        [new UnsignedByte(0)],
                        [new UnsignedByte(1)],
                        [new UnsignedByte(2)],
                        [new UnsignedByte(3)],
                        [new UnsignedByte(4)],
                        [new UnsignedByte(5)]
                ])
    }
}
