package stocks.tse

import grails.converters.JSON
import org.apache.lucene.search.BooleanQuery

class IndexController {

    def priceService
    def indexSeries9Service
    def propertyGraphService

    def info() {
        def index = Index.get(params.id as Long)
        propertyGraphService.ensureProperty('Index', index?.id, index?.persianName)
        redirect(controller: 'twitter', action: 'property', id: index?.id)
    }

    def clearTimeSeries() {
        indexSeries9Service.clear()
        render 'done'
    }

    def loadTimeSeries() {
        indexSeries9Service.write(IndexHistory.createCriteria().list {
            index {
                eq('id', params.id as Long)
            }
            order('date', ORDER_ASCENDING)
        })
        render 'done'
    }

    def search() {
        def queryStr = params."filter[filters][0][value]"?.toString() ?: ''
        BooleanQuery.setMaxClauseCount(1000000)

        def result = Index.search("*${queryStr}*", max: 20).results.unique { a, b -> a?.id <=> b?.id }.collect {
            [
                    name : it.persianName,
                    value: it.id
            ]
        }
        render([data: result] as JSON)
    }

    def globalSearch() {
        def queryStr = params.term?.toString()?.trim() ?: ''
        BooleanQuery.setMaxClauseCount(1000000)

        def searchResult = Index.search("*${queryStr}*", max: 50)
        def result = []

        def maxScore = searchResult.scores.max()

        searchResult.results.eachWithIndex { item, index ->
            result << [
                    text : item.toString(),
                    link : createLink(controller: 'index', action: 'info', id: item.id),
                    score: searchResult.scores[index] / maxScore,
                    type : "${message(code: 'globalSearch.index')}"
            ]
        }
        render(result as JSON)
    }

    def hashTagSearch() {
        def queryStr = params.term?.toString()?.trim() ?: ''
        BooleanQuery.setMaxClauseCount(1000000)

        def searchResult = Index.search("*${queryStr?.replace('_', '* AND *')}*", max: params.max ? params.max.toString().toInteger() : 50)
        def result = []

        def maxScore = searchResult.scores.max()

        searchResult.results?.findAll { it?.toString() }?.eachWithIndex { item, index ->
            result << [
                    text : item.toString(),
                    tag: item.toString()?.replace(' ', '_'),
                    link : createLink(controller: 'twitter', action: 'property', id: item.id, absolute: true),
                    score: searchResult.scores[index] / maxScore,
                    type : "${message(code: 'globalSearch.index')}",
                    id       : item.id,
                    typeClass: 'Index'
            ]
        }
        render(result as JSON)
    }
}
