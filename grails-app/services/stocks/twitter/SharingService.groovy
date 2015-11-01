package stocks.twitter

import com.tinkerpop.blueprints.impls.orient.OrientVertex
import stocks.User
import stocks.twitter.Search.TwitterMaterial

class SharingService {

    def graphDBService
    def materialGraphService
    def propertyGraphService
    def messageSource

    void shareMaterial(User owner, String type, Long identifier, String title, String description, Long imageId, List<Map> properties, List<String> groups) {
        graphDBService.executeCommand("DELETE EDGE About WHERE out.identifier = ${identifier}")
        graphDBService.executeCommand("DELETE EDGE Share WHERE out.identifier = ${identifier}")

        def materialVertex = materialGraphService.ensureMaterial(owner, type, identifier, title, description, imageId)

        def searchData = TwitterMaterial.findByIdentifier(identifier)

        def propertyTitleList = []
        properties.each { property ->
            def propertyVertex = propertyGraphService.ensureProperty(property.type as String, property.identifier as Long, property.title as String)
            graphDBService.addEdge('About', materialVertex, propertyVertex)
            propertyTitleList << "${messageSource.getMessage("twitter.search.type.${property.type}", null, '', Locale.ENGLISH)} - ${property.title}"
        }
        searchData.propertyTitleList = propertyTitleList

        def groupRidList = []
        groups.each { groupId ->
            def groupVertex = graphDBService.getVertex(groupId)
            graphDBService.addEdge('Share', materialVertex, groupVertex)
            groupRidList << "#${groupId}"
        }
        searchData.groupRidList = groupRidList

        searchData.save()
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

    List<Map> suggestFollowList(Long userId, Integer skip = 0, Integer limit = 10){
        graphDBService.queryAndUnwrapVertex("SELECT FROM (SELECT EXPAND(\$Total) LET \$Trav = (SELECT @rid as @rid, @class as @class, identifier, title, IN('Follow').size() as followersCount, \$DEPTH as depth FROM (TRAVERSE OUT('Follow') FROM Person WHERE identifier = ${userId}) WHERE \$DEPTH > 1 AND @rid NOT IN (SELECT OUT('Follow') FROM Person WHERE identifier = ${userId})), \$Common = (SELECT @rid as @rid, @class as @class, identifier, title, IN('Follow').size() as followersCount, 9999 as depth FROM Followable WHERE identifier <> 0 AND identifier <> ${userId} AND @rid NOT IN (SELECT OUT('Follow') FROM Person WHERE identifier = ${userId})), \$Total = SET(UNIONALL(\$Trav, \$Common))) ORDER BY depth ASC, followersCount DESC SKIP ${skip} LIMIT ${limit}")
    }

}
