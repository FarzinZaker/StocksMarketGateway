package stocks.tse.data

import stocks.tse.Future
import stocks.tse.TSEDataService
import stocks.tse.event.FutureEvent

class FutureDataService extends TSEDataService<Future, FutureEvent> {
    static transactional = false
    def marketStatusService

    @Override
    protected FutureEvent getSampleEventObject() {
        new FutureEvent()
    }

    @Override
    protected Future find(FutureEvent object) {
        Future.findByCode(object.code)
    }

    public void importData(Date date){

        if(marketStatusService.isCloseWithMargin(marketStatusService.MARKET_FUTURE))
            return

        importData('futureInformation',[[date.format('yyyyMMdd').toInteger()]])
    }
}
