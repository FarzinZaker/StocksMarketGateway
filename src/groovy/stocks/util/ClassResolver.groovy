package stocks.util

import grails.util.Holder
import grails.util.Holders

import java.beans.Introspector

/**
 * Created by farzin on 20/01/2015.
 */
public class ClassResolver {

    public static loadServiceByName(String serviceName) {
        Holders.applicationContext.getBean(Introspector.decapitalize(serviceName.split('\\.')?.last()))
    }

    public static Boolean serviceExists(String serviceName){
        Holders.grailsApplication.getArtefacts('Service').any {
            it.fullName == serviceName
        }
    }

    public static def loadDomainClassByName(String domainName){
        Holders.grailsApplication.getDomainClass(domainName).clazz
    }
}
