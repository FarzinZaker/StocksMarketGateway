package stocks.twitter

import grails.converters.JSON
import org.apache.lucene.search.BooleanQuery
import stocks.tse.Symbol
import stocks.RateHelper
import stocks.rate.Coin
import stocks.tse.Index
import stocks.rate.Currency
import stocks.rate.Metal
import stocks.rate.Oil
import stocks.rate.CoinFuture

class TwitterController {

    def propertyAutoComplete() {

        def queryStr = params."filter[filters][0][value]"?.toString() ?: ''
        def result = searchItems(params.group as String, queryStr)
        if (params.role == "target" && params.group != 'stocks.tools.correlation.CompanyCorrelationService')
            result = [[value: 'all', text: message(code: "${serviceClass.propertyName}.all")]] + result
        render([data: result] as JSON)
    }

    def searchItems(String group, String queryStr) {
        switch (group) {
            case 'Symbol':
                searchSymbolItems(queryStr)
                break
            case 'Index':
                searchIndexItems(queryStr)
                break
            case 'Coin':
                searchCoinItems(queryStr)
                break
            case 'Currency':
                searchCurrencyItems(queryStr)
                break
            case 'Metal':
                searchMetalItems(queryStr)
                break
            case 'Oil':
                searchOilItems(queryStr)
                break
            case 'Future':
                searchFutureItems(queryStr)
                break
            default:
                []
        }
    }

    def searchSymbolItems(String queryStr) {
        BooleanQuery.setMaxClauseCount(1000000)
        Symbol.search("*${queryStr}* AND ((marketCode:MCNO AND (type:300 OR type:303 OR type:309) AND -boardCode:4) OR status:I)", max: 20).results.unique { a, b -> a?.id <=> b?.id }.collect {
            [
                    text : "${it.persianCode} - ${it.persianName}",
                    value: it.id
            ]
        }
    }

    def searchIndexItems(String queryStr) {
        BooleanQuery.setMaxClauseCount(1000000)
        Index.search("*${queryStr}*", max: 10000).results.collect {
            [
                    text : it.persianName,
                    value: it.id
            ]
        }
    }

    def searchCoinItems(String queryStr) {
        RateHelper.COINS.findAll {
            it.value.name.toLowerCase().contains(queryStr.toLowerCase())
        }.collect {
            def symbol = it.key
            [
                    text : it.value.name,
                    value: Coin.createCriteria().list {
                        eq('symbol', symbol)
                        projections {
                            property('id')
                        }
                    }?.find()
            ]
        }
    }

    def searchCurrencyItems(String queryStr) {
        RateHelper.CURRENCIES.findAll {
            it.value.name.toLowerCase().contains(queryStr.toLowerCase())
        }.collect {
            def symbol = it.key
            [
                    text : it.value.name,
                    value: Currency.createCriteria().list {
                        eq('symbol', symbol)
                        projections {
                            property('id')
                        }
                    }?.find()
            ]
        }
    }

    def searchMetalItems(String queryStr) {
        RateHelper.METALS.findAll {
            it.value.name.toLowerCase().contains(queryStr.toLowerCase())
        }.collect {
            def symbol = it.key
            [
                    text : it.value.name,
                    value: Metal.createCriteria().list {
                        eq('symbol', symbol)
                        projections {
                            property('id')
                        }
                    }?.find()
            ]
        }
    }

    def searchOilItems(String queryStr) {
        RateHelper.OILS.findAll {
            it.value.name.toLowerCase().contains(queryStr.toLowerCase())
        }.collect {
            def symbol = it.key
            [
                    text : it.value.name,
                    value: Oil.createCriteria().list {
                        eq('symbol', symbol)
                        projections {
                            property('id')
                        }
                    }?.find()
            ]
        }
    }

    def searchFutureItems(String queryStr) {
        BooleanQuery.setMaxClauseCount(1000000)
        CoinFuture.search("*${queryStr}*", max: 10000).results.sort {
            it.lastTradingDate
        }.collect {
            [
                    text : it.contractDescription,
                    value: it.id
            ]
        }
    }
}
