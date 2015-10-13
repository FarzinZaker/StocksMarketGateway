package stocks.graph

import com.tinkerpop.blueprints.impls.orient.OrientVertex

class MaterialGraphService {

    def graphDBService

    public static String TYPE_ARTICLE = 'Article'
    public static String TYPE_SCREENER = 'Screener'
    public static String TYPE_BACKTEST = 'BackTest'
    public static String TYPE_PORTFOLIO = 'Portfolio'
    public static String TYPE_ANALYSIS = 'Analysis'

    OrientVertex ensureMaterial(String type, Long identifier, String title) {

        graphDBService.executeCommand("UPDATE Material SET title = '${title}' WHERE identifier = ${identifier}")

        def material = graphDBService.findVertex("SELECT FROM Material WHERE identifier = ${identifier}")
        if (!material) {
            material = graphDBService.addVertex(type, [
                    identifier : identifier,
                    publishDate: new Date()
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
}
