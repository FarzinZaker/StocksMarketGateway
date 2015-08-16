package stocks.tools.correlation

import org.apache.lucene.search.BooleanQuery
import stocks.rate.CoinFuture
import stocks.rate.event.CoinFutureEvent
import stocks.tools.CorrelationServiceBase
import stocks.tse.event.FutureEvent

class CoinFutureCorrelationService extends CorrelationServiceBase {

    def futureSeries9Service

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
            result.put(indexId?.toString(), futureSeries9Service.closingPriceList(indexId as Long, startDate, endDate, groupingMode).collect {
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

        futureSeries9Service.closingPriceList(item as Long, startDate, endDate, groupingMode)
    }

    @Override
    Double getBaseValue(String item, Date startDate, String adjustmentType) {
        futureSeries9Service.lastClosingPrice(item as Long, startDate) ?: 0
    }

    @Override
    Map<String, Double> getBaseValueCache(List<String> items, Date startDate, String adjustmentType) {
        def result = [:]
        items.each {indexId ->
            result.put(indexId, futureSeries9Service.lastClosingPrice(indexId as Long, startDate))
        }
        result
    }
}
