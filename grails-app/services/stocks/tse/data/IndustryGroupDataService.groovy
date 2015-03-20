package stocks.tse.data

import stocks.tse.IndustryGroup
import stocks.tse.TSEDataService
import stocks.tse.event.IndustryGroupEvent

class IndustryGroupDataService extends TSEDataService<IndustryGroup, IndustryGroupEvent> {
    static transactional = false
    static schedules = [
            [
                    method : 'importData',
//                    trigger: [
//                            type      : 'Simple',
//                            parameters: [repeatInterval: 300000l, startDelay: 60000]
//                    ]
                    trigger: [
                            type      : 'Cron',
                            parameters: [cronExpression: '0 30 1 * * ?']
                    ]
            ]
    ]

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
