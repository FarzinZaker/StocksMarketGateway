package stocks.tse.persist

import stocks.tse.MarketValue
import stocks.tse.event.MarketValueEvent

class MarketValuePersistService {
    static transactional = false
    def bulkDataGateway

    Boolean update(MarketValueEvent event) {
        def marketValue = MarketValue.get(event.data.id)
        beforeUpdate(event, marketValue)
        marketValue.properties = event.properties.findAll {
            !(it.key.toString() in ['creationDate']) && !it.key.toString().endsWith('Id')
        }
        bulkDataGateway.save(marketValue)
        afterUpdate(event, marketValue)
        false
    }

    MarketValue create(MarketValueEvent event) {
        beforeCreate(event)
        def data = new MarketValue(event.properties + [creationDate: new Date(), modificationDate: new Date()])
        bulkDataGateway.save(data)
        afterCreate(event, data)
        data
    }

    private void updateValueChange(MarketValueEvent marketValueEvent) {
        def previousMarketValue = MarketValue.createCriteria().list {
            eq('marketIdentifier', marketValueEvent.marketIdentifier)
            lt('date', marketValueEvent.date)
            order('date', ORDER_DESCENDING)
            maxResults(1)
            projections {
                property('value')
            }
        }?.find()

        marketValueEvent.valueChange = marketValueEvent.value - (previousMarketValue ?: 0)
    }

    protected void beforeCreate(MarketValueEvent event) {
        updateValueChange(event)
    }

    protected void afterCreate(MarketValueEvent event, MarketValue data) {

    }

    protected void beforeUpdate(MarketValueEvent event, MarketValue data) {
        updateValueChange(event)
    }

    protected void afterUpdate(MarketValueEvent event, MarketValue data) {
    }
}
