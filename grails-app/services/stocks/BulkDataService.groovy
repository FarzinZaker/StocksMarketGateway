package stocks

import groovy.transform.Synchronized
import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass
import org.hibernate.SessionFactory

import java.util.concurrent.ArrayBlockingQueue

class BulkDataService {

    SessionFactory sessionFactory

    private static ArrayBlockingQueue dataQueue

    private void init() {
        dataQueue = new ArrayBlockingQueue(20)
    }

    @Synchronized
    def save(object) {

        if(!dataQueue)
            init()

        DefaultGrailsDomainClass domainClass = object.domainClass
        dataQueue.put([class: domainClass, object: object])
        if (dataQueue.remainingCapacity() == 0)
            push()
    }

    def push() {
        if (!dataQueue || dataQueue.isEmpty())
            return

        def transaction = sessionFactory.getCurrentSession().beginTransaction()

//        domainClass.clazz.withNewTransaction {
            while (!dataQueue.isEmpty()) {
                dataQueue.take().object.save()
            }
//        }
        transaction.commit()
        sessionFactory.currentSession.clear()
    }

    @Synchronized
    def release(){
        push()
    }
}
