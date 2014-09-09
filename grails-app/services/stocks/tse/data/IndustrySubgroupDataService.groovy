package stocks.tse.data

import stocks.tse.IndustrySubgroup
import stocks.tse.TSEDataService
import stocks.tse.event.IndustrySubgroupEvent

class IndustrySubgroupDataService extends TSEDataService<IndustrySubgroup, IndustrySubgroupEvent> {

    static schedules = [
            [
                    method : 'importData',
                    trigger: [
                            type      : 'Cron',
                            parameters: [cronExpression: '0 0 1 * * ?']
                    ]
            ]
    ]

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
