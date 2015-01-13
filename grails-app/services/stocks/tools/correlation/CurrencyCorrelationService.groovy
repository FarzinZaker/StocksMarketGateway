package stocks.tools.correlation

import stocks.RateHelper
import stocks.rate.event.CurrencyEvent
import stocks.tools.CorrelationServiceBase

class CurrencyCorrelationService extends CorrelationServiceBase {

    @Override
    List searchItems(String term) {

        RateHelper.CURRENCIES.findAll{
            it.value.name.toLowerCase().contains(term.toLowerCase())
        }.collect {
            [
                    text : it.value.name,
                    value: it.key
            ]
        }
    }

    @Override
    String getItemName(String item) {
        RateHelper.CURRENCIES."${item}".name
    }

    @Override
    def all() {
        RateHelper.CURRENCIES.keySet().toList()
    }

    @Override
    Map<String, List> getItemValuesCache(List<String> items, Date startDate, Date endDate, String period) {
        def itemList = CurrencyEvent.createCriteria().list {
            'in'('symbol', items)
            isNotNull("${period}Snapshot")
            gte("${period}Snapshot", startDate)
            lte("${period}Snapshot", endDate)
            projections {
                property("symbol")
                property("${period}Snapshot")
                property('price')
            }
        }.collect {
            [
                    item : it[0].toString(),
                    date : it[1],
                    value: it[2]
            ]
        }

        def result = [:]
        itemList.each {
            if (!result.keySet().contains(it.item))
                result.put(it.item, [[date: it.date, value: it.value]])
            else
                result[it.item].add([date: it.date, value: it.value])
        }
        result
    }

    @Override
    List getItemValues(String item, Date startDate, Date endDate, String period) {

        CurrencyEvent.createCriteria().list {
            eq('symbol', item)
            isNotNull("${period}Snapshot")
            gte("${period}Snapshot", startDate)
            lte("${period}Snapshot", endDate)
            projections {
                property("${period}Snapshot")
                property('price')
            }
        }.collect {
            [
                    date : it[0],
                    value: it[1]
            ]
        }
    }

    @Override
    Double getBaseValue(String item, Date startDate) {
        CurrencyEvent.get(CurrencyEvent.createCriteria().get {
            eq('symbol', item)
            lt('creationDate', startDate)
            projections {
                max('id')
            }
        })?.price ?: 0
    }

    @Override
    Map<String, Double> getBaseValueCache(List<String> items, Date startDate) {
        def list = CurrencyEvent.createCriteria().list {
            'in'('symbol', items)
            lt('creationDate', startDate)
            projections {
                groupProperty('symbol')
                max('id')
            }
        }
        def events = CurrencyEvent.findAllByIdInList(list.collect { it[1] })
        def result = [:]
        list.each { item ->
            def id = item[0].toString()
            def value = events.find { it.id == (item[1] as Double) }?.price ?: 0
            if (!result.containsKey(id))
                result.put(id, value)
        }

        RateHelper.CURRENCIES.each {
            if (!result.keySet().contains(it.key.toString()))
                result.put(it.key.toString(), 0)
        }
        result
    }
}