package stocks

import org.codehaus.groovy.grails.web.context.ServletContextHolder
import org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes
import stocks.codal.event.AnnouncementEvent

class CodalEventService {

    def announcementPersistService

    private def persistEvent(event) {
        event.creationDate = new Date()

        def appContext = ServletContextHolder.servletContext
                .getAttribute(GrailsApplicationAttributes.APPLICATION_CONTEXT)
        def service = appContext."${event.domainClass.persistentProperties.find { it.name == 'data' }.type.name.toString().replace('.', "_").split('_').last().toLowerCase()}PersistService"

        if (event.data) {
            if (service.update(event))
                event.save()
        } else {
            event.data = service.create(event)
            event.save()
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
