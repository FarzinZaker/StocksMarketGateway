package stocks.twitter

import com.tinkerpop.blueprints.impls.orient.OrientVertex
import stocks.User

class SharingService {

    def graphDBService
    def materialGraphService
    def propertyGraphService

    void shareMaterial(User owner, String type, Long identifier, String title, String description, Long imageId, List<Map> properties, List<String> groups) {
        graphDBService.executeCommand("DELETE EDGE About WHERE out.identifier = ${identifier}")
        graphDBService.executeCommand("DELETE EDGE Share WHERE out.identifier = ${identifier}")

        def materialVertex = materialGraphService.ensureMaterial(owner, type, identifier, title, description, imageId)

        properties.each { property ->
            def propertyVertex = propertyGraphService.ensureProperty(property.type as String, property.identifier as Long, property.title as String)
            graphDBService.addEdge('About', materialVertex, propertyVertex)
        }

        groups.each { groupId ->
            def groupVertex = graphDBService.getVertex(groupId)
            graphDBService.addEdge('Share', materialVertex, groupVertex)
        }
    }

    void removeMaterial(Long identifier){
        materialGraphService.removeMaterial(identifier)
    }

    List<Map> materialShareGroups(Long identifier){
        graphDBService.queryAndUnwrapVertex("SELECT FROM Group WHERE @rid in (SELECT DISTINCT(in.@rid) FROM Share WHERE out.identifier = ${identifier})")
    }

    List<Map> materialAboutProperties(Long identifier){
        graphDBService.queryAndUnwrapVertex("SELECT FROM Property WHERE @rid in (SELECT DISTINCT(in.@rid) FROM About WHERE out.identifier = ${identifier})")
    }

}
