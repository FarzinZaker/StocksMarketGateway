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
        if (dataQueue.remainingCapacity() == 0) {
            push()

        }
    }

//    @Transactional

    def push() {
        if (!dataQueue || dataQueue.isEmpty())
            return
        def domainClass = new DefaultGrailsDomainClass(dataQueue.peek()?.object?.class)

        while (!dataQueue.isEmpty()) {
            def item = dataQueue.take()

            boolean res = false

            Thread.start {
                try {
                    domainClass.clazz.withTransaction {
                        if (item.object.save(flush: true))
                            res = true
                    }
                } catch (x) {
                    x.printStackTrace()
                }
            }.join()
            if (!res)
                dataQueue.put(item)

        }
    }

//    @Synchronized
    def release() {
        push()
    }
}
