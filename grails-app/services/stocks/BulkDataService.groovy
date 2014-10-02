package stocks

import groovy.transform.Synchronized
import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass
import org.hibernate.SessionFactory
import org.springframework.transaction.annotation.Transactional

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

    @Transactional
    def push() {
        if (!dataQueue || dataQueue.isEmpty())
            return


        while (!dataQueue.isEmpty()) {

//            def transaction = sessionFactory.getCurrentSession().beginTransaction()
            def item = dataQueue.take()
            try {
                if(!item.object.save()) {
                    dataQueue.put(item)

                    println("save failed - retrying: ${item.object}")
                }
                else
                    println("new record saved: ${item.object}")
//                else
//                    transaction.commit()
            }
            catch (ignored){
//                transaction.rollback()
                println(ignored.stackTrace)
                dataQueue.put(item)
            }

//            sessionFactory.currentSession.clear()
        }
    }

    @Synchronized
    def release() {
        push()
    }
}
