package stocks.tools.correlation

import org.apache.lucene.search.BooleanQuery
import stocks.tools.CorrelationServiceBase
import stocks.tse.Index
import stocks.tse.event.IndexEvent

class IndexCorrelationService extends CorrelationServiceBase {

    @Override
    List searchItems(String term) {

        BooleanQuery.setMaxClauseCount(1000000)
        Index.search("*${term}*", max: 10000).results.collect {
            [
                    text : it.persianName,
                    value: it.id
            ]
        }
    }

    @Override
    String getItemName(String item) {
        Index.get(item as Long).persianName
    }

    @Override
    def all() {
        Index.list()*.id
    }

    @Override
    Map<String, List> getItemValuesCache(List<String> items, Date startDate, Date endDate, String period) {
        List<Index> dataList = Index.findAllByIdInList(items.collect { it as Long })
        def itemList = IndexEvent.createCriteria().list {
            'in'('data', dataList)
            isNotNull("${period}Snapshot")
            gte("${period}Snapshot", startDate)
            lte("${period}Snapshot", endDate)
            projections {
                data {
                    property('id')
                }
                property("${period}Snapshot")
                property('finalIndexValue')
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
        Index data = Index.get(item as Long)
        IndexEvent.createCriteria().list {
            eq('data', data)
            isNotNull("${period}Snapshot")
            gte("${period}Snapshot", startDate)
            lte("${period}Snapshot", endDate)
            projections {
                property("${period}Snapshot")
                property('finalIndexValue')
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
        Index data = Index.get(item as Long)
        IndexEvent.get(IndexEvent.createCriteria().get {
            eq('data', data)
            lt('creationDate', startDate)
            projections {
                max('id')
            }
        })?.finalIndexValue ?: 0
    }

    @Override
    Map<String, Double> getBaseValueCache(List<String> items, Date startDate) {
        def indexes = Index.findAllByIdInList(items.collect { it as Long })
        def list = IndexEvent.createCriteria().list {
            'in'('data', indexes)
            lt('creationDate', startDate)
            projections {
                groupProperty('data')
                max('id')
            }
        }
        def indexEvents = IndexEvent.findAllByIdInList(list.collect { it[1] })
        def result = [:]
        list.each { item ->
            def futureId = (item[0] as Index).id?.toString()
            def value = indexEvents.find { it.id == (item[1] as Double) }?.finalIndexValue ?: 0
            if (!result.containsKey(futureId))
                result.put(futureId, value)
        }

        indexes.each {
            if (!result.keySet().contains(it.id.toString()))
                result.put(it.id.toString(), 0)
        }
        result
    }
}
