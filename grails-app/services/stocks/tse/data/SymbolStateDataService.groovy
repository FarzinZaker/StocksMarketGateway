package stocks.tse.data

import org.apache.axis.types.UnsignedByte
import stocks.tse.SymbolState
import stocks.tse.TSEDataService
import stocks.tse.event.SymbolStateEvent

class SymbolStateDataService extends TSEDataService<SymbolState, SymbolStateEvent> {

    static schedules = [
            [
                    method : 'importData',
                    trigger: [
                            type      : 'Simple',
                            parameters: [repeatInterval: 60000l, startDelay: 60000]
                    ]
            ]
    ]

    @Override
    protected SymbolStateEvent getSampleEventObject() {
        new SymbolStateEvent()
    }

    @Override
    protected SymbolState find(SymbolStateEvent object) {
        SymbolState.findBySymbolInternalCode(object.symbolInternalCode)
    }

    public void importData(){
        importData('instrumentsState',
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
