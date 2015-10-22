package stocks.graph

import com.tinkerpop.blueprints.impls.orient.OrientVertex

class PropertyGraphService {

    def graphDBService

    public static String TYPE_COIN = 'Coin'
    public static String TYPE_CURRENCY = 'Currency'
    public static String TYPE_FUTURE = 'Future'
    public static String TYPE_METAL = 'Metal'
    public static String TYPE_OIL = 'Oil'
    public static String TYPE_SYMBOL = 'Symbol'

    OrientVertex ensureProperty(String type, Long identifier, String title) {

        def material = graphDBService.findVertex("SELECT FROM Property WHERE identifier = ${identifier}")
        if (!material) {
            material = graphDBService.addVertex(type, [
                    identifier: identifier,
                    title     : title
            ])
        }
        material
    }

    List<Map> propertyCloud(){
        graphDBService.queryAndUnwrapVertex("SELECT @rid, @class as label, identifier, title, IN('About').size() AS count FROM Property GROUP BY @rid ORDER BY count DESC")
    }

    OrientVertex getByIdentifier(Long id) {
        graphDBService.findVertex("SELECT FROM Property WHERE identifier = ${id}")
    }

    Map getAndUnwrapByIdentifier(Long id) {
        graphDBService.unwrapVertex(graphDBService.findVertex("SELECT FROM Property WHERE identifier = ${id}"))
    }

    List<Map> authorList(String propertyId) {
        graphDBService.queryAndUnwrapVertex("SELECT * FROM Person WHERE @rid IN (SELECT IN('About').IN('Own').@rid FROM Property WHERE @rid = #${propertyId})")
    }

    List<Map> propertyCloud(String propertyId){
        graphDBService.queryAndUnwrapVertex("SELECT * FROM (SELECT EXPAND(IN('About').OUT('About')) FROM Property WHERE @rid = #${propertyId}) WHERE @rid <> #${propertyId}")
    }

}
