package stocks.tse.persist

import stocks.tse.IndustryGroupState
import stocks.tse.TSEPersistService
import stocks.tse.event.IndustryGroupStateEvent

class IndustryGroupStatePersistService extends TSEPersistService<IndustryGroupState, IndustryGroupStateEvent> {
    @Override
    protected getSampleObject() {
        new IndustryGroupState()
    }

    @Override
    protected void beforeCreate(IndustryGroupStateEvent event) {

    }

    @Override
    protected void afterCreate(IndustryGroupStateEvent event, IndustryGroupState data) {

    }

    @Override
    protected void beforeUpdate(IndustryGroupStateEvent event, IndustryGroupState data) {

    }

    @Override
    protected void afterUpdate(IndustryGroupStateEvent event, IndustryGroupState data) {

    }
}
