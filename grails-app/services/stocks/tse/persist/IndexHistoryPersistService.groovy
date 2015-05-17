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
        def date = data.date
        date = date.clearTime()
        data.dailySnapshot = date
        def calendar = Calendar.getInstance() as GregorianCalendar
        calendar.setTime(date)
        def jc = new JalaliCalendar(calendar)
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY)
            data.weeklySnapshot = date
        if (jc.getDay() == jc.getLastDayOfMonth(jc.getYear(), jc.getMonth()))
            data.monthlySnapshot = date
        IndexHistory.withTransaction {
            data.save(flush: true)
//            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY)
//            println data.weeklySnapshot
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
