package stocks

import grails.converters.JSON
import stocks.tse.IndustryGroup
import stocks.tse.IndustrySubgroup
import stocks.tse.Symbol
import stocks.tse.SymbolDailyTrade

class ReportController {

    def priceService
    def lowLevelDataService

    def heatMap() {
        [
                industryGroups: SymbolDailyTrade.createCriteria().list {
                    gte('date', new Date().clearTime())
                    projections {
                        symbol {
                            industryGroup {
                                distinct('id')
                                property('name')
                            }
                        }
                    }
                }.collect { [text: it[1], value: it[0]] }.sort { it.text }
        ]
    }

    def heatMapForImage() {
        [
                industryGroups: SymbolDailyTrade.createCriteria().list {
                    gte('date', new Date().clearTime())
                    projections {
                        symbol {
                            industryGroup {
                                distinct('id')
                                property('name')
                            }
                        }
                    }
                }.collect { [text: it[1], value: it[0]] }.sort { it.text }
        ]
    }

    def heatMapJson() {

        def result = lowLevelDataService.executeFunction('sym_sel_heat_map', [:])
        result = result.findAll{it.totalTradeValue * 100 / result.sum{it.totalTradeValue} > (params.thumbnail ? 0.2 : 0.1)}.groupBy {
            "${it.industryGroupId},${it.industryGroup}"
        }.collect {
            def keyItems = it.key.split(',')
            [
                    id                : keyItems.first(),
                    name              : keyItems.last(),
                    priceChangeOnSize : (it.value.sum { Math.round(it.priceChange * 10000 / (it.closingPrice - it.priceChange)) / 100F * it.totalTradeValue } as Double) / it.value.sum {
                        it.totalTradeValue
                    },
                    priceChangeOnCount: (it.value.sum { Math.round(it.priceChange * 10000 / (it.closingPrice - it.priceChange)) / 100F * it.totalTradeVolume } as Double) / it.value.sum {
                        it.totalTradeVolume
                    },
                    children          : it.value.collect {
                        [
                                id              : it.symbolId,
                                name            : it.symbolCode,
                                size            : it.totalTradeValue,
                                count           : it.totalTradeVolume,
                                fullName        : it.symbolName,
                                price           : Math.round(it.closingPrice),
                                priceChange     : Math.round(it.priceChange * 10000 / (it.closingPrice - it.priceChange)) / 100F,
                                priceChangeValue: it.priceChange
                        ]
                    }
            ]
        }
        result = [
                id      : 0L,
                name    : message(code: 'report.heatmap.industryGroup.all'),
                children: result
        ]
        render(result as JSON)
    }

    def heatMapJsonKendo() {

        def result = lowLevelDataService.executeFunction('sym_sel_heat_map', [:])
        result = result.groupBy {
            it.industryGroup
        }.collect {
            [
                    name : it.key,
                    value: it.value.sum {
                        it.totalTradeValue
                    } ?: 0,
                    items: it.value.collect {
                        [
                                name       : it.symbolCode,
                                value      : it.totalTradeValue,
                                fullName   : it.symbolName,
                                price      : it.closingPrice,
                                priceCHange: it.priceChange
                        ]
                    }
            ]
        }
        result = [
                [
                        name : 'all',
                        value: result.sum { it.value },
                        items: result
                ]
        ]
        render(result as JSON)
    }
}
