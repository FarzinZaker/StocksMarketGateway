package stocks.tse.persist

import fi.joensuu.joyds1.calendar.JalaliCalendar
import stocks.tse.IndexHistory
import stocks.tse.TSEPersistService
import stocks.tse.event.IndexHistoryEvent

class IndexHistoryPersistService extends TSEPersistService<IndexHistory, IndexHistoryEvent> {

    @Override
    protected getSampleObject() {
        return new IndexHistory()
    }

    @Override
    protected void beforeCreate(IndexHistoryEvent event) {
    }

    @Override
    protected void afterCreate(IndexHistoryEvent event, IndexHistory data) {
        data.date = data.date.clearTime()
        IndexHistory.withTransaction {
            data.save(flush: true)
        }
    }

    @Override
    protected void beforeUpdate(IndexHistoryEvent event, IndexHistory data) {
        false
    }

    @Override
    protected void afterUpdate(IndexHistoryEvent event, IndexHistory data) {

    }
}
