package stocks.tools.correlation

import org.apache.lucene.search.BooleanQuery
import stocks.tools.CorrelationServiceBase
import stocks.tse.Company
import stocks.tse.Symbol
import stocks.tse.SymbolDailyTrade

class CompanyCorrelationService extends CorrelationServiceBase {

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
        List<Symbol> symList = Symbol.findAllByIdInList((items?.size() > 1000 ? items[0..999] : items).collect {
            it as Long
        })
        def itemList = SymbolDailyTrade.createCriteria().list {
            'in'('symbol', symList)
            isNotNull("${period}Snapshot")
            gte("${period}Snapshot", startDate)
            lte("${period}Snapshot", endDate)
            projections {
                symbol {
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
        Symbol symbol = Symbol.get(item as Long)
        SymbolDailyTrade.createCriteria().list {
            eq('symbol', symbol)
            isNotNull("${period}Snapshot")
            gte("${period}Snapshot", startDate)
            lte("${period}Snapshot", endDate)
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
        Symbol symbol = Symbol.get(item as Long)
        SymbolDailyTrade.get(SymbolDailyTrade.createCriteria().get {
            eq('symbol', symbol)
            lt('creationDate', startDate)
            projections {
                max('id')
            }
        })?.closingPrice ?: 0
    }

    @Override
    Map<String, Double> getBaseValueCache(List<String> items, Date startDate) {
        List<Symbol> symList = Symbol.findAllByIdInList((items?.size() > 1000 ? items[0..999] : items).collect {
            it as Long
        })
        def list = SymbolDailyTrade.createCriteria().list {
            'in'('symbol', symList)
            lt('creationDate', startDate)
            projections {
                groupProperty('symbol')
                max('id')
            }
        }
        def dailyTrades = SymbolDailyTrade.findAllByIdInList(list.collect { it[1] })
        def result = [:]
        list.each { item ->
            def symbolId = (item[0] as Symbol).id?.toString()
            def value = dailyTrades.find { it.id == (item[1] as Double) }?.closingPrice ?: 0
            if (!result.containsKey(symbolId))
                result.put(symbolId, value)
        }
        result
    }
}
