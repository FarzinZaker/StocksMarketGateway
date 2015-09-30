package stocks.tools.correlation

import stocks.RateHelper
import stocks.rate.Oil
import stocks.tools.CorrelationServiceBase

class OilCorrelationService extends CorrelationServiceBase {

    def oilSeries9Service

    @Override
    List searchItems(String term) {

        RateHelper.OILS.findAll {
            ['WTI-Crude-Oil-Nymex'].contains(it.key) &&
                    it.value.name.toLowerCase().contains(term.toLowerCase())
        }.collect {
            def symbol = it.key
            [
                    text : it.value.name,
                    value: Oil.createCriteria().list {
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
        RateHelper.OILS."${Oil.get(item as Long)?.symbol}".name
    }

    @Override
    def all() {
        Oil.createCriteria().list {
            'in'('symbol', ['WTI-Crude-Oil-Nymex'])
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
            result.put(itemId?.toString(), oilSeries9Service.priceList(itemId as Long, startDate, endDate, groupingMode).collect {
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
        oilSeries9Service.priceList(item as Long, startDate, endDate, groupingMode)
    }

    @Override
    Double getBaseValue(String item, Date startDate, String adjustmentType) {
        oilSeries9Service.lastPrice(item as Long, startDate) ?: 0
    }

    @Override
    Map<String, Double> getBaseValueCache(List<String> items, Date startDate, String adjustmentType) {
        def result = [:]
        items.each { itemId ->
            result.put(itemId, oilSeries9Service.lastPrice(itemId as Long, startDate))
        }
        result
    }
}
