package stocks.tse.data

import stocks.tse.Future
import stocks.tse.TSEDataService
import stocks.tse.event.FutureEvent

class FutureDataService extends TSEDataService<Future, FutureEvent> {

    @Override
    protected FutureEvent getSampleEventObject() {
        new FutureEvent()
    }

    @Override
    protected Future find(FutureEvent object) {
        Future.findByCode(object.code)
    }

    public void importData(Date date){
        importData('futureInformation',[[date.format('yyyyMMdd').toInteger()]])
    }
}
