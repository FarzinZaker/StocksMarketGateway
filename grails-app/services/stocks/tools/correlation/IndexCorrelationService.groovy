package stocks.tools.correlation

import org.apache.lucene.search.BooleanQuery
import stocks.tools.CorrelationServiceBase
import stocks.tse.BlackListedIndex
import stocks.tse.BlackListedSymbol
import stocks.tse.Index
import stocks.tse.IndexHistory
import stocks.tse.event.IndexEvent

class IndexCorrelationService extends CorrelationServiceBase {

    def indexSeries9Service

    @Override
    List searchItems(String term) {

        BooleanQuery.setMaxClauseCount(1000000)
        Index.search("*${term}*${BlackListedIndex.compassQuery()}", max: 10000).results.collect {
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
        Index.findAllByPersianNameNotInList(BlackListedIndex.persianNameList())*.id
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
        items.each { indexId ->
            result.put(indexId?.toString(), indexSeries9Service.finalIndexValueList(indexId as Long, startDate, endDate, groupingMode).collect {
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

        indexSeries9Service.finalIndexValueList(item as Long, startDate, endDate, groupingMode)
    }

    @Override
    Double getBaseValue(String item, Date startDate, String adjustmentType) {
        indexSeries9Service.lastFinalIndexValue(item as Long, startDate) ?: 0
    }

    @Override
    Map<String, Double> getBaseValueCache(List<String> items, Date startDate, String adjustmentType) {
        def result = [:]
        items.each { indexId ->
            result.put(indexId, indexSeries9Service.lastFinalIndexValue(indexId as Long, startDate))
        }
        result
    }
}
