package stocks.tse.persist

import stocks.tse.EnergyMarketValue
import stocks.tse.event.EnergyMarketValueEvent

class EnergyMarketValuePersistService {
    static transactional = false
    def bulkDataGateway

    Boolean update(EnergyMarketValueEvent event) {
        def energyMarketValue = EnergyMarketValue.get(event.data.id)
        beforeUpdate(event, energyMarketValue)
        energyMarketValue.properties = event.properties.findAll {
            !(it.key.toString() in ['creationDate']) && !it.key.toString().endsWith('Id')
        }
        energyMarketValue.modificationDate = new Date()
        bulkDataGateway.save(energyMarketValue)
        afterUpdate(event, energyMarketValue)
        false
    }

    EnergyMarketValue create(EnergyMarketValueEvent event) {
        beforeCreate(event)
        def data = new EnergyMarketValue(event.properties + [creationDate: new Date(), modificationDate: new Date()])
        bulkDataGateway.save(data)
        afterCreate(event, data)
        data
    }

    protected void beforeCreate(EnergyMarketValueEvent event) {
    }

    protected void afterCreate(EnergyMarketValueEvent event, EnergyMarketValue data) {

    }

    protected void beforeUpdate(EnergyMarketValueEvent event, EnergyMarketValue data) {
    }

    protected void afterUpdate(EnergyMarketValueEvent event, EnergyMarketValue data) {
    }
}
