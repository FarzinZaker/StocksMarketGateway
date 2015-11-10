package stocks.rate

import grails.converters.JSON
import org.apache.lucene.search.BooleanQuery

class MetalController {

    def hashTagSearch() {
        def queryStr = params.term?.toString()?.trim() ?: ''
        BooleanQuery.setMaxClauseCount(1000000)

        def searchResult = Metal.search("*${queryStr?.replace('_', '* AND *')}*", max: 50)
        def result = []

        def maxScore = searchResult.scores.max()

        searchResult.results.eachWithIndex { item, index ->
            result << [
                    text : item.toString(),
                    tag  : item.toString()?.replace(' ', '_'),
                    link : createLink(controller: 'twitter', action: 'property', id: item.id),
                    score: searchResult.scores[index] / maxScore,
                    type : "${message(code: 'globalSearch.metal')}"
            ]
        }
        render(result as JSON)
    }
}
