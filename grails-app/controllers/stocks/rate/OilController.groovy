package stocks.rate

import grails.converters.JSON
import org.apache.lucene.search.BooleanQuery

class OilController {

    def oilSeries9Service

    def hashTagSearch() {
        def queryStr = params.term?.toString()?.trim() ?: ''
        BooleanQuery.setMaxClauseCount(1000000)

        def searchResult = Oil.search("*${queryStr?.replace('_', '* AND *')}*", max: params.max ? params.max.toString().toInteger() : 50)
        def result = []

        def maxScore = searchResult.scores.max()

        searchResult.results?.findAll { it?.toString() }?.eachWithIndex { item, index ->
            result << [
                    text     : item.toString(),
                    tag      : item.toString()?.replace(' ', '_'),
                    link     : createLink(controller: 'twitter', action: 'property', id: item.id, absolute: true),
                    score    : searchResult.scores[index] / maxScore,
                    type     : "${message(code: 'globalSearch.oil')}",
                    id       : item.id,
                    typeClass: 'Oil'
            ]
        }
        render(result as JSON)
    }

    def sparkLine(){
        def list = oilSeries9Service.sparkLine(params.symbol as String, 30)
        render ([id: params.symbol, value: list, min: list.min(), max:list.max()] as JSON)
    }
}
