package stocks.commodity.persist

import stocks.commodity.TradeStatistics
import stocks.commodity.event.TradeStatisticsEvent

class TradeStatisticsPersistService {
    static transactional = false
    def bulkDataGateway
    def grailsApplication
    def queryService

    Boolean update(TradeStatisticsEvent event) {
        def tradeStatistics = event.data as TradeStatistics
        beforeUpdate(event, tradeStatistics)
        def result = tradeStatistics.domainClass.persistantProperties.findAll {
            !(it.name in ['creationDate', 'modificationDate'])
        }.any { property ->
            event.data."${property.name}" != event."${property.name}"
        }
        tradeStatistics.properties = event.properties.findAll {
            !(it.key.toString() in ['creationDate']) && !it.key.toString().endsWith('Id')
        }
        bulkDataGateway.save(tradeStatistics)
        afterUpdate(event, tradeStatistics)
        result
    }

    TradeStatistics create(TradeStatisticsEvent event) {
        beforeCreate(event)
        def data = new TradeStatistics(event.properties + [creationDate: new Date(), modificationDate: new Date()])
        bulkDataGateway.save(data)
        afterCreate(event, data)
        data
    }


    private void beforeCreate(TradeStatisticsEvent event) {

    }

    private void afterCreate(TradeStatisticsEvent event, TradeStatistics data) {
//        queryService.applyEventBasedQueries(data)
    }

    private void beforeUpdate(TradeStatisticsEvent event, TradeStatistics data) {

    }

    private void afterUpdate(TradeStatisticsEvent event, TradeStatistics data) {
    }
}
