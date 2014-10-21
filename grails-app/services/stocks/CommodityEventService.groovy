package stocks

import org.codehaus.groovy.grails.web.context.ServletContextHolder
import org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes
import stocks.commodity.event.TradeStatisticsEvent

import java.beans.Introspector

class CommodityEventService {
    static transactional = false
    def bulkDataGateway

    private def persistEvent(event) {
        event.creationDate = new Date()

        def appContext = ServletContextHolder.servletContext
                .getAttribute(GrailsApplicationAttributes.APPLICATION_CONTEXT)
        def service = appContext."${Introspector.decapitalize(event.domainClass.persistentProperties.find { it.name == 'data' }.type.name.toString().replace('.', "_").split('_').last())}PersistService"

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
            case TradeStatisticsEvent.class:
                break
        }
    }
}
