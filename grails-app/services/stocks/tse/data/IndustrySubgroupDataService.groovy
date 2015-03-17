package stocks.tse.data

import stocks.tse.IndustrySubgroup
import stocks.tse.TSEDataService
import stocks.tse.event.IndustrySubgroupEvent

class IndustrySubgroupDataService extends TSEDataService<IndustrySubgroup, IndustrySubgroupEvent> {
    static transactional = false
    static schedules = [
            [
                    method : 'importData',
                    trigger: [
                            type      : 'Simple',
                            parameters: [repeatInterval: 300000l, startDelay: 60000]
                    ]
//                    trigger: [
//                            type      : 'Cron',
//                            parameters: [cronExpression: '0 50 1 * * ?']
//                    ]
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
