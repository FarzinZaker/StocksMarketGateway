package stocks.tse.data

import fi.joensuu.joyds1.calendar.JalaliCalendar
import groovy.time.TimeCategory
import org.apache.axis.types.UnsignedByte
import stocks.tse.IndexHistory
import stocks.tse.TSEDataService
import stocks.tse.event.IndexHistoryEvent

class IndexHistoryDataService extends TSEDataService<IndexHistory, IndexHistoryEvent> {

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
    protected Boolean getAutoLogStateEnabled() {
        false
    }

    @Override
    protected IndexHistoryEvent getSampleEventObject() {
        new IndexHistoryEvent()
    }

    @Override
    protected IndexHistory find(IndexHistoryEvent object) {
        IndexHistory.findByInternalCodeAndDate(object.internalCode, object.date.clearTime())
    }

    public void importData() {
        def minDate = Date.parse("yyyy-MM-dd", "2009-01-01")
        def state = getLastState()
        if (!state) {
            state = [
                    time  : minDate.time
            ]
        }
        def date = new Date(state.time as Long)
        if(date < minDate)
            date = minDate

        if(date >= new Date().clearTime())
            return

        importData('indexB2',
                [
                        [date.format('yyyyMMdd').toInteger()]
                ])

        use(TimeCategory) {
            date = date + 1.days
        }
        def calendar = Calendar.getInstance() as GregorianCalendar
        calendar.setTime(date)
        def jc = new JalaliCalendar(calendar)

        logState([
                status: 'succeed',
                date  : String.format("%04d/%02d/%02d", jc.getYear(), jc.getMonth(), jc.getDay()),
                time  : date.time
        ])
    }
}
