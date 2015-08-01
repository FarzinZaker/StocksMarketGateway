package stocks.graph

import com.orientechnologies.orient.core.metadata.schema.OType

class PropertyGraphService {

    def graphDBService

    def init() {
        def propertyClass = graphDBService.ensureVertexClass('Property')
        graphDBService.ensureProperty(propertyClass, 'id', OType.LONG)
        [
                'PropertyCoin',
                'PropertyCurrency',
                'PropertyFuture',
                'PropertyMetal',
                'PropertyOil',
                'PropertySymbol',
        ].each {
            clazz ->
                graphDBService.ensureVertexClass(clazz, propertyClass)
        }
    }
}
