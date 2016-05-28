package stocks.tools.correlation

import org.apache.lucene.search.BooleanQuery
import stocks.tools.CorrelationServiceBase
import stocks.tse.AdjustmentHelper
import stocks.tse.BlackListedSymbol
import stocks.tse.Company
import stocks.tse.Symbol
import stocks.tse.SymbolAdjustedDailyTrade
import stocks.tse.SymbolDailyTrade

class CompanyCorrelationService extends CorrelationServiceBase {

    def adjustedPriceSeries9Service

    @Override
    List searchItems(String queryStr) {

        BooleanQuery.setMaxClauseCount(1000000)
        Symbol.search("*${queryStr}*${BlackListedSymbol.compassQuery()} AND ((marketCode:MCNO AND (type:300 OR type:303 OR type:309) AND -boardCode:4) OR status:I)", max: 20).results.unique { a, b -> a?.id <=> b?.id }.collect {
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
                    not {
                        'in'('persianCode', BlackListedSymbol.persianCodeList())
                    }
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
        items.each { symbolId ->
            result.put(symbolId, adjustedPriceSeries9Service.closingPriceList(symbolId as Long, startDate, endDate, groupingMode, adjustmentType).collect {
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

        adjustedPriceSeries9Service.closingPriceList(item as Long, startDate, endDate, groupingMode, adjustmentType)
    }

    @Override
    Double getBaseValue(String item, Date startDate, String adjustmentType) {
        adjustedPriceSeries9Service.lastClosingPrice(item as Long, startDate, adjustmentType) ?: 0
    }

    @Override
    Map<String, Double> getBaseValueCache(List<String> items, Date startDate, String adjustmentType) {
        def result = [:]
        items.each {symbolId ->
            result.put(symbolId, adjustedPriceSeries9Service.lastClosingPrice(symbolId as Long, startDate, adjustmentType))
        }
        result
    }
}
