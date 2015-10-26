package stocks.graph

import com.tinkerpop.blueprints.impls.orient.OrientGraph
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory
import org.apache.commons.collections.buffer.BlockingBuffer

import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicReferenceArray

class GraphConnectionPoolService {

    def grailsApplication

    static OrientGraphFactory factory

    private static ArrayBlockingQueue<OrientGraph> freeQueue = new ArrayBlockingQueue<OrientGraph>(5)
    private static CopyOnWriteArrayList<OrientGraph> inUseQueue = new CopyOnWriteArrayList<OrientGraph>()

    private static AtomicInteger servedRequests = new AtomicInteger(0)

    public OrientGraph get() {
        if (freeQueue.isEmpty() && inUseQueue.size() < 5) {
            freeQueue.put(createNew())
        }
        def item = freeQueue.take()
        if (item.isClosed())
            return get()

        inUseQueue.add(item)

        showStatus()

        servedRequests.set(servedRequests.incrementAndGet())
        item
    }

    public void release(OrientGraph graph) {
        inUseQueue.remove(graph)
        freeQueue.put(graph)

        showStatus()
    }

    private OrientGraph createNew() {
        if (!factory) {
            factory = new OrientGraphFactory("remote:${grailsApplication.config.graph.dataSource.host}/${grailsApplication.config.graph.dataSource.db}", grailsApplication.config.graph.dataSource.username?.toString(), grailsApplication.config.graph.dataSource.password?.toString())
        }
        factory.getTx()
    }

    private static void showStatus(){
        println()
        println "total:\t${freeQueue.size() + inUseQueue.size()}"
        println "free:\t${freeQueue.size()}"
        println "inUse:\t${inUseQueue.size()}"
        println "served:\t${servedRequests.get()}"
        println()
    }
}
