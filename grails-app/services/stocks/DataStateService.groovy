package stocks

import grails.converters.JSON
import groovy.transform.Synchronized
import org.codehaus.groovy.grails.commons.GrailsClass

class DataStateService {
    static transactional = false
    def grailsApplication
    def dataService

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
                synchronized (this) {
                    def serviceName = currentService.fullName
                    Thread.startDaemon {
                        DataServiceState.withTransaction {
                            DataServiceState.findAllByServiceNameAndIsLastState(serviceName, true).each {
                                it.isLastState = false
                                it.save()
                            }

                            DataServiceState state = new DataServiceState()
                            state.serviceName = serviceName
                            state.data = data as JSON
                            state.save(flush: true)
                        }
                    }.join()
                }
            }

            service.metaClass.getLastState = {
                def data = DataServiceState.findByServiceNameAndIsLastState(currentService.fullName, true)?.data
                data ? JSON.parse(data) : null
            }
        }
    }


}
