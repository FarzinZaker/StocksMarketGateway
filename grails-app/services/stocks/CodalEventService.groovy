package stocks

import org.codehaus.groovy.grails.web.context.ServletContextHolder
import org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes
import stocks.alerting.QueryInstance
import stocks.codal.event.AnnouncementEvent

import java.beans.Introspector

class CodalEventService {
    static transactional = false
    def bulkDataGateway

    def announcementPersistService

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
            case AnnouncementEvent.class:
                handle(event as AnnouncementEvent)
                break
        }
    }

    def handle(AnnouncementEvent event) {
        announcementPersistService.grabFiles(event)
    }
}
