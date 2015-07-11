package stocks.commodity.persist

import stocks.commodity.CommodityMarketActivity
import stocks.commodity.event.CommodityMarketActivityEvent

class CommodityMarketActivityPersistService {

    static transactional = false
    def bulkDataGateway
    def grailsApplication
    def queryService

    Boolean update(CommodityMarketActivityEvent event) {
        def marketActivity = event.data as CommodityMarketActivity
        beforeUpdate(event, marketActivity)
        def result = marketActivity.domainClass.persistantProperties.findAll {
            !(it.name in ['creationDate', 'modificationDate'])
        }.any { property ->
            event.data."${property.name}" != event."${property.name}"
        }
        marketActivity.properties = event.properties.findAll {
            !(it.key.toString() in ['creationDate']) && !it.key.toString().endsWith('Id')
        }
        bulkDataGateway.save(marketActivity)
        afterUpdate(event, marketActivity)
        result
    }

    CommodityMarketActivity create(CommodityMarketActivityEvent event) {
        beforeCreate(event)
        def data = new CommodityMarketActivity(event.properties + [creationDate: new Date(), modificationDate: new Date()])
        bulkDataGateway.save(data)
        afterCreate(event, data)
        data
    }


    private void beforeCreate(CommodityMarketActivityEvent event) {

    }

    private void afterCreate(CommodityMarketActivityEvent event, CommodityMarketActivity data) {
//        queryService.applyEventBasedQueries(data)
    }

    private void beforeUpdate(CommodityMarketActivityEvent event, CommodityMarketActivity data) {

    }

    private void afterUpdate(CommodityMarketActivityEvent event, CommodityMarketActivity data) {
    }
}
