package stocks.tse.data

import org.apache.axis.types.UnsignedByte
import stocks.tse.Symbol
import stocks.tse.TSEDataService
import stocks.tse.event.SymbolEvent

class SymbolDataService extends TSEDataService<Symbol, SymbolEvent> {
    static transactional = false
    static schedules = [
            [
                    method : 'importData',
//                    trigger: [
//                            type      : 'Simple',
//                            parameters: [repeatInterval: 300000l, startDelay: 60000]
//                    ]
                    trigger: [
                            type      : 'Cron',
                            parameters: [cronExpression: '0 0 2 * * ?']
                    ]
            ]
    ]

    @Override
    protected SymbolEvent getSampleEventObject() {
        new SymbolEvent()
    }

    @Override
    protected Symbol find(SymbolEvent object) {
        Symbol.findByCode(object.code)
    }

    public void importData() {
        (0..5).each { index ->
            Thread.startDaemon {
                importData('instrument',
                        [[new UnsignedByte(index)]]
                )
            }
        }
    }
}
