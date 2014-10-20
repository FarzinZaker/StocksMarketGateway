package stocks.tse.persist

import stocks.tse.SupervisorMessage
import stocks.tse.TSEPersistService
import stocks.tse.event.SupervisorMessageEvent

class SupervisorMessagePersistService extends TSEPersistService<SupervisorMessage, SupervisorMessageEvent> {
    static transactional = false
    @Override
    protected getSampleObject() {
        new SupervisorMessage()
    }

    @Override
    protected void beforeCreate(SupervisorMessageEvent event) {

    }

    @Override
    protected void afterCreate(SupervisorMessageEvent event, SupervisorMessage data) {

    }

    @Override
    protected void beforeUpdate(SupervisorMessageEvent event, SupervisorMessage data) {

    }

    @Override
    protected void afterUpdate(SupervisorMessageEvent event, SupervisorMessage data) {

    }
}
