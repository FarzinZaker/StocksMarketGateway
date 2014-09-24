package stocks

import groovy.transform.Synchronized
import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass
import org.hibernate.SessionFactory

import java.util.concurrent.ArrayBlockingQueue

class BulkDataService {

    SessionFactory sessionFactory

    private static ArrayBlockingQueue dataQueue

    private void init() {
        dataQueue = new ArrayBlockingQueue(1)
    }

    @Synchronized
    def save(object) {

        if (!dataQueue)
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

        while (!dataQueue.isEmpty()) {
            def item = dataQueue.take()
            try {
                if(!item.object.save())
                    dataQueue.put(item)
            }
            catch (ignored){
                println(ignored.stackTrace)
                dataQueue.put(item)
            }
        }

        transaction.commit()
        sessionFactory.currentSession.clear()
    }

    @Synchronized
    def release() {
        push()
    }
}
