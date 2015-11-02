package stocks.tse.persist

import stocks.tse.SymbolAverageTrade
import stocks.tse.event.SymbolAverageTradeEvent

class SymbolAverageTradePersistService {
    static transactional = false
    def bulkDataGateway

    Boolean update(SymbolAverageTradeEvent event) {
        def marketValue = SymbolAverageTrade.get(event.data.id)
        beforeUpdate(event, marketValue)
        marketValue.properties = event.properties.findAll {
            !(it.key.toString() in ['creationDate']) && !it.key.toString().endsWith('Id')
        }
        bulkDataGateway.save(marketValue)
        afterUpdate(event, marketValue)
        false
    }

    SymbolAverageTrade create(SymbolAverageTradeEvent event) {
        beforeCreate(event)
        def data = new SymbolAverageTrade(event.properties + [creationDate: new Date(), modificationDate: new Date()])
        bulkDataGateway.save(data)
        afterCreate(event, data)
        data
    }

    protected void beforeCreate(SymbolAverageTradeEvent event) {
    }

    protected void afterCreate(SymbolAverageTradeEvent event, SymbolAverageTrade data) {

    }

    protected void beforeUpdate(SymbolAverageTradeEvent event, SymbolAverageTrade data) {
    }

    protected void afterUpdate(SymbolAverageTradeEvent event, SymbolAverageTrade data) {
    }
}
