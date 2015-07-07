package stocks.tse.data

import stocks.tse.SymbolClientType
import stocks.tse.TSEDataService
import stocks.tse.event.SymbolClientTypeEvent

class SymbolClientTypeDataService extends TSEDataService<SymbolClientType, SymbolClientTypeEvent> {

    static transactional = false
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
        SymbolClientType.findBySymbolInternalCodeAndDate(object.symbolInternalCode, object.date)
    }

    public void importData(){
        if(Calendar.instance.get(Calendar.HOUR_OF_DAY) < 7)
            return
        importData('clientType', [[]])
    }
}
