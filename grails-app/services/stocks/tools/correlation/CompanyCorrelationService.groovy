package stocks.tools.correlation

import org.apache.lucene.search.BooleanQuery
import stocks.tools.CorrelationServiceBase
import stocks.tse.Company
import stocks.tse.Symbol
import stocks.tse.SymbolDailyTrade

class CompanyCorrelationService extends CorrelationServiceBase {

    @Override
    List searchItems(String term) {

        BooleanQuery.setMaxClauseCount(1000000)
        Symbol.search("*${term}*", max: 10000).results.findAll { Symbol item ->
            !(0..9).contains(item.persianCode.charAt(item.persianCode.size() - 1)) &&
                    (item.persianCode.charAt(0) != 'ج' || item.persianCode.charAt(1) != ' ') &&
                    (item.persianName.charAt(0) != 'ح' || (item.persianName.charAt(1) != ' ' && item.persianName.charAt(1) != '.')) &&
                    ['300', '400', '309', '404'].contains(item.type) &&
                    item.marketCode == 'NO'
        }.collect { it.company }.findAll {
            it
        }.unique { a, b -> a?.id <=> b?.id }.collect {
            [
                    text : it.name,
                    value: it.id
            ]
        }
    }

    @Override
    String getItemName(String item) {
        Company.get(item as Long).name
    }

    @Override
    def all() {
        Company.createCriteria().list {
            projections {
                property('id')
            }
        }
    }

    @Override
    Map<String, List> getItemValuesCache(List<String> items, Date startDate, Date endDate, String period) {
        List<Symbol> symList = getCompaniesSymbols(Company.findAllByIdInList(items.collect { it as Long }))
        def itemList = SymbolDailyTrade.createCriteria().list {
            'in'('symbol', symList)
            isNotNull("${period}Snapshot")
            gte("${period}Snapshot", startDate)
            lte("${period}Snapshot", endDate)
            projections {
                symbol {
                    company {
                        property('id')
                    }
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
        Symbol symbol = getCompanySymbol(Company.get(item as Long))
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
        Symbol symbol = getCompanySymbol(Company.get(item as Long))
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
        def companies = Company.findAllByIdInList(items.collect { it as Long })
        List<Symbol> symList = getCompaniesSymbols(companies)
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
            def companyId = (item[0] as Symbol).companyId?.toString()
            def value = dailyTrades.find { it.id == (item[1] as Double) }?.closingPrice ?: 0
            if (!result.containsKey(companyId))
                result.put(companyId, value)
        }

        companies.each {
            if (!result.keySet().contains(it.id.toString()))
                result.put(it.id.toString(), 0)
        }
        result
    }

    Symbol getCompanySymbol(Company company) {
        Symbol.findAllByCompany(company).find { Symbol item ->
            !(0..9).contains(item.persianCode.charAt(item.persianCode.size() - 1)) &&
                    (item.persianCode.charAt(0) != 'ج' || item.persianCode.charAt(1) != ' ') &&
                    (item.persianName.charAt(0) != 'ح' || (item.persianName.charAt(1) != ' ' && item.persianName.charAt(1) != '.')) &&
                    ['300', '400', '309', '404'].contains(item.type) &&
                    item.marketCode == 'NO'
        }
    }

    List<Symbol> getCompaniesSymbols(List<Company> list) {
        Symbol.findAllByCompanyInList(list).findAll { Symbol item ->
            !(0..9).contains(item.persianCode.charAt(item.persianCode.size() - 1)) &&
                    (item.persianCode.charAt(0) != 'ج' || item.persianCode.charAt(1) != ' ') &&
                    (item.persianName.charAt(0) != 'ح' || (item.persianName.charAt(1) != ' ' && item.persianName.charAt(1) != '.')) &&
                    ['300', '400', '309', '404'].contains(item.type) &&
                    item.marketCode == 'NO'
        }
    }
}
