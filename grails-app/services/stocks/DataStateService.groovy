package stocks

import grails.converters.JSON
import org.codehaus.groovy.grails.commons.GrailsClass

class DataStateService {

    def grailsApplication
    def dataService
    def bulkDataService

    Collection<GrailsClass> getServiceNames() {
        grailsApplication.serviceClasses.findAll { service ->
            dataService.dataServicePackages.any {
                service.fullName.contains(it)
            }
        }
    }

    def initializeStateLogging() {
        serviceNames.each { currentService ->
            def service = grailsApplication.mainContext[currentService.propertyName]

            service.metaClass.logState = { def data ->

                def serviceName = currentService.fullName

                DataServiceState.findAllByServiceNameAndIsLastState(serviceName, true).each {
                    it.isLastState = false
                    bulkDataService.save(it)
                }

                DataServiceState state = new DataServiceState()
                state.serviceName = serviceName
                state.data = data as JSON
                bulkDataService.save(state)
            }

            service.metaClass.getLastState = {
                def data = DataServiceState.findByServiceNameAndIsLastState(currentService.fullName, true)?.data
                data ? JSON.parse(data) : null
            }
        }
    }


}
