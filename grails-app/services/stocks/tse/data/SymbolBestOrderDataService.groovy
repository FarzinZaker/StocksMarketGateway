package stocks.tse.data

import org.apache.axis.types.UnsignedByte
import stocks.tse.SymbolBestOrder
import stocks.tse.TSEDataService
import stocks.tse.event.SymbolBestOrderEvent

class SymbolBestOrderDataService extends TSEDataService<SymbolBestOrder, SymbolBestOrderEvent> {
    static transactional = false
    def marketStatusService
    static schedules = [
            [
                    method : 'importData',
                    trigger: [
                            type      : 'Simple',
                            parameters: [repeatInterval: 600000l, startDelay: 60000]
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

    public void importData(Boolean ignoreMarketStatus = false){

//        if(!ignoreMarketStatus && marketStatusService.isCloseWithMargin(marketStatusService.MARKET_STOCK))
//            return

        (0..5).each { index ->
            Thread.startDaemon {
                importData('bestLimitsAllIns',
                        [[new UnsignedByte(index)]]
                )
            }
        }
    }

    public void importData(Long symbolInternalCode){
        importData('bestLimitOneIns',[[symbolInternalCode]])
    }
}
