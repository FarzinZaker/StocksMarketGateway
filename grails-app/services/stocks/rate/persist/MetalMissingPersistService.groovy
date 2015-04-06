package stocks.rate.persist

import fi.joensuu.joyds1.calendar.JalaliCalendar
import stocks.rate.Metal
import stocks.rate.event.MetalEvent
import stocks.tse.SymbolDailyTrade

class MetalMissingPersistService {
    static transactional = false
    def bulkDataGateway
    def grailsApplication
    def queryService

    Boolean update(MetalEvent event) {
        afterUpdate(event, event.data)
        true
    }

    Metal create(MetalEvent event) {
        beforeCreate(event)
        def data = new Metal(event.properties + [creationDate: new Date(), modificationDate: new Date()])
        bulkDataGateway.save(data)
        afterCreate(event, data)
        data
    }

    protected void beforeCreate(MetalEvent event) {

    }

    protected void afterCreate(MetalEvent event, Metal data) {

        queryService.applyEventBasedQueries(data)
    }

    protected void beforeUpdate(MetalEvent event, Metal data) {

    }

    protected void afterUpdate(MetalEvent event, Metal data) {
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
