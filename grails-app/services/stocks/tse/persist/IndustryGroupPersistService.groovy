package stocks.tse.persist

import stocks.tse.IndustryGroup
import stocks.tse.TSEPersistService
import stocks.tse.event.IndustryGroupEvent

class IndustryGroupPersistService extends TSEPersistService<IndustryGroup, IndustryGroupEvent> {
    static transactional = false
    @Override
    protected getSampleObject() {
        new IndustryGroup()
    }

    @Override
    protected void beforeCreate(IndustryGroupEvent event) {

    }

    @Override
    protected void afterCreate(IndustryGroupEvent event, IndustryGroup data) {

    }

    @Override
    protected void beforeUpdate(IndustryGroupEvent event, IndustryGroup data) {

    }

    @Override
    protected void afterUpdate(IndustryGroupEvent event, IndustryGroup data) {

    }
}
