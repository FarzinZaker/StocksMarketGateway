package stocks.rate.persist

import fi.joensuu.joyds1.calendar.JalaliCalendar
import stocks.rate.Currency
import stocks.rate.event.CurrencyEvent
import stocks.tse.SymbolDailyTrade

class CurrencyMissingPersistService {
    static transactional = false
    def bulkDataGateway
    def grailsApplication
    def queryService

    Boolean update(CurrencyEvent event) {
        afterUpdate(event, event.data)
        true
    }

    Currency create(CurrencyEvent event) {
        beforeCreate(event)
        def data = new Currency(event.properties + [creationDate: new Date(), modificationDate: new Date()])
        bulkDataGateway.save(data)
        afterCreate(event, data)
        data
    }

    protected void beforeCreate(CurrencyEvent event) {

    }

    protected void afterCreate(CurrencyEvent event, Currency data) {

        queryService.applyEventBasedQueries(data)
    }

    protected void beforeUpdate(CurrencyEvent event, Currency data) {

    }

    protected void afterUpdate(CurrencyEvent event, Currency data) {
        def date = event.time
        date = date.clearTime()
        event.dailySnapshot = date
        def calendar = Calendar.getInstance() as GregorianCalendar
        calendar.setTime(date)
        def jc = new JalaliCalendar(calendar)
        if (jc.getDayOfWeek() == 5)
            event.weeklySnapshot = date
        if (jc.getDay() == jc.getLastDayOfMonth(jc.getYear(), jc.getMonth()))
            event.monthlySnapshot = date
//        SymbolDailyTrade.withTransaction {
//            event.save(flush: true)
//        }
    }
}
