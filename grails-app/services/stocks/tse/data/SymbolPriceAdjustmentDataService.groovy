package stocks.tse.data

import org.apache.axis.types.UnsignedByte
import stocks.tse.SymbolPriceAdjustment
import stocks.tse.TSEDataService
import stocks.tse.event.SymbolPriceAdjustmentEvent

class SymbolPriceAdjustmentDataService extends TSEDataService<SymbolPriceAdjustment, SymbolPriceAdjustmentEvent> {
    static transactional = false
    static schedules = [
            [
                    method : 'importData',
                    trigger: [
                            type      : 'Cron',
                            parameters: [cronExpression: '0 0 4 * * ?']
                    ]
            ]
    ]

    @Override
    protected SymbolPriceAdjustmentEvent getSampleEventObject() {
        new SymbolPriceAdjustmentEvent()
    }

    @Override
    protected SymbolPriceAdjustment find(SymbolPriceAdjustmentEvent object) {
        SymbolPriceAdjustment.findBySymbolAndDate(object.symbol, object.date)
    }

    public void importData(){
        importData('adjPrice',
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
