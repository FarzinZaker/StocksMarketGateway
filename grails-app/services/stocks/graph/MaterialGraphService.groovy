package stocks.graph

import com.tinkerpop.blueprints.impls.orient.OrientVertex

class MaterialGraphService {

    def graphDBService

    public static String TYPE_ARTICLE = 'Article'
    public static String TYPE_SCREENER = 'Screener'
    public static String TYPE_BACKTEST = 'BackTest'
    public static String TYPE_PORTFOLIO = 'Portfolio'
    public static String TYPE_ANALYSIS = 'Analysis'

    OrientVertex ensureMaterial(String type, Long identifier, String title, String description, Long imageId) {

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
        material
    }

    OrientVertex removeMaterial(Long identifier) {
        graphDBService.executeCommand("DELETE EDGE About WHERE out.identifier = ${identifier}")
        graphDBService.executeCommand("DELETE EDGE Share WHERE out.identifier = ${identifier}")
        def material = graphDBService.findVertex("SELECT FROM Material WHERE identifier = ${identifier}")
        graphDBService.deleteVertex(material.id.toString().replace('#', ''))
    }

    List<Map> listByGroup(String groupId, Integer skip = 0, Integer limit = 10) {
        attachPropertyList(graphDBService.queryVertex("SELECT * FROM (SELECT EXPAND(IN('Share')) FROM Group WHERE @rid = #${groupId}) ORDER BY publishDate DESC SKIP ${skip} LIMIT ${limit}"))
    }

    private List<Map> attachPropertyList(List<OrientVertex> list){
        list.collect{
            def item = graphDBService.unwrapVertex(it)
            item.propertyList = graphDBService.queryAndUnwrapVertex("SELECT * FROM (SELECT EXPAND(OUT('About')) FROM Material WHERE @rid = #${item.idNumber})")
            item
        }
    }

    OrientVertex getByIdentifier(Long id){
        graphDBService.findVertex("SELECT FROM Material WHERE identifier = ${id}")
    }

    Map getAndUnwrapByIdentifier(Long id){
        graphDBService.unwrapVertex(graphDBService.findVertex("SELECT FROM Material WHERE identifier = ${id}"))
    }

    List<Map> getRelatedMaterials(String id, Integer skip = 0, Integer limit = 10){
        graphDBService.queryAndUnwrapVertex("SELECT * FROM Material WHERE @rid in (SELECT out.@rid FROM About WHERE in.@rid in (SELECT in.@rid FROM About WHERE out.@rid = #${id})) AND @rid <> #${id} ORDER BY publishDate DESC SKIP ${skip} LIMIT ${limit}")
    }

    List<Map> getNewMaterials(String id, Integer skip = 0, Integer limit = 10){
        graphDBService.queryAndUnwrapVertex("SELECT * FROM Material WHERE @rid <> #${id} ORDER BY publishDate DESC SKIP ${skip} LIMIT ${limit}")
    }
}
