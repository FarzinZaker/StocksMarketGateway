package stocks.tse.persist

import stocks.tse.IndustrySubgroup
import stocks.tse.TSEPersistService
import stocks.tse.event.IndustrySubgroupEvent

class IndustrySubgroupPersistService extends TSEPersistService<IndustrySubgroup, IndustrySubgroupEvent> {
    static transactional = false
    @Override
    protected getSampleObject() {
        new IndustrySubgroup()
    }

    @Override
    protected void beforeCreate(IndustrySubgroupEvent event) {

    }

    @Override
    protected void afterCreate(IndustrySubgroupEvent event, IndustrySubgroup data) {

    }

    @Override
    protected void beforeUpdate(IndustrySubgroupEvent event, IndustrySubgroup data) {

    }

    @Override
    protected void afterUpdate(IndustrySubgroupEvent event, IndustrySubgroup data) {

    }
}
