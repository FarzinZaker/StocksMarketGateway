package stocks.graph

import com.orientechnologies.orient.core.metadata.schema.OClass
import com.orientechnologies.orient.core.metadata.schema.OProperty
import com.orientechnologies.orient.core.metadata.schema.OType
import com.orientechnologies.orient.core.sql.OCommandSQL
import com.tinkerpop.blueprints.Direction
import com.tinkerpop.blueprints.impls.orient.OrientEdge
import com.tinkerpop.blueprints.impls.orient.OrientGraph
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory
import com.tinkerpop.blueprints.impls.orient.OrientVertex
import grails.converters.JSON

class GraphDBService {

    def grailsApplication
    def graphConnectionPoolService

    static def factory

    def doWithGraph(Closure closure) {
//        if (!factory) {
//            factory = new OrientGraphFactory("remote:${grailsApplication.config.graph.dataSource.host}/${grailsApplication.config.graph.dataSource.db}", grailsApplication.config.graph.dataSource.username?.toString(), grailsApplication.config.graph.dataSource.password?.toString())
//            .setupPool(1, 20)
//        }
        def graph = graphConnectionPoolService.get()// factory.getTx()
        try {
            closure(graph)
        }
        catch (Exception ignored) {
            ignored.printStackTrace()
        }
        finally {
//            println 'created:' + factory.createdInstancesInPool
//            println 'available:' + factory.availableInstancesInPool
//            graph.shutdown()
//            factory.close()
            graphConnectionPoolService.release(graph)
        }
    }

    OClass ensureVertexClass(String name, OClass superclass = null, Boolean isAbstract = false) {
        OClass clazz = null
        doWithGraph { OrientGraph graph ->
            clazz = graph.getVertexType(name)
            if (!clazz) {
                if (superclass)
                    clazz = graph.createVertexType(name, superclass)
                else
                    clazz = graph.createVertexType(name)
            }
            graph.commit()
            clazz.setAbstract(isAbstract)
        }
        clazz
    }

    OClass ensureEdgeClass(String name, OClass superclass = null, Boolean isAbstract = false) {
        OClass clazz = null
        doWithGraph { OrientGraph graph ->
            clazz = graph.getEdgeType(name)
            if (!clazz) {
                if (superclass)
                    clazz = graph.createEdgeType(name, superclass)
                else
                    clazz = graph.createEdgeType(name)
            }
            graph.commit()
            clazz.setAbstract(isAbstract)
        }
        clazz
    }

    OProperty ensureProperty(OClass clazz, String name, OType type, Boolean notNull = false, String collectionType = null) {
        OProperty property = null
        doWithGraph { OrientGraph graph ->
            property = clazz.getProperty(name)
            if (!property)
                if (collectionType)
                    property = clazz.createProperty(name, type, graph.getVertexType(collectionType))
                else
                    property = clazz.createProperty(name, type)
            graph.commit()
            property.setNotNull(notNull)
        }
        property
    }

    OrientVertex findVertex(String queryString) {
        OrientVertex vertex = null
        doWithGraph { OrientGraph graph ->
            vertex = queryVertex(queryString)?.find() as OrientVertex
        }
        vertex
    }

    OrientVertex getVertex(String id) {
        OrientVertex result = null
        doWithGraph { OrientGraph graph ->
            result = graph.getVertex(id)
        }
        result
    }

    OrientEdge getEdge(String id) {
        OrientEdge result = null
        doWithGraph { OrientGraph graph ->
            result = graph.getEdge(id)
        }
        result
    }

    Map getAndUnwrapVertex(String id) {
        OrientVertex result = null
        doWithGraph { OrientGraph graph ->
            result = graph.getVertex(id)
        }
        unwrapVertex(result)
    }

    OrientVertex addVertex(String clazz = 'V', Map properties = [:]) {
        OrientVertex vertex = null
        doWithGraph { OrientGraph graph ->
            vertex = graph.addVertex("class:${clazz}".toString());
            properties.keySet().each { property ->
                vertex.setProperty(property?.toString(), properties[property])
            }
            graph.commit()
        }
        vertex
    }

    OrientVertex editVertex(String id, Map properties = [:]) {
        OrientVertex vertex = getVertex(id)
        doWithGraph { OrientGraph graph ->
            properties.keySet().each { property ->
                vertex.setProperty(property?.toString(), properties[property])
            }
            graph.commit()
        }
        vertex
    }

    def deleteVertex(String id) {
        OrientVertex vertex = getVertex(id)
        doWithGraph { OrientGraph graph ->
            vertex.remove()
            graph.commit()
        }
    }

    OrientEdge addEdge(String clazz = 'E', OrientVertex from, OrientVertex to, Map properties = [:], String label = null) {
        OrientEdge edge = null
        doWithGraph { OrientGraph graph ->
            edge = graph.addEdge("class:${clazz}".toString(), from, to, label ?: clazz);
            properties.keySet().each { property ->
                edge.setProperty(property?.toString(), properties[property])
            }
            graph.commit()
        }
        edge
    }

    def deleteEdge(OrientEdge edge) {
        doWithGraph { OrientGraph graph ->
            edge.remove()
            graph.commit()
        }
    }

    OrientEdge findEdgeByLabel(OrientVertex from, OrientVertex to, String label = null) {
        from.getEdges(to, Direction.OUT, label)?.find() as OrientEdge
    }

    List<OrientVertex> queryVertex(String queryString) {
        List result = null
        doWithGraph { OrientGraph graph ->
            result = graph.command(new OCommandSQL(queryString)).execute().iterator()?.toList()
        }
        result
    }

    List<OrientEdge> queryEdge(String queryString) {
        List result = null
        doWithGraph { OrientGraph graph ->
            result = graph.command(new OCommandSQL(queryString)).execute().iterator()?.toList()
        }
        result
    }

    void executeCommand(String command) {
        doWithGraph { OrientGraph graph ->
            graph.command(new OCommandSQL(command)).execute().iterator()?.toList()
        }
    }

    Long count(String queryString) {
        Long result = null
        doWithGraph { OrientGraph graph ->
            result = graph.command(new OCommandSQL(queryString)).execute().iterator()?.toList()?.find()?.getProperty('COUNT') as Long
        }
        result
    }

    List<Map> queryAndUnwrapVertex(String queryString) {
        List result = null
        doWithGraph { OrientGraph graph ->
            result = graph.command(new OCommandSQL(queryString)).execute().iterator()?.toList()?.collect { OrientVertex vertex -> unwrapVertex(vertex) }
        }
        result
    }

    List<Map> queryAndUnwrapEdge(String queryString) {
        List result = null
        doWithGraph { OrientGraph graph ->
            result = graph.command(new OCommandSQL(queryString)).execute().iterator()?.toList()?.collect { OrientEdge edge -> unwrapEdge(edge) }
        }
        result
    }

    Map unwrapVertex(OrientVertex vertex) {
        if(!vertex)
            return null
        def result = [:]
        result.id = vertex.id?.toString()
        result.idNumber = vertex.id?.toString()?.replace('#', '')
        result.label = vertex.label?.toString()
        vertex.propertyKeys.each {
            if (it.endsWith('List'))
                result.put(it, JSON.parse(vertex.getProperty(it)?.toString()).collect { it.replace('"', '') })
            else
                result.put(it, vertex.getProperty(it))
        }
        result
    }

    Map unwrapEdge(OrientEdge edge) {
        if(!edge)
            return null
        def result = [:]
        result.id = edge.id?.toString()
        result.idNumber = edge.id?.toString()?.replace('#', '')
        result.label = edge.label?.toString()
        edge.propertyKeys.each {
            result.put(it, edge.getProperty(it))
        }
        result
    }
}
