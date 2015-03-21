package stocks.tse.data

import stocks.tse.IndustryGroupState
import stocks.tse.TSEDataService
import stocks.tse.event.IndustryGroupStateEvent

class IndustryGroupStateDataService extends TSEDataService<IndustryGroupState, IndustryGroupStateEvent> {
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
                            parameters: [cronExpression: '0 25 1 * * ?']
                    ]
            ]
    ]

    @Override
    protected IndustryGroupStateEvent getSampleEventObject() {
        new IndustryGroupStateEvent()
    }

    @Override
    protected IndustryGroupState find(IndustryGroupStateEvent object) {
        IndustryGroupState.findByIndustryGroupIdentifierAndDate(object.industryGroupIdentifier, object.date)
    }

    public void importData(){
        importData('sectorState', [[(new Date() - 1).format("yyyyMMdd") as Integer]])
    }
}
