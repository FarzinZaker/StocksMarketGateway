package stocks.rate.persist

import stocks.rate.Currency
import stocks.rate.event.CurrencyEvent

class CurrencyPersistService {
    static transactional = false
    def bulkDataGateway
    def grailsApplication
    def queryService

    Boolean update(CurrencyEvent event) {
        def currency = Currency.get(event.data.id)
        beforeUpdate(event, currency)
        def result = currency.domainClass.persistantProperties.findAll {
            !(it.name in ['creationDate', 'modificationDate']) &&
                    (it.type in [Integer, Long, Double, Boolean, Date, String])
        }.any { property ->
            event.data."${property.name}" != event."${property.name}"
        }
        currency.properties = event.properties.findAll {
            !(it.key.toString() in ['creationDate']) && !it.key.toString().endsWith('Id')
        }
        bulkDataGateway.save(currency)
        afterUpdate(event, currency)
        result
    }

    Currency create(CurrencyEvent event) {
        beforeCreate(event)
        def data = new Currency(event.properties + [creationDate: new Date(), modificationDate: new Date()])
        bulkDataGateway.save(data)
        afterCreate(event, data)
        data
    }

    protected void beforeCreate(CurrencyEvent event) {

    }

    protected void afterCreate(CurrencyEvent event, Currency data) {
        queryService.applyEventBasedQueries(data)
    }

    protected void beforeUpdate(CurrencyEvent event, Currency data) {

    }

    protected void afterUpdate(CurrencyEvent event, Currency data) {
    }
}
