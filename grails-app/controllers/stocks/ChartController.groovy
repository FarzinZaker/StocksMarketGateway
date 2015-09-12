package stocks

import grails.converters.JSON
import grails.plugin.cache.CachePut
import grails.plugin.cache.Cacheable
import groovy.time.TimeCategory
import org.apache.lucene.search.BooleanQuery
import stocks.tse.Symbol
import stocks.tse.Index

class ChartController {

    def adjustedPriceSeries9Service

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
        def symbol = Symbol.findByPersianCode(params.symbol?.toString())
        render([
                name                 : symbol.persianCode,
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
                ticker               : symbol.persianCode,
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

        def trades = adjustedPriceSeries9Service.dailyTradeList(Symbol.findByPersianCode(params.symbol?.toString()).id, new Date((params.from as Long) * 1000), new Date((params.to as Long) * 1000), groupingMode, params.adjustmentType?.toString())

        render(
                [
                t: trades.collect {

                    def date = it.date
                    use(TimeCategory){
                        date = date + 2.days
                    }
                    date.time / 1000
                },
                c: trades.collect { it.lastTradePrice },
                o: trades.collect { it.firstTradePrice },
                h: trades.collect { it.maxPrice },
                l: trades.collect { it.minPrice },
                v: trades.collect { it.totalTradeVolume },
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
            symbols = Symbol.search("*${phrase}* ${market ? "AND marketIdentifier:${market}" : ''}", max: 20).results.unique { a, b -> a?.id <=> b?.id }.collect {
                [
                        symbol     : it.persianCode,
                        full_name  : it.persianCode,
                        description: '',
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
                        description: '',
                        exchange   : it.marketIdentifier,
                        type       : 'index'
                ]
            }

        render((symbols + indexes) as JSON)
    }
    def sparkLine(Long id){
        def list = adjustedPriceSeries9Service.sparkLIine(id, 30)
        render ([id: id, value: list] as JSON)
    }
}
