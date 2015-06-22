package stocks

import grails.converters.JSON
import stocks.tse.Symbol

class ChartController {

    def adjustedPriceSeriesService

    def config() {
        render([
                supports_search       : true,
                supports_group_request: false,
                supports_marks        : true,
                exchanges             : [
                        [value: "",
                         name : "All Exchanges",
                         desc : ""
                        ],
                        [
                                value: "XETRA",
                                name : "XETRA",
                                desc : "XETRA"
                        ],
                        [
                                value: "NSE",
                                name : "NSE",
                                desc : "NSE"
                        ], [
                                value: "NasdaqNM",
                                name : "NasdaqNM",
                                desc : "NasdaqNM"
                        ],
                        [
                                value: "NYSE",
                                name : "NYSE",
                                desc : "NYSE"
                        ],
                        [
                                value: "CDNX",
                                name : "CDNX", desc: "CDNX"
                        ],
                        [
                                value: "Stuttgart",
                                name : "Stuttgart",
                                desc : "Stuttgart"
                        ]
                ],
                symbolsTypes          : [
                        [
                                name : "All types",
                                value: ""
                        ], [
                                name : "Stock",
                                value: "stock"
                        ],
                        [
                                name : "Index",
                                value: "index"
                        ]
                ],
                supportedResolutions  : ["D", "2D", "3D", "W", "3W", "M", "6M"]
        ] as JSON)
    }

    def symbols() {
        def symbol = Symbol.findByPersianCode(params.symbol?.toString())
        render([
                name                 : symbol.persianCode,
                "exchange-traded"    : message(code: "market.${symbol.marketIdentifier}"),
                "exchange-listed"    : message(code: "market.${symbol.marketIdentifier}"),
                timezone             : "America/New_York",
                minmov               : 1,
                minmov2              : 0,
                pricescale           : 10,
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

        def trades = adjustedPriceSeriesService.dailyTradeList(Symbol.findByPersianCode(params.symbol?.toString()).id, new Date((params.from as Long) * 1000), new Date((params.to as Long) * 1000), groupingMode)

        render([
                t: trades.collect { it.date.time },
                c: trades.collect { it.closingPrice },
                o: trades.collect { it.firstTradePrice },
                h: trades.collect { it.maxPrice },
                l: trades.collect { it.lastTradePrice },
                v: trades.collect { it.totalTradeVolume },
                s: 'ok'
        ] as JSON)
    }
}
