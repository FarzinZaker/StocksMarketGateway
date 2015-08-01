package stocks.graph

import com.orientechnologies.orient.core.metadata.schema.OClass
import com.orientechnologies.orient.core.metadata.schema.OProperty
import com.orientechnologies.orient.core.metadata.schema.OType
import com.orientechnologies.orient.core.sql.OCommandSQL
import com.tinkerpop.blueprints.Edge
import com.tinkerpop.blueprints.Vertex
import com.tinkerpop.blueprints.impls.orient.OrientGraph

class GraphDBService {

    def grailsApplication

    OrientGraph getGraph() {
        new OrientGraph("remote:${grailsApplication.config.graph.dataSource.host}/${grailsApplication.config.graph.dataSource.db}", grailsApplication.config.graph.dataSource.username?.toString(), grailsApplication.config.graph.dataSource.password?.toString())
    }

    OClass ensureVertexClass(String name, OClass superclass = null) {
        def clazz = graph.getVertexType(name)
        if (!clazz) {
            if (superclass)
                clazz = graph.createVertexType(name, superclass)
            else
                clazz = graph.createVertexType(name)
        }
        clazz
    }

    OClass ensureEdgeClass(String name) {
        def clazz = graph.getEdgeType(name)
        if (!clazz)
            clazz = graph.createEdgeType(name)
        clazz
    }

    OProperty ensureProperty(OClass clazz, String name, OType type) {
        if (!clazz.hasProperty(name))
            clazz.createProperty(name, type)
        else clazz.getProperty(name)
    }

    Vertex addVertex(String clazz = 'V', Map properties = [:]) {
        Vertex vertex = graph.addVertex("class:${clazz}");
        properties.keySet().each { property ->
            vertex.setProperty(property?.toString(), properties[property])
        }
        vertex
    }

    Edge addEdge(String clazz = 'E', Vertex from, Vertex to, Map properties = [:], String label = null) {
        Edge edge = graph.addEdge("class:${clazz}", from, to, label);
        properties.keySet().each { property ->
            edge.setProperty(property?.toString(), properties[property])
        }
        edge
    }

    def query(String query) {
        graph.command(new OCommandSQL(query))
    }
}
