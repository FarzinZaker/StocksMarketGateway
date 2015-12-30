package stocks.rate

import grails.converters.JSON
import org.apache.lucene.search.BooleanQuery

class CoinController {

    def coinSeries9Service

    def hashTagSearch() {
        def queryStr = params.term?.toString()?.trim() ?: ''
        BooleanQuery.setMaxClauseCount(1000000)

        def searchResult = Coin.search("*${queryStr?.replace('_', '* AND *')}*", max: 50)
        def result = []

        def maxScore = searchResult.scores.max()

        searchResult.results.eachWithIndex { item, index ->
            result << [
                    text     : item.toString(),
                    tag      : item.toString()?.replace(' ', '_'),
                    link     : createLink(controller: 'twitter', action: 'property', id: item.id),
                    score    : searchResult.scores[index] / maxScore,
                    type     : "${message(code: 'globalSearch.coin')}",
                    id       : item.id,
                    typeClass: 'Coin'
            ]
        }
        render(result as JSON)
    }

    def sparkLine(){
        def list = coinSeries9Service.sparkLine(params.symbol as String, 30)
        render ([id: params.id, value: list, min: list.min(), max:list.max()] as JSON)
    }
}
