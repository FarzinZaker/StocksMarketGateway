package stocks.tse.persist

import fi.joensuu.joyds1.calendar.JalaliCalendar
import stocks.tse.SymbolDailyTrade
import stocks.tse.TSEPersistService
import stocks.tse.event.SymbolDailyTradeEvent

class SymbolDailyTradeMissingPersistService extends TSEPersistService<SymbolDailyTrade, SymbolDailyTradeEvent> {

    @Override
    protected getSampleObject() {
        return new SymbolDailyTrade()
    }

    @Override
    protected void beforeCreate(SymbolDailyTradeEvent event) {
    }

    @Override
    protected void afterCreate(SymbolDailyTradeEvent event, SymbolDailyTrade data) {
        data.dailySnapshot = data.date
        def calendar = Calendar.getInstance() as GregorianCalendar
        calendar.setTime(data.date)
        def jc = new JalaliCalendar(calendar)
        if (jc.getDayOfWeek() == 5)
            data.weeklySnapshot = data.date
        if (jc.getDay() == jc.getLastDayOfMonth(jc.getYear(), jc.getMonth()))
            data.monthlySnapshot = data.date
        SymbolDailyTrade.withTransaction {
            data.save(flush: true)
        }
    }

    @Override
    protected void beforeUpdate(SymbolDailyTradeEvent event, SymbolDailyTrade data) {
        false
    }

    @Override
    protected void afterUpdate(SymbolDailyTradeEvent event, SymbolDailyTrade data) {

    }
}
