package stocks.tse

import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass


/**
 * Created with IntelliJ IDEA.
 * User: Farzin
 * Date: 8/4/14
 * Time: 4:12 PM
 */
public abstract class TSEPersistService<T, K> {
    static transactional = false

    def bulkDataGateway

    protected abstract def getSampleObject();

    T create(K event) {
        beforeCreate(event)
        def domainClass = new DefaultGrailsDomainClass(getSampleObject().class)
        def instance = domainClass.newInstance()
        instance.properties = event.properties + [creationDate: new Date(), modificationDate: new Date()]
        bulkDataGateway.save(instance)
        afterCreate(event, instance as T)
        instance as T
    }

    protected abstract void beforeCreate(K event)

    protected abstract void afterCreate(K event, T data)

    protected List<String> getPropertyExcludeList() {
        []
    }

    Boolean update(K event) {
        def result
        def object = event.data
        def domainClass = new DefaultGrailsDomainClass(object.class)
        if (beforeUpdate(event, object) != false) {
            result = domainClass.persistantProperties.findAll {
                !(it.name in (['creationDate', 'modificationDate'] + propertyExcludeList)) &&
                        (it.type in [Integer, Long, Double, Boolean, Date, String])
            }.any { property ->
                event.data."${property.name}" != event."${property.name}"
            }
            object.properties = event.properties.findAll {
                !(it.key.toString() in ['creationDate'] + propertyExcludeList) && !it.key.toString().endsWith('Id')
            }
//            if (result)
            object.modificationDate = new Date()
            bulkDataGateway.save(object)
            if (result)
                afterUpdate(event, object)
        } else
            result = false
        result
    }

    protected abstract void beforeUpdate(K event, T data)

    protected abstract void afterUpdate(K event, T data)

}
