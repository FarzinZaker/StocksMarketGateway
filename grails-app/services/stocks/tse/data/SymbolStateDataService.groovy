package stocks.tse.data

import org.apache.axis.types.UnsignedByte
import stocks.tse.SymbolState
import stocks.tse.TSEDataService
import stocks.tse.event.SymbolStateEvent

class SymbolStateDataService extends TSEDataService<SymbolState, SymbolStateEvent> {
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
    protected SymbolStateEvent getSampleEventObject() {
        new SymbolStateEvent()
    }

    @Override
    protected SymbolState find(SymbolStateEvent object) {
        SymbolState.findBySymbolInternalCode(object.symbolInternalCode)
    }

    public void importData(Boolean ignoreMarketStatus = false){

        if(!ignoreMarketStatus && marketStatusService.isCloseWithMargin(marketStatusService.MARKET_STOCK))
            return

        (0..5).each { index ->
            Thread.startDaemon {
                importData('instrumentsState',
                        [[new UnsignedByte(index)]]
                )
            }
        }
    }
}
