package stocks.tools.correlation

import org.apache.lucene.search.BooleanQuery
import stocks.tools.CorrelationServiceBase
import stocks.tse.Index
import stocks.tse.IndexHistory
import stocks.tse.event.IndexEvent

class IndexCorrelationService extends CorrelationServiceBase {

    def indexSeriesService

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
    Map<String, List> getItemValuesCache(List<String> items, Date startDate, Date endDate, String period, String adjustmentType) {

        def groupingMode = '1d'
        switch (period){
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
            result.put(indexId?.toString(), indexSeriesService.finalIndexValueList(indexId as Long, startDate, endDate, groupingMode).collect {
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
        switch (period){
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

        indexSeriesService.finalIndexValueList(item as Long, startDate, endDate, groupingMode)
    }

    @Override
    Double getBaseValue(String item, Date startDate, String adjustmentType) {
        indexSeriesService.lastFinalIndexValue(item as Long, startDate) ?: 0
    }

    @Override
    Map<String, Double> getBaseValueCache(List<String> items, Date startDate, String adjustmentType) {
        def result = [:]
        items.each {indexId ->
            result.put(indexId, indexSeriesService.lastFinalIndexValue(indexId as Long, startDate))
        }
        result
    }
}
