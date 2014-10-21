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
        dataQueue = new ArrayBlockingQueue(10)
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


        Thread.start {
            def domainClass = new DefaultGrailsDomainClass(dataQueue.peek()?.object?.class)
            domainClass.clazz.withTransaction {
                while (!dataQueue.isEmpty()) {
                    def item = dataQueue.take()
                    try {

                        if (!item.object.save(flush: true))
                            dataQueue.put(item)
                    }
                    catch (ignored) {
                        dataQueue.put(item)
                    }
                }
            }
        }.join()
    }

    @Synchronized
    def release() {
        push()
    }
}
