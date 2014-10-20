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
                    trigger: [
                            type      : 'Cron',
                            parameters: [cronExpression: '0 0 1 * * ?']
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

    public void importData(){
        importData('instrument',
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
