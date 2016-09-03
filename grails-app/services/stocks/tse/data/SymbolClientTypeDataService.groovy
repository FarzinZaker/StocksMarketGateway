package stocks.tse.data

import stocks.tse.SymbolClientType
import stocks.tse.TSEDataService
import stocks.tse.event.SymbolClientTypeEvent

class SymbolClientTypeDataService extends TSEDataService<SymbolClientType, SymbolClientTypeEvent> {

    static transactional = false

    def marketStatusService

    static schedules = [
            [
                    method : 'importData',
                    trigger: [
                            type      : 'Simple',
                            parameters: [repeatInterval: 5000l, startDelay: 60000]
                    ]
            ]
    ]

    @Override
    protected SymbolClientTypeEvent getSampleEventObject() {
        return new SymbolClientTypeEvent()
    }

    @Override
    protected SymbolClientType find(SymbolClientTypeEvent object) {
        SymbolClientType.findBySymbolInternalCodeAndDate(object.symbolInternalCode, new Date().clearTime())
    }

    public void importData(Boolean ignoreMarketStatus = false){

//        if(!ignoreMarketStatus && marketStatusService.isCloseWithMargin(marketStatusService.MARKET_STOCK))
//            return

        if(Calendar.instance.get(Calendar.HOUR_OF_DAY) < 7)
            return
        importData('clientType', [[]])
    }
}
