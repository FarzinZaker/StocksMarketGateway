package stocks.rate.persist

import stocks.rate.Oil
import stocks.rate.event.OilEvent

class OilPersistService {
    static transactional = false
    def bulkDataGateway
    def grailsApplication
    def queryService

    Boolean update(OilEvent event) {
        def oil = Oil.get(event.data.id)
        beforeUpdate(event, oil)
        def result = oil.domainClass.persistantProperties.findAll {
            !(it.name in ['creationDate', 'modificationDate']) &&
                    (it.type in [Integer, Long, Double, Boolean, Date, String])
        }.any { property ->
            event.data."${property.name}" != event."${property.name}"
        }
        oil.properties = event.properties.findAll {
            !(it.key.toString() in ['creationDate']) && !it.key.toString().endsWith('Id')
        }
//        if (result)
        oil.modificationDate = new Date()
        bulkDataGateway.save(oil)
        afterUpdate(event, oil)
        result
    }

    Oil create(OilEvent event) {
        beforeCreate(event)
        def data = new Oil(event.properties + [creationDate: new Date(), modificationDate: new Date()])
        bulkDataGateway.save(data)
        afterCreate(event, data)
        data
    }

    protected void beforeCreate(OilEvent event) {

    }

    protected void afterCreate(OilEvent event, Oil data) {
        queryService.applyEventBasedQueries(data)
    }

    protected void beforeUpdate(OilEvent event, Oil data) {

    }

    protected void afterUpdate(OilEvent event, Oil data) {
    }
}
