package stocks.tools.correlation

import stocks.RateHelper
import stocks.rate.Currency
import stocks.rate.event.CurrencyEvent
import stocks.tools.CorrelationServiceBase

class CurrencyCorrelationService extends CorrelationServiceBase {

    def currencySeries9Service

    @Override
    List searchItems(String term) {

        RateHelper.CURRENCIES.findAll {
            ['us-dollar-exchange', 'us-dollar', 'euro', 'gbp', 'aed','chinese-yuan', 'lear-turkey'].contains(it.key) &&
                    it.value.name.toLowerCase().contains(term.toLowerCase())
        }.collect {
            def symbol = it.key
            [
                    text : it.value.name,
                    value: Currency.createCriteria().list {
                        eq('symbol', symbol)
                        projections {
                            property('id')
                        }
                    }?.find()
            ]
        }
    }

    @Override
    String getItemName(String item) {
        RateHelper.CURRENCIES."${Currency.get(item as Long)?.symbol}".name
    }

    @Override
    def all() {
        Currency.createCriteria().list {
            'in'('symbol', ['us-dollar-exchange', 'us-dollar', 'euro', 'gbp', 'aed','chinese-yuan', 'lear-turkey'])
            projections {
                property('id')
            }
        }
    }

    @Override
    Map<String, List> getItemValuesCache(List<String> items, Date startDate, Date endDate, String period, String adjustmentType) {

        def groupingMode = '1d'
        switch (period) {
            case 'daily':
                groupingMode = '1d'
                break
            case 'weekly':
                groupingMode = '1w'
                break
            case 'monthly':
                groupingMode = '30d'
                break
        }

        def result = [:]
        items.each { itemId ->
            result.put(itemId?.toString(), currencySeries9Service.priceList(itemId as Long, startDate, endDate, groupingMode).collect {
                [
                        date : it.date,
                        value: it.value
                ]

            })
        }
        result
    }

    @Override
    List getItemValues(String item, Date startDate, Date endDate, String period, String adjustmentType) {

        def groupingMode = '1d'
        switch (period) {
            case 'daily':
                groupingMode = '1d'
                break
            case 'weekly':
                groupingMode = '1w'
                break
            case 'monthly':
                groupingMode = '30d'
                break
        }
        currencySeries9Service.priceList(item as Long, startDate, endDate, groupingMode)
    }

    @Override
    Double getBaseValue(String item, Date startDate, String adjustmentType) {
        currencySeries9Service.lastPrice(item as Long, startDate) ?: 0
    }

    @Override
    Map<String, Double> getBaseValueCache(List<String> items, Date startDate, String adjustmentType) {
        def result = [:]
        items.each { itemId ->
            result.put(itemId, currencySeries9Service.lastPrice(itemId as Long, startDate))
        }
        result
    }
}
