package stocks.graph

import com.tinkerpop.blueprints.impls.orient.OrientVertex
import stocks.User

class MaterialGraphService {

    def graphDBService
    def personGraphService

    public static String TYPE_ARTICLE = 'Article'
    public static String TYPE_SCREENER = 'Screener'
    public static String TYPE_BACKTEST = 'BackTest'
    public static String TYPE_PORTFOLIO = 'Portfolio'
    public static String TYPE_ANALYSIS = 'Analysis'

    OrientVertex ensureMaterial(User owner, String type, Long identifier, String title, String description, Long imageId) {

        graphDBService.executeCommand("DELETE EDGE Own WHERE in.identifier = ${identifier}")

        graphDBService.executeCommand("UPDATE Material SET title = '${title}', description = '${description?.replaceAll("<(.|\n)*?>", '') ?: '-'}', imageId = ${imageId ?: 0} WHERE identifier = ${identifier}")

        def material = graphDBService.findVertex("SELECT FROM Material WHERE identifier = ${identifier}")
        if (!material) {
            material = graphDBService.addVertex(type, [
                    identifier : identifier,
                    publishDate: new Date(),
                    title      : title,
                    description: description?.replaceAll("<(.|\n)*?>", '') ?: '-',
                    imageId    : imageId ?: 0
            ])
        }

        def person = personGraphService.ensurePerson(owner)
        graphDBService.addEdge('Own', person, material)
        material
    }

    OrientVertex removeMaterial(Long identifier) {
        graphDBService.executeCommand("DELETE EDGE About WHERE out.identifier = ${identifier}")
        graphDBService.executeCommand("DELETE EDGE Share WHERE out.identifier = ${identifier}")
        def material = graphDBService.findVertex("SELECT FROM Material WHERE identifier = ${identifier}")
        graphDBService.deleteVertex(material.id.toString().replace('#', ''))
    }

    List<Map> listByGroup(String groupId, Integer skip = 0, Integer limit = 10) {
        graphDBService.queryAndUnwrapVertex("SELECT * FROM (SELECT EXPAND(IN('Share')) FROM Group WHERE @rid = #${groupId}) ORDER BY publishDate DESC SKIP ${skip} LIMIT ${limit}")
    }

    List<Map> topByGroup(String groupId, Integer skip = 0, Integer limit = 10) {
        graphDBService.queryAndUnwrapVertex("SELECT @rid as @rid, @class as @class, identifier, publishDate, title, description, imageId, AVG(INE('Rate').value) as rate FROM (SELECT EXPAND(IN('Share')) FROM Group WHERE @rid = #${groupId}) WHERE @class = 'Article' GROUP BY identifier ORDER BY rate DESC SKIP ${skip} LIMIT ${limit}")
    }

    List<Map> listByAuthor(String authorId, Integer skip = 0, Integer limit = 10) {
        graphDBService.queryAndUnwrapVertex("SELECT * FROM (SELECT EXPAND(OUT('Own')) FROM Person WHERE @rid = #${authorId}) WHERE @class = 'Article' ORDER BY publishDate DESC SKIP ${skip} LIMIT ${limit}")
    }

    List<Map> topByAuthor(String authorId, Integer skip = 0, Integer limit = 10) {
        graphDBService.queryAndUnwrapVertex("SELECT @rid as @rid, @class as @class, identifier, publishDate, title, description, imageId, AVG(INE('Rate').value) as rate FROM (SELECT EXPAND(OUT('Own')) FROM Person WHERE @rid = #${authorId}) WHERE @class = 'Article' GROUP BY identifier ORDER BY rate DESC SKIP ${skip} LIMIT ${limit}")
    }

    List<Map> listForHome(Long userId, Integer skip = 0, Integer limit = 10) {
        graphDBService.queryAndUnwrapVertex("SELECT * FROM (SELECT EXPAND(SET(UNIONALL(OUT('Follow').OUT('Own'), OUT('Follow').IN('About')))) From Person WHERE identifier = ${userId}) WHERE @class = 'Article' ORDER BY publishDate DESC SKIP ${skip} LIMIT ${limit}")
    }

    List<Map> topForHome(Long userId, Integer skip = 0, Integer limit = 10) {
        graphDBService.queryAndUnwrapVertex("SELECT @rid as @rid, @class as @class, identifier, publishDate, title, description, imageId, AVG(INE('Rate').value) as rate FROM (SELECT EXPAND(SET(UNIONALL(OUT('Follow').OUT('Own'), OUT('Follow').IN('About')))) From Person WHERE identifier = ${userId}) WHERE @class = 'Article' GROUP BY identifier ORDER BY rate DESC SKIP ${skip} LIMIT ${limit}")
    }

    List<Map> listByProperty(String propertyId, Integer skip = 0, Integer limit = 10) {
        graphDBService.queryAndUnwrapVertex("SELECT * FROM (SELECT EXPAND(IN('About')).@rid FROM Property WHERE @rid = #${propertyId}) WHERE @class = 'Article' ORDER BY publishDate DESC SKIP ${skip} LIMIT ${limit}")
    }

    List<Map> topByProperty(String groupId, Integer skip = 0, Integer limit = 10) {
        graphDBService.queryAndUnwrapVertex("SELECT @rid as @rid, @class as @class, identifier, publishDate, title, description, imageId, AVG(INE('Rate').value) as rate FROM (SELECT EXPAND(IN('About')) FROM Property WHERE @rid = #${groupId}) WHERE @class = 'Article' GROUP BY identifier ORDER BY rate DESC SKIP ${skip} LIMIT ${limit}")
    }

    List<Map> getPropertyList(String materialId) {
        graphDBService.queryAndUnwrapVertex("SELECT * FROM (SELECT EXPAND(OUT('About')) FROM Material WHERE @rid = #${materialId})")
    }

    OrientVertex getByIdentifier(Long id) {
        graphDBService.findVertex("SELECT FROM Material WHERE identifier = ${id}")
    }

    Map getAndUnwrapByIdentifier(Long id) {
        graphDBService.unwrapVertex(graphDBService.findVertex("SELECT FROM Material WHERE identifier = ${id}"))
    }

    List<Map> getRelatedMaterials(String id, Integer skip = 0, Integer limit = 10) {
        graphDBService.queryAndUnwrapVertex("SELECT * FROM Material WHERE @rid in (SELECT out.@rid FROM About WHERE in.@rid in (SELECT in.@rid FROM About WHERE out.@rid = #${id})) AND @rid <> #${id} ORDER BY publishDate DESC SKIP ${skip} LIMIT ${limit}")
    }

    List<Map> getNewMaterials(String id, Integer skip = 0, Integer limit = 10) {
        graphDBService.queryAndUnwrapVertex("SELECT * FROM Material WHERE @rid <> #${id} ORDER BY publishDate DESC SKIP ${skip} LIMIT ${limit}")
    }

    List<Map> getFollowList(Long userId, Integer skip = 0, Integer limit = 10) {
        graphDBService.queryAndUnwrapVertex("SELECT EXPAND(OUT('Follow')) FROM Person WHERE identifier = ${userId} SKIP ${skip} LIMIT ${limit}")
    }

    List<Map> getMeta(String id){
        graphDBService.queryAndUnwrapVertex("SELECT EXPAND(UNIONALL(IN('Own'), OUT('Share'))) FROM #${id}")
    }
}
