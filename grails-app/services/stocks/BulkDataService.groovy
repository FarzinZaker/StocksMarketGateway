package stocks

import groovy.transform.Synchronized
import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass
import org.hibernate.Session

//import org.hibernate.SessionFactory
import org.springframework.transaction.annotation.Transactional
import stocks.commodity.TradeStatistics
import stocks.tse.Board

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
        Thread.start {
            if (!dataQueue)
                init()

            DefaultGrailsDomainClass domainClass = object.domainClass
            dataQueue.put([class: domainClass, object: object])
            if (dataQueue.remainingCapacity() == 0) {
                domainClass.clazz.withTransaction {
                    push()
                }

            }
        }.join()
    }

//    @Transactional

    def push() {
        if (!dataQueue || dataQueue.isEmpty())
            return

        while (!dataQueue.isEmpty()) {
            def item = dataQueue.take()

            boolean res = false
            try {
                if(item.object.id)
                    item.object=item.object.attach()
                println 'before save '+item.object
                if (item.object.save(flush: true))
                    res = true
                println 'after save '+item.object
            } catch (x) {
                x.printStackTrace()
            }
            if (!res)
                dataQueue.put(item)
        }
    }

//    @Synchronized
    def release() {
        push()
    }
}
