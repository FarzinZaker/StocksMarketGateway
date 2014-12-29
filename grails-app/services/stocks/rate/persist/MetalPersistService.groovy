package stocks.rate.persist

import stocks.rate.Metal
import stocks.rate.event.MetalEvent

class MetalPersistService {
    static transactional = false
    def bulkDataGateway
    def grailsApplication
    def queryService

    Boolean update(MetalEvent event) {
        def metal = Metal.get(event.data.id)
        beforeUpdate(event, metal)
        def result = metal.domainClass.persistantProperties.findAll {
            !(it.name in ['creationDate', 'modificationDate']) &&
                    (it.type in [Integer, Long, Double, Boolean, Date, String])
        }.any { property ->
            event.data."${property.name}" != event."${property.name}"
        }
        metal.properties = event.properties.findAll {
            !(it.key.toString() in ['creationDate']) && !it.key.toString().endsWith('Id')
        }
        bulkDataGateway.save(metal)
        afterUpdate(event, metal)
        result
    }

    Metal create(MetalEvent event) {
        beforeCreate(event)
        def data = new Metal(event.properties + [creationDate: new Date(), modificationDate: new Date()])
        bulkDataGateway.save(data)
        afterCreate(event, data)
        data
    }

    protected void beforeCreate(MetalEvent event) {

    }

    protected void afterCreate(MetalEvent event, Metal data) {
        queryService.applyEventBasedQueries(data)
    }

    protected void beforeUpdate(MetalEvent event, Metal data) {

    }

    protected void afterUpdate(MetalEvent event, Metal data) {
    }
}
