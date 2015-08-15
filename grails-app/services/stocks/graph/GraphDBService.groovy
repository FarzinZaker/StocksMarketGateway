package stocks.graph

import com.jniwrapper.Bool
import com.orientechnologies.orient.core.metadata.schema.OClass
import com.orientechnologies.orient.core.metadata.schema.OProperty
import com.orientechnologies.orient.core.metadata.schema.OType
import com.orientechnologies.orient.core.sql.OCommandSQL
import com.tinkerpop.blueprints.Edge
import com.tinkerpop.blueprints.Vertex
import com.tinkerpop.blueprints.impls.orient.OrientGraph
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory

class GraphDBService {

    def grailsApplication
//
//    private OrientGraphFactory factory
//
//    OrientGraph getGraphDB() {
//        if (!factory)
//            factory = new OrientGraphFactory("remote:${grailsApplication.config.graph.dataSource.host}/${grailsApplication.config.graph.dataSource.db}", grailsApplication.config.graph.dataSource.username?.toString(), grailsApplication.config.graph.dataSource.password?.toString())
//        factory.getTx();
//    }

    def doWithGraph(Closure closure) {
        def factory = new OrientGraphFactory("remote:${grailsApplication.config.graph.dataSource.host}/${grailsApplication.config.graph.dataSource.db}", grailsApplication.config.graph.dataSource.username?.toString(), grailsApplication.config.graph.dataSource.password?.toString())
        def graph = factory.getTx()
        try {
            closure(graph)
        }
        catch (Exception ignored) {
            ignored.printStackTrace()
        }
        finally {
            factory.close()
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

    Vertex getVertex(String queryString) {
        Vertex vertex = null
        doWithGraph { OrientGraph graph ->
            def iterator = query(queryString)
            if (iterator.hasNext())
                vertex = iterator.next() as Vertex
        }
        vertex
    }

    Vertex addVertex(String clazz = 'V', Map properties = [:]) {
        Vertex vertex = null
        doWithGraph { OrientGraph graph ->
            vertex = graph.addVertex("class:${clazz}".toString());
            properties.keySet().each { property ->
                vertex.setProperty(property?.toString(), properties[property])
            }
            graph.commit()
        }
        vertex
    }

    Edge addEdge(String clazz = 'E', Vertex from, Vertex to, Map properties = [:], String label = null) {
        Edge edge = null
        doWithGraph { OrientGraph graph ->
            edge = graph.addEdge("class:${clazz}".toString(), from, to, label);
            properties.keySet().each { property ->
                edge.setProperty(property?.toString(), properties[property])
            }
            graph.commit()
        }
        edge
    }

    Iterator query(String queryString) {
        Iterator result = null
        doWithGraph { OrientGraph graph ->
            result = graph.command(new OCommandSQL(queryString)).execute().iterator()
        }
        result
    }
}
