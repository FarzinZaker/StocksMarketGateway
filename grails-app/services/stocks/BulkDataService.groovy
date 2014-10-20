package stocks

import groovy.transform.Synchronized
import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass
import org.hibernate.Session
//import org.hibernate.SessionFactory
import org.springframework.transaction.annotation.Transactional
import stocks.commodity.TradeStatistics

import java.util.concurrent.ArrayBlockingQueue

class BulkDataService {
    static transactional = false
//    SessionFactory sessionFactory

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
                if(!item.object.save(flush:true))
                    dataQueue.put(item)
//                else
//                    transaction.commit()
//                println sessionFactory.currentSession.flushMode
//                sessionFactory.currentSession.flush()
            }
            catch (ignored){
//                transaction.rollback()
//                println(ignored.stackTrace)
//                ignored.printStackTrace()
                println "${new Date()} ${ignored.message} ${item}"
                dataQueue.put(item)
            }
//            sessionFactory.currentSession.clear()
//            sessionFactory.currentSession.clear()
        }
    }

    @Synchronized
    def release() {
        push()
    }
}
