package stocks.rate.persist

import fi.joensuu.joyds1.calendar.JalaliCalendar
import stocks.rate.Coin
import stocks.rate.event.CoinEvent
import stocks.tse.SymbolDailyTrade

class CoinMissingPersistService {
    static transactional = false
    def bulkDataGateway
    def grailsApplication
    def queryService

    Boolean update(CoinEvent event) {
        afterUpdate(event, event.data)
        true
    }

    Coin create(CoinEvent event) {
        beforeCreate(event)
        def data = new Coin(event.properties + [creationDate: new Date(), modificationDate: new Date()])
        bulkDataGateway.save(data)
        afterCreate(event, data)
        data
    }

    protected void beforeCreate(CoinEvent event) {

    }

    protected void afterCreate(CoinEvent event, Coin data) {

        queryService.applyEventBasedQueries(data)
    }

    protected void beforeUpdate(CoinEvent event, Coin data) {

    }

    protected void afterUpdate(CoinEvent event, Coin data) {
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
