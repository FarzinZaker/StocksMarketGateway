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

    protected abstract def getSampleObject();

    T create(K event) {
        beforeCreate(event)
        def domainClass = new DefaultGrailsDomainClass(getSampleObject().class)
//        domainClass.clazz.withSession {
            domainClass.clazz.withTransaction {
                def instance = domainClass.newInstance()
                instance.properties = event.properties + [creationDate: new Date(), modificationDate: new Date()]
                instance.save()
                afterCreate(event, instance as T)
                instance as T
            }
//        }
    }

    protected abstract void beforeCreate(K event)

    protected abstract void afterCreate(K event, T data)

    Boolean update(K event) {
        def result = null
        def object = event.data
        def domainClass = new DefaultGrailsDomainClass(object.class)
//        domainClass.clazz.withSession {
            domainClass.clazz.withTransaction {
                beforeUpdate(event, object)
                result = object.domainClass.persistantProperties.findAll {
                    !(it.name in ['creationDate', 'modificationDate'])
                }.any { property ->
                    event.data."${property.name}" != event."${property.name}"
                }
                object.properties = event.properties.findAll {
                    !(it.key.toString() in ['creationDate']) && !it.key.toString().endsWith('Id')
                }
                object.save()
                afterUpdate(event, object)
            }
//        }
        result
    }

    protected abstract void beforeUpdate(K event, T data)

    protected abstract void afterUpdate(K event, T data)

}
