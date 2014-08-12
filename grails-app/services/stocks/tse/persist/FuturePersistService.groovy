package stocks.tse.persist

import stocks.tse.Future
import stocks.tse.TSEPersistService
import stocks.tse.event.FutureEvent

class FuturePersistService extends TSEPersistService<Future, FutureEvent> {
    @Override
    protected getSampleObject() {
        new Future()
    }

    @Override
    protected void beforeCreate(FutureEvent event) {

    }

    @Override
    protected void afterCreate(FutureEvent event, Future data) {

    }

    @Override
    protected void beforeUpdate(FutureEvent event, Future data) {

    }

    @Override
    protected void afterUpdate(FutureEvent event, Future data) {

    }
}
