package stocks.tools.correlation

import org.apache.lucene.search.BooleanQuery
import stocks.tools.CorrelationServiceBase
import stocks.tse.AdjustmentHelper
import stocks.tse.Company
import stocks.tse.Symbol
import stocks.tse.SymbolAdjustedDailyTrade
import stocks.tse.SymbolDailyTrade

class CompanyCorrelationService extends CorrelationServiceBase {

    def adjustedPriceSeriesService

    @Override
    List searchItems(String queryStr) {

        BooleanQuery.setMaxClauseCount(1000000)
        Symbol.search("*${queryStr}* AND ((marketCode:MCNO AND (type:300 OR type:303 OR type:309) AND -boardCode:4) OR status:I)").results.unique { a, b -> a?.id <=> b?.id }.collect {
            [
                    text : "${it.persianCode} - ${it.persianName}",
                    value: it.id
            ]
        }
    }

    @Override
    String getItemName(String item) {
        def symbol = Symbol.get(item as Long)
        "${symbol.persianCode} - ${symbol.persianName}"
    }

    @Override
    def all() {
        Symbol.createCriteria().list {
            or {
                and {
                    eq('marketCode', 'MCNO')
                    'in'('type', ['300', '303', '309'])
                    notEqual('boardCode', '4')
                }
                eq('status', 'I')
            }
            projections {
                property('id')
            }
        }
    }

    @Override
    Map<String, List> getItemValuesCache(List<String> items, Date startDate, Date endDate, String period) {

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
        items.each { symbolId ->
            result.put(symbolId, adjustedPriceSeriesService.closingPriceList(symbolId as Long, startDate, endDate, groupingMode).collect {
                [
                        date : it.date,
                        value: it.value
                ]

            })
        }
        result
    }

    @Override
    List getItemValues(String item, Date startDate, Date endDate, String period) {

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

        adjustedPriceSeriesService.closingPriceList(item as Long, startDate, endDate, groupingMode)
    }

    @Override
    Double getBaseValue(String item, Date startDate) {
        adjustedPriceSeriesService.lastClosingPrice(item as Long, startDate) ?: 0
    }

    @Override
    Map<String, Double> getBaseValueCache(List<String> items, Date startDate) {
        def result = [:]
        items.each {symbolId ->
            result.put(symbolId, adjustedPriceSeriesService.lastClosingPrice(symbolId as Long, startDate))
        }
        result
    }
}
