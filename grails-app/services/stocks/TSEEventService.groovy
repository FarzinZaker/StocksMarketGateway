package stocks

import org.codehaus.groovy.grails.web.context.ServletContextHolder
import org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes
import stocks.tse.event.BoardEvent

import java.beans.Introspector

class TSEEventService {

    def bulkDataService

    private def persistEvent(event) {
        event.creationDate = new Date()

        def appContext = ServletContextHolder.servletContext
                .getAttribute(GrailsApplicationAttributes.APPLICATION_CONTEXT)
        def service = appContext."${Introspector.decapitalize(event.domainClass.persistentProperties.find { it.name == 'data' }.type.name.toString().replace('.', "_").split('_').last())}PersistService"

        if (event.data) {
            if (service.update(event))
                bulkDataService.save(event)
        } else {
            event.data = service.create(event)
            bulkDataService.save(event)
        }
        event
    }

    def open(sampleEventObject, String serviceName, List<List> parameters){

    }

    def close(sampleEventObject, String serviceName, List<List> parameters, List eventList){

    }

    def send(Object event) {
        persistEvent(event)
        switch (event.class) {
            case BoardEvent.class:
                handle(event as BoardEvent)
                break
        }
        event
    }

    def handle(BoardEvent event) {
    }
}
