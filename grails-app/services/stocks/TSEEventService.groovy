package stocks

import org.codehaus.groovy.grails.web.context.ServletContextHolder
import org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes
import org.codehaus.groovy.reflection.ReflectionUtils
import stocks.tse.event.BoardEvent
import stocks.tse.event.SymbolClientTypeEvent
import stocks.util.ClassResolver

import java.beans.Introspector

class TSEEventService {
    static transactional = false
    def bulkDataGateway

    private def persistEvent(event, String senderClassName) {
        event.creationDate = new Date()

//        def appContext = ServletContextHolder.servletContext
//                .getAttribute(GrailsApplicationAttributes.APPLICATION_CONTEXT)
//        def service = appContext."${Introspector.decapitalize(event.domainClass.persistentProperties.find { it.name == 'data' }.type.name.toString().replace('.', "_").split('_').last())}PersistService"
//        def className
//        for(def i = 1; i < 100; i++) {
//            className = ReflectionUtils.getCallingClass(i).name
//            if(className.endsWith('DataService'))
//                break
//        }
        def service = ClassResolver.loadServiceByName(senderClassName.replace('DataService', 'PersistService'))

        if (event.data) {
            if (service.update(event))
                bulkDataGateway.save(event)
        } else {
            event.data = service.create(event)
            bulkDataGateway.save(event)
        }
        event
    }

    def open(sampleEventObject, String serviceName, List<List> parameters){

    }

    def close(sampleEventObject, String serviceName, List<List> parameters, List eventList){

    }

    def send(Object event, String senderClassName) {
        switch (event.class) {
            case SymbolClientTypeEvent.class:
                def item = event as SymbolClientTypeEvent
                if(item.individualBuyVolume + item.legalBuyVolume != item.individualSellVolume + item.legalSellVolume)
                    return
        }
        persistEvent(event, senderClassName)
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
