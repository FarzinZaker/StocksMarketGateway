package stocks.tse

import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass


/**
 * Created with IntelliJ IDEA.
 * User: Farzin
 * Date: 8/4/14
 * Time: 4:12 PM
 */
public abstract class TSEPersistService<T, K> {

    protected abstract def getSampleObject();

    T create(K event)
    {
        beforeCreate(event)
        def instance = new DefaultGrailsDomainClass(getSampleObject().class).newInstance()
        instance.properties = event.properties + [creationDate: new Date(), modificationDate: new Date()]
        instance.save()
        afterCreate(event, instance as T)
        instance as T
    }

    protected abstract void beforeCreate(K event)
    protected abstract void afterCreate(K event, T data)

    Boolean update(K event) {
        def announcement = event.data
        beforeUpdate(event, announcement)
        def result = announcement.domainClass.persistantProperties.findAll {
            !(it.name in ['creationDate', 'modificationDate'])
        }.any { property ->
            event.data."${property.name}" != event."${property.name}"
        }
        announcement.properties = event.properties.findAll {
            !(it.key.toString() in ['creationDate']) && !it.key.toString().endsWith('Id')
        }
        announcement.save()
        afterUpdate(event, announcement)
        result
    }

    protected abstract void beforeUpdate(K event, T data)
    protected abstract void afterUpdate(K event, T data)

}
