package stocks.twitter

import grails.converters.JSON
import org.apache.lucene.search.BooleanQuery
import stocks.User
import stocks.tse.Symbol
import stocks.RateHelper
import stocks.rate.Coin
import stocks.tse.Index
import stocks.rate.Currency
import stocks.rate.Metal
import stocks.rate.Oil
import stocks.rate.CoinFuture
import stocks.twitter.Search.*

class TwitterController {

    def materialGraphService
    def propertyGraphService
    def springSecurityService
    def priceService
    def followGraphService
    def sharingService
    def groupGraphService

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

    def propertyList() {
        [
                propertyList: materialGraphService.getPropertyList(params.id as String)
        ]
    }

    def meta() {
        def meta = materialGraphService.getMeta(params.id as String)
        def groups = meta.findAll { it.label == 'Group' && it.ownerType == 'user' }
        def hasAccess = meta.any { it.label == 'Group' && it.ownerType != 'user' }
        if (!hasAccess) {
            def userGroups = groupGraphService.listForMember(springSecurityService.currentUser as User)
            hasAccess = userGroups.any { userGroup -> groups.any { group -> group.idNumber == userGroup.idNumber } }
        }
        [
                author   : meta.find { it.label == 'Person' },
                groups   : groups,
                hasAccess: hasAccess
        ]
    }

    def property() {
        def propertyVertex = params.id ? propertyGraphService.getAndUnwrapByIdentifier(params.id as Long) : null
        def user = springSecurityService.currentUser as User
        def propertyInfo = null
        switch (propertyVertex.label) {
            case 'Symbol':
                propertyInfo = priceService.lastDailyTrade(Symbol.get(params.id as Long))
                break
            case 'Index':
                propertyInfo = Index.get(params.id as Long)
                break
            case 'Coin':
                propertyInfo = Coin.get(params.id as Long)
                break
            case 'Currency':
                propertyInfo = Currency.get(params.id as Long)
                break
            case 'Metal':
                propertyInfo = Metal.get(params.id as Long)
                break
            case 'Future':
                propertyInfo = CoinFuture.get(params.id as Long)
                break
            case 'Oil':
                propertyInfo = Oil.get(params.id as Long)
                break
        }
        [
                property    : propertyVertex,
                authorList  : propertyGraphService.authorList(propertyVertex.idNumber as String),
                propertyInfo: propertyInfo
        ]
    }

    def propertyJson() {
        def list = materialGraphService.listByProperty(params.id as String, params.skip as Integer, params.limit as Integer)
        render(list.collect {
            g.render(template: "/twitter/material/${it.label}", model: [material: it, showProperties: true])
        } as JSON)
    }

    def follow() {
        followGraphService.follow(springSecurityService.currentUser as User, params.id as String)
        render twitter.followButton(itemId: params.id)
    }

    def unfollow() {
        followGraphService.unfollow((springSecurityService.currentUser as User)?.id, params.id as String)
        render twitter.followButton(itemId: params.id)
    }

    def suggest() {
        def skip = params.skip?.toInteger() ?: 0
        def limit = params.limit?.toInteger() ?: 10
        render(sharingService.suggestFollowList(springSecurityService.currentUser?.id, skip, limit).collect {
            g.render(template: '/twitter/followable', model: [item: it]) as String
        } as JSON)
    }

    def globalAuthorSearch() {
        def queryStr = params.term?.toString()?.trim() ?: ''
        BooleanQuery.setMaxClauseCount(1000000)

        def searchResult = TwitterPerson.search("*${queryStr?.replace('_', '* AND *')}*", max: 50)
        def result = []

        def maxScore = searchResult.scores.max()

        searchResult.results.eachWithIndex { item, index ->
            result << [
                    text : item.title,
                    author  : item.title?.replace(' ', '_'),
                    link : createLink(controller: 'user', action: 'wall', id: item.identifier),
                    score: searchResult.scores[index] / maxScore,
                    type : message(code: 'globalSearch.author')
            ]
        }
        render(result.sort { -it.score } as JSON)
    }

    def globalPropertySearch() {
        def queryStr = params.term?.toString()?.trim() ?: ''
        BooleanQuery.setMaxClauseCount(1000000)

        def searchResult = TwitterProperty.search("${queryStr ?: '**'}", max: 50)
        def result = []

        def maxScore = searchResult.scores.max()

        searchResult.results.eachWithIndex { item, index ->
            result << [
                    text : "${item.title}",
                    link : createLink(controller: 'twitter', action: 'property', id: item.identifier),
                    score: searchResult.scores[index] / maxScore,
                    type : message(code: 'globalSearch.tag')
            ]
        }
        render(result.sort { -it.score } as JSON)
    }

    def globalMaterialSearch() {
        def queryStr = params.term?.toString()?.trim() ?: ''
        BooleanQuery.setMaxClauseCount(1000000)

        def searchResult = TwitterMaterial.search("${queryStr ?: '**'}", max: 50)
        def result = []

        def maxScore = searchResult.scores.max()

        searchResult.results.eachWithIndex { item, index ->
            result << [
                    text : "${item.title}",
                    link : createLink(controller: 'article', action: 'thread', id: item.identifier),
                    score: searchResult.scores[index] / maxScore,
                    type : message(code: 'globalSearch.material')
            ]
        }
        render(result.sort { -it.score } as JSON)
    }

    def globalGroupSearch() {
        def queryStr = params.term?.toString()?.trim() ?: ''
        BooleanQuery.setMaxClauseCount(1000000)

        def searchResult = TwitterGroup.search("*${queryStr?.replace('_', '* AND *')}*", max: 50)
        def result = []

        def maxScore = searchResult.scores.max()

        searchResult.results.eachWithIndex { item, index ->
            result << [
                    text : item.title,
                    author  : item.title?.replace(' ', '_'),
                    link : createLink(controller: 'group', action: 'home', id: item.rid?.replace('#', '')),
                    score: searchResult.scores[index] / maxScore,
                    type : message(code: 'globalSearch.group')
            ]
        }
        render(result.sort { -it.score } as JSON)
    }
}
