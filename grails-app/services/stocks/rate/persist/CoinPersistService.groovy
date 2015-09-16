package stocks.rate.persist

import stocks.rate.Coin
import stocks.rate.event.CoinEvent

class CoinPersistService {
    static transactional = false
    def bulkDataGateway
    def grailsApplication
    def queryService

    Boolean update(CoinEvent event) {
        def coin = Coin.get(event.data.id)
        beforeUpdate(event, coin)
        def result = coin.domainClass.persistantProperties.findAll {
            !(it.name in ['creationDate', 'modificationDate']) &&
                    (it.type in [Integer, Long, Double, Boolean, Date, String])
        }.any { property ->
            event.data."${property.name}" != event."${property.name}"
        }
        coin.properties = event.properties.findAll {
            !(it.key.toString() in ['creationDate']) && !it.key.toString().endsWith('Id')
        }
//        if (result)
        coin.modificationDate = new Date()
        bulkDataGateway.save(coin)
        afterUpdate(event, coin)
        result
    }

    Coin create(CoinEvent event) {
        beforeCreate(event)
        def data = new Coin(event.properties + [creationDate: new Date(), modificationDate: new Date()])
        bulkDataGateway.save(data)
        afterCreate(event, data)
        data
    }

    protected void beforeCreate(CoinEvent event) {

    }

    protected void afterCreate(CoinEvent event, Coin data) {
        queryService.applyEventBasedQueries(data)
    }

    protected void beforeUpdate(CoinEvent event, Coin data) {

    }

    protected void afterUpdate(CoinEvent event, Coin data) {
    }
}
