package stocks.tse.data

import stocks.tse.IndustrySubgroup
import stocks.tse.TSEDataService
import stocks.tse.event.IndustrySubgroupEvent

class IndustrySubgroupDataService extends TSEDataService<IndustrySubgroup, IndustrySubgroupEvent> {
    @Override
    protected IndustrySubgroupEvent getSampleEventObject() {
        new IndustrySubgroupEvent()
    }

    @Override
    protected IndustrySubgroup find(IndustrySubgroupEvent object) {
        IndustrySubgroup.findByCode(object.code)
    }

    public void importData(){
        importData('subSector', [[]])
    }
}
