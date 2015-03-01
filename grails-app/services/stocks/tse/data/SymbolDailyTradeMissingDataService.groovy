package stocks.tse.data

import fi.joensuu.joyds1.calendar.JalaliCalendar
import grails.converters.JSON
import groovy.time.TimeCategory
import org.apache.axis.types.UnsignedByte
import stocks.DataServiceState
import stocks.tse.SymbolDailyTrade
import stocks.tse.TSEDataService
import stocks.tse.event.SymbolDailyTradeEvent

class SymbolDailyTradeMissingDataService extends TSEDataService<SymbolDailyTrade, SymbolDailyTradeEvent> {

    static transactional = false
    static schedules = [
            [
                    method : 'importData',
                    trigger: [
                            type      : 'Simple',
                            parameters: [repeatInterval: 10000l, startDelay: 60000]
                    ]
            ]
    ]

    @Override
    protected Boolean getAutoLogStateEnabled() {
        false
    }

    @Override
    protected SymbolDailyTradeEvent getSampleEventObject() {
        new SymbolDailyTradeEvent()
    }

    @Override
    protected SymbolDailyTrade find(SymbolDailyTradeEvent object) {
        SymbolDailyTrade.findBySymbolInternalCodeAndDailySnapshot(object.symbolInternalCode, object.date)
    }

    public void importData() {
        def state = getLastState()
        if (!state) {
            state = [
                    time  : new Date().time
            ]
        }
        def date = new Date(state.time as Long)

        importData('tradeOneDay',
                [
                        [date.format('yyyyMMdd').toInteger(), new UnsignedByte(0)],
                        [date.format('yyyyMMdd').toInteger(), new UnsignedByte(1)],
                        [date.format('yyyyMMdd').toInteger(), new UnsignedByte(2)],
                        [date.format('yyyyMMdd').toInteger(), new UnsignedByte(3)],
                        [date.format('yyyyMMdd').toInteger(), new UnsignedByte(4)],
                        [date.format('yyyyMMdd').toInteger(), new UnsignedByte(5)]
                ])

        use(TimeCategory) {
            date = date - 1.days
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