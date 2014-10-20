package stocks.tse.persist

import stocks.tse.MarketActivity
import stocks.tse.TSEPersistService
import stocks.tse.event.MarketActivityEvent

class MarketActivityPersistService extends TSEPersistService<MarketActivity, MarketActivityEvent> {
    static transactional = false
    @Override
    protected getSampleObject() {
        new MarketActivity()
    }

    @Override
    protected void beforeCreate(MarketActivityEvent event) {

    }

    @Override
    protected void afterCreate(MarketActivityEvent event, MarketActivity data) {

    }

    @Override
    protected void beforeUpdate(MarketActivityEvent event, MarketActivity data) {

    }

    @Override
    protected void afterUpdate(MarketActivityEvent event, MarketActivity data) {

    }
}
