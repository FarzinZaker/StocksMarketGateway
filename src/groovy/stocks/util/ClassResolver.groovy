package stocks.util

import grails.util.Holder
import grails.util.Holders

import java.beans.Introspector

/**
 * Created by farzin on 20/01/2015.
 */
public class ClassResolver {

    private static loadedServices = [:]

    public static loadServiceByName(String serviceName) {
        if (!loadedServices.containsKey(serviceName))
            loadedServices[serviceName] = Holders.applicationContext.getBean(Introspector.decapitalize(serviceName.split('\\.')?.last()))
        loadedServices[serviceName]
    }

    private static checkedServices = [:]

    public static Boolean serviceExists(String serviceName) {
        if (!checkedServices.containsKey(serviceName))
            checkedServices[serviceName] = Holders.grailsApplication.getArtefacts('Service').any {
                it.fullName == serviceName
            }
        checkedServices[serviceName]
    }

    private static loadedDomainClasses = [:]

    public static def loadDomainClassByName(String domainName) {
        if (!loadedDomainClasses.containsKey(domainName))
            loadedDomainClasses[domainName] = Holders.grailsApplication.getDomainClass(domainName).clazz
        loadedDomainClasses[domainName]
    }

    private static packageDomainClasses = [:]

    public static def loadDomainClassListByPackage(String pkg) {
        if (!packageDomainClasses.containsKey(pkg))
            packageDomainClasses[pkg] = Holders.grailsApplication.getArtefacts('Domain').findAll {
                it.fullName.startsWith(pkg)
            }
        packageDomainClasses[pkg]
    }
}
