package stocks

import org.codehaus.groovy.grails.web.context.ServletContextHolder
import org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes
import stocks.rate.event.CoinEvent
import stocks.rate.event.CurrencyEvent
import stocks.rate.event.CoinFutureEvent
import stocks.rate.event.MetalEvent
import stocks.util.ClassResolver

import java.beans.Introspector

class RateEventService {
    static transactional = false
    def bulkDataGateway

    def coinPersistService
    def currencyPersistService
    def coinFuturePersistService
    def metalPersistService

    private def persistEvent(event, String senderClassName) {
        event.creationDate = new Date()

//        def appContext = ServletContextHolder.servletContext
//                .getAttribute(GrailsApplicationAttributes.APPLICATION_CONTEXT)
//        def service = appContext."${Introspector.decapitalize(event.domainClass.persistentProperties.find { it.name == 'data' }.type.name.toString().replace('.', "_").split('_').last())}PersistService"

        def service = ClassResolver.loadServiceByName(senderClassName.replace('DataService', 'PersistService'))

        if (event.data) {
            if (service.update(event))
                bulkDataGateway.save(event)
        } else {
            event.data = service.create(event)
            bulkDataGateway.save(event)
        }
    }

    def send(Object event) {
        persistEvent(event)
        switch (event.class) {
            case CoinEvent.class:
                handle(event as CoinEvent)
                break
            case CurrencyEvent.class:
                handle(event as CurrencyEvent)
                break
            case CoinFutureEvent.class:
                handle(event as CoinFutureEvent)
                break
            case MetalEvent.class:
                handle(event as MetalEvent)
                break
        }
    }

    def handle(CoinEvent event) {
    }

    def handle(CurrencyEvent event) {
    }

    def handle(CoinFutureEvent event) {
    }

    def handle(MetalEvent event) {
    }
}
