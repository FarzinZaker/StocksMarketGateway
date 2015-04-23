package stocks.tools.correlation

import org.apache.lucene.search.BooleanQuery
import stocks.tools.CorrelationServiceBase
import stocks.tse.Index
import stocks.tse.IndexHistory
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
        def itemList = IndexHistory.createCriteria().list {
            'in'('index', dataList)
            isNotNull("${period}Snapshot")
            gte("${period}Snapshot", startDate)
            lte("${period}Snapshot", endDate)
            projections {
                'index' {
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
        IndexHistory.createCriteria().list {
            eq('index', data)
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
        IndexEvent.get(IndexHistory.createCriteria().get {
            eq('index', data)
            lt('creationDate', startDate)
            projections {
                max('id')
            }
        })?.finalIndexValue ?: 0
    }

    @Override
    Map<String, Double> getBaseValueCache(List<String> items, Date startDate) {
        def indexes = Index.findAllByIdInList(items.collect { it as Long })
        def list = IndexHistory.createCriteria().list {
            'in'('index', indexes)
            lt('creationDate', startDate)
            projections {
                groupProperty('index')
                max('id')
            }
        }
        def indexEvents = IndexHistory.findAllByIdInList(list.collect { it[1] })
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
