package stocks.rate.persist

import stocks.rate.CoinFuture
import stocks.rate.event.CoinFutureEvent

class CoinFuturePersistService {
//    static transactional = false
    def bulkDataGateway
    def grailsApplication
    def queryService

    Boolean update(CoinFutureEvent event) {
        def future = CoinFuture.get(event.data.id)
        beforeUpdate(event, future)
        def result = future.domainClass.persistantProperties.findAll {
            !(it.name in ['creationDate', 'modificationDate']) &&
                    (it.type in [Integer, Long, Double, Boolean, Date, String])
        }.any { property ->
            event.data."${property.name}" != event."${property.name}"
        }
        future.properties = event.properties.findAll {
            !(it.key.toString() in ['creationDate']) && !it.key.toString().endsWith('Id')
        }
        future.save(flush: true)
//        bulkDataGateway.save(future)
        afterUpdate(event, future)
        result
    }

    CoinFuture create(CoinFutureEvent event) {
        beforeCreate(event)
        def data = new CoinFuture(event.properties + [creationDate: new Date(), modificationDate: new Date()])
        bulkDataGateway.save(data)
        afterCreate(event, data)
        data
    }

    protected void beforeCreate(CoinFutureEvent event) {

    }

    protected void afterCreate(CoinFutureEvent event, CoinFuture data) {
        queryService.applyEventBasedQueries(data)
    }

    protected void beforeUpdate(CoinFutureEvent event, CoinFuture data) {

    }

    protected void afterUpdate(CoinFutureEvent event, CoinFuture data) {
    }
}
