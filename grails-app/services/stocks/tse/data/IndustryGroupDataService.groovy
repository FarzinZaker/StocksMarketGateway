package stocks.tse.data

import stocks.tse.IndustryGroup
import stocks.tse.TSEDataService
import stocks.tse.event.IndustryGroupEvent

class IndustryGroupDataService extends TSEDataService<IndustryGroup, IndustryGroupEvent> {

    @Override
    protected IndustryGroupEvent getSampleEventObject() {
        new IndustryGroupEvent()
    }

    @Override
    protected IndustryGroup find(IndustryGroupEvent object) {
        IndustryGroup.findByCode(object.code)
    }

    public void importData(){
        importData('sector', [[]])
    }
}
