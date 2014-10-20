package stocks.tse.data

import stocks.tse.SupervisorMessage
import stocks.tse.TSEDataService
import stocks.tse.event.SupervisorMessageEvent

class SupervisorMessageDataService extends TSEDataService<SupervisorMessage, SupervisorMessageEvent> {
    static transactional = false
    static schedules = [
            [
                    method : 'importData',
                    trigger: [
                            type      : 'Simple',
                            parameters: [repeatInterval: 5000l, startDelay: 60000]
                    ]
            ]
    ]

    @Override
    protected SupervisorMessageEvent getSampleEventObject() {
        new SupervisorMessageEvent()
    }

    @Override
    protected SupervisorMessage find(SupervisorMessageEvent object) {
        SupervisorMessage.findByTitle(object.title)
    }

    public void importData(){
        importData('msg', [[]])
    }
}
