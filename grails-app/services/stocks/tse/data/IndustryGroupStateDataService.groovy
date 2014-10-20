package stocks.tse.data

import stocks.tse.IndustryGroupState
import stocks.tse.TSEDataService
import stocks.tse.event.IndustryGroupStateEvent

class IndustryGroupStateDataService extends TSEDataService<IndustryGroupState, IndustryGroupStateEvent> {
    static transactional = false
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
