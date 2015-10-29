package stocks.chart

import grails.converters.JSON
import groovy.time.TimeCategory
import org.apache.lucene.search.BooleanQuery
import stocks.FarsiNormalizationFilter
import stocks.tse.Index
import stocks.tse.Symbol

class IndexChartController {

    def indexSeries9Service

    def config() {

        def markets = []
        markets << [value: "",
                    name : message(code: 'market.all'),
                    desc : ""
        ]
        5.times {
            markets << [
                    value: it - 1,
                    name : message(code: "market.${it}"),
                    desc : ''
            ]
        }

        render([
                supports_search       : true,
                supports_group_request: false,
                supports_marks        : false,
                exchanges             : markets,
                symbolsTypes          : [
                        [
                                name : message(code: 'all'),
                                value: ""
                        ], [
                                name : message(code: 'stock'),
                                value: "stock"
                        ],
                        [
                                name : message(code: 'index'),
                                value: "index"
                        ]
                ],
                supportedResolutions  : ["D", "2D", "3D", "W", "3W", "M", "6M"],
                header_symbol_search  : false
        ] as JSON)
    }

    def symbols() {
        def symbol = Index.findByPersianName(params.symbol?.toString())
        render([
                name                 : symbol.persianName,
                "exchange-traded"    : message(code: "market.${symbol.marketIdentifier}"),
                "exchange-listed"    : message(code: "market.${symbol.marketIdentifier}"),
                timezone             : "Asia/Tehran",
                minmov               : 1,
                minmov2              : 0,
                pricescale           : 1,
                pointvalue           : 1,
                session              : "0930-1630",
                has_intraday         : false,
                has_no_volume        : false,
                ticker               : symbol.persianName,
                description          : symbol.persianName,
                type                 : "stock",
                supported_resolutions: ["D", "2D", "3D", "W", "3W", "M", "6M"]] as JSON)
    }

    def history() {
        def groupingMode = '1d'
        switch (params.resolution) {
            case "2D":
                groupingMode = '2d'
                break
            case "3D":
                groupingMode = '3d'
                break
            case "W":
                groupingMode = '1w'
                break
            case "3W":
                groupingMode = '3w'
                break
            case "M":
                groupingMode = '30d'
                break
            case "6M":
                groupingMode = '180d'
                break

        }

        def trades = indexSeries9Service.indexHistoryList(Index.findByPersianName(params.symbol?.toString()).id, new Date((params.from as Long) * 1000), new Date((params.to as Long) * 1000), groupingMode)

        render(
                [
                        t: trades.collect {

                            def date = it.date
                            use(TimeCategory){
                                date = date + 2.days
                            }
                            date.time / 1000
                        },
                        c: trades.collect { it.finalIndexValue },
                        o: trades.collect { it.firstIndexValue },
                        h: trades.collect { it.highestIndexValue },
                        l: trades.collect { it.lowestIndexValue },
                        v: trades.collect { 0 },
                        s: 'ok'
                ] as JSON)
    }

    def search() {

        BooleanQuery.setMaxClauseCount(1000000)
        def limit = (params.limit ?: 0) as Integer
        def phrase = FarsiNormalizationFilter.apply((params.query ? params.query.toString().split(':').last() : '') as String)
        def market = (params.exchange && params.exchange != '' ? params.exchange?.toString()?.toInteger() : null)

        def symbols = []
        if (!params.type || params.type == '' || params.type == 'stock')
            symbols = Symbol.search("*${phrase}*", max: 20).results.unique { a, b -> a?.id <=> b?.id }.collect {
                [
                        symbol     : it.persianName,
                        full_name  : it.persianName,
                        description: it.persianName,
                        exchange   : it.marketIdentifier,
                        type       : 'stock'
                ]
            }

        def indexes = []
        if (!params.type || params.type == '' || params.type == 'index')
            indexes = Index.search("*${phrase}* ${market ? "AND marketIdentifier:${market}" : ''}").results.unique { a, b -> a?.id <=> b?.id }.collect {
                [
                        symbol     : it.persianName,
                        full_name  : it.persianName,
                        description: it.persianName,
                        exchange   : it.marketIdentifier,
                        type       : 'index'
                ]
            }

        render((symbols + indexes) as JSON)
    }
}
