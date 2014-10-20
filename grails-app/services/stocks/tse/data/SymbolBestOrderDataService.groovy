package stocks.tse.data

import org.apache.axis.types.UnsignedByte
import stocks.tse.SymbolBestOrder
import stocks.tse.TSEDataService
import stocks.tse.event.SymbolBestOrderEvent

class SymbolBestOrderDataService extends TSEDataService<SymbolBestOrder, SymbolBestOrderEvent> {
    static transactional = false
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
    protected SymbolBestOrderEvent getSampleEventObject() {
        new SymbolBestOrderEvent()
    }

    @Override
    protected SymbolBestOrder find(SymbolBestOrderEvent object) {
        SymbolBestOrder.findBySymbolInternalCodeAndNumber(object.symbolInternalCode, object.number)
    }

    public void importData(){
        importData('bestLimitsAllIns',
                [
                        [new UnsignedByte(0)],
                        [new UnsignedByte(1)],
                        [new UnsignedByte(2)],
                        [new UnsignedByte(3)],
                        [new UnsignedByte(4)],
                        [new UnsignedByte(5)]
                ])
    }

    public void importData(Long symbolInternalCode){
        importData('bestLimitOneIns',[[symbolInternalCode]])
    }
}
