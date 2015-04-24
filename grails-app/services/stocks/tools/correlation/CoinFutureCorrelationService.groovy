package stocks.tools.correlation

import org.apache.lucene.search.BooleanQuery
import stocks.rate.CoinFuture
import stocks.rate.event.CoinFutureEvent
import stocks.tools.CorrelationServiceBase
import stocks.tse.event.FutureEvent

class CoinFutureCorrelationService extends CorrelationServiceBase {

    @Override
    List searchItems(String term) {

        BooleanQuery.setMaxClauseCount(1000000)
        CoinFuture.search("*${term}*", max: 10000).results.findAll { it.contractCode.contains('GC') }.sort {
            it.lastTradingDate
        }.collect {
            [
                    text : it.contractDescription,
                    value: it.id
            ]
        }
    }

    @Override
    String getItemName(String item) {
        CoinFuture.get(item as Long).contractDescription
    }

    @Override
    def all() {
        CoinFuture.list().sort { it.lastTradingDate }*.id
    }

    @Override
    Map<String, List> getItemValuesCache(List<String> items, Date startDate, Date endDate, String period) {
        List<CoinFuture> dataList = CoinFuture.findAllByIdInList(items.collect { it as Long })
        def itemList = CoinFutureEvent.createCriteria().list {
            'in'('data', dataList)
            isNotNull("${period}Snapshot")
            gte("${period}Snapshot", startDate)
            lte("${period}Snapshot", endDate)
            gt("lastTradedPrice", 0D)
            projections {
                data {
                    property('id')
                }
                property("${period}Snapshot")
                property('closingPrice')
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
        CoinFuture data = CoinFuture.get(item as Long)
        CoinFutureEvent.createCriteria().list {
            eq('data', data)
            isNotNull("${period}Snapshot")
            gte("${period}Snapshot", startDate)
            lte("${period}Snapshot", endDate)
            gt("lastTradedPrice", 0D)
            projections {
                property("${period}Snapshot")
                property('closingPrice')
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
        CoinFuture data = CoinFuture.get(item as Long)
        CoinFutureEvent.get(CoinFutureEvent.createCriteria().get {
            eq('data', data)
            lt('creationDate', startDate)
            projections {
                max('id')
            }
        })?.closingPrice ?: 0
    }

    @Override
    Map<String, Double> getBaseValueCache(List<String> items, Date startDate) {
        def futures = CoinFuture.findAllByIdInList(items.collect { it as Long })
        def list = CoinFutureEvent.createCriteria().list {
            'in'('data', futures)
            lt('creationDate', startDate)
            projections {
                groupProperty('data')
                max('id')
            }
        }
        def futureEvents = CoinFutureEvent.findAllByIdInList(list.collect { it[1] })
        def result = [:]
        list.each { item ->
            def futureId = (item[0] as CoinFuture).id?.toString()
            def value = futureEvents.find { it.id == (item[1] as Double) }?.closingPrice ?: 0
            if (!result.containsKey(futureId))
                result.put(futureId, value)
        }

        futures.each {
            if (!result.keySet().contains(it.id.toString()))
                result.put(it.id.toString(), 0)
        }
        result
    }
}
