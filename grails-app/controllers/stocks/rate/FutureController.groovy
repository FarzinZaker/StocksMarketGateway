package stocks.rate

import grails.converters.JSON
import org.apache.lucene.search.BooleanQuery

class FutureController {

    def hashTagSearch() {
        def queryStr = params.term?.toString()?.trim() ?: ''
        BooleanQuery.setMaxClauseCount(1000000)

        def searchResult = CoinFuture.search("*${queryStr?.replace('_', '* AND *')}*", max: params.max ? params.max.toString().toInteger() : 50)
        def result = []

        def maxScore = searchResult.scores.max()

        searchResult.results?.findAll { it?.toString() }?.eachWithIndex { item, index ->
            result << [
                    text     : item.toString(),
                    tag      : item.toString()?.replace(' ', '_'),
                    link     : createLink(controller: 'twitter', action: 'property', id: item.id, absolute: true),
                    score    : searchResult.scores[index] / maxScore,
                    type     : "${message(code: 'globalSearch.future')}",
                    id       : item.id,
                    typeClass: 'Future'
            ]
        }
        render(result as JSON)
    }
}
