package stocks

import com.sun.script.javascript.RhinoScriptEngineFactory
import grails.converters.JSON
import org.codehaus.groovy.grails.io.support.GrailsResourceUtils
import org.ocpsoft.prettytime.PrettyTime
import org.watij.webspec.dsl.WebSpec
import stocks.commodity.CommodityMarketActivity
import stocks.commodity.CommodityMarketHelper
import stocks.rate.CoinFuture
import stocks.tse.Index
import stocks.tse.IndexHistory
import stocks.tse.MarketActivity
import stocks.tse.MarketValue
import stocks.tse.SymbolClientType
import sun.misc.GC
import stocks.tse.EnergyMarketValue

class DashboardController {

    def feedService
    def offlineChartService

    def index() {
    }

    def marketView() {
        def totalIndex = Index.findByInternalCode(32097828799138957)
        def priceIndex = Index.findByInternalCode(8384385859414435)
        def otcTotalIndex = Index.findByInternalCode(43685683301327984)
        def marketValue = MarketValue.createCriteria().list {
            eq('marketIdentifier', 1)
            lte('date', new Date())
            order('date', ORDER_DESCENDING)
        }?.find()
        def otcMarketValue = MarketValue.createCriteria().list {
            eq('marketIdentifier', 2)
            lte('date', new Date())
            order('date', ORDER_DESCENDING)
        }?.find()
        def clientTypes = SymbolClientType.createCriteria().list {
            gte('date', new Date().clearTime())
            projections {
                sum('individualBuyVolume')
                sum('individualSellVolume')
                sum('legalBuyVolume')
                sum('legalSellVolume')
            }
        }?.first()
        def lastCommodityDate = CommodityMarketActivity.createCriteria().list {
            projections {
                max('date')
            }
        }?.find()
        def powerMarketValue = EnergyMarketValue.createCriteria().list {
            eq('marketIdentifier', 1)
            lte('date', new Date())
            order('date', ORDER_DESCENDING)
        }?.find()
        def physicalMarketValue = EnergyMarketValue.createCriteria().list {
            eq('marketIdentifier', 2)
            lte('date', new Date())
            order('date', ORDER_DESCENDING)
        }?.find()
        def commodityMarketActivities = CommodityMarketActivity.findAllByDate(lastCommodityDate)
        def futureContracts = CoinFuture.findAllByLastTradingDateGreaterThanEquals(new Date())
        render([
                bourse   : [
                        totalIndex   : [
                                value        : totalIndex.finalIndexValue,
                                change       : totalIndex.finalIndexValue - (100 - totalIndex.todayIndexChangePercentTowardYesterday) * totalIndex.finalIndexValue / 100,
                                changePercent: totalIndex.todayIndexChangePercentTowardYesterday
                        ],
                        priceIndex   : [
                                value        : priceIndex.finalIndexValue,
                                change       : priceIndex.finalIndexValue - (100 - priceIndex.todayIndexChangePercentTowardYesterday) * priceIndex.finalIndexValue / 100,
                                changePercent: priceIndex.todayIndexChangePercentTowardYesterday
                        ],
                        otcTotalIndex: [
                                value: otcTotalIndex.finalIndexValue
                        ],
                        clientTypes  : [
                                totalIndividualBuyVolume : clientTypes[0],
                                totalIndividualSellVolume: clientTypes[1],
                                totalLegalBuyVolume      : clientTypes[2],
                                totalLegalSellVolume     : clientTypes[3],
                        ],
                        market       : [
                                value        : marketValue.value,
                                change       : marketValue.valueChange,
                                changePercent: marketValue.valueChange / ((marketValue.value - marketValue.valueChange) ?: 1),
                                tradeValue   : marketValue.tradeValue
                        ],
                        otcMarket    : [
                                value        : otcMarketValue.value,
                                change       : otcMarketValue.valueChange,
                                changePercent: otcMarketValue.valueChange / ((otcMarketValue.value - otcMarketValue.valueChange) ?: 1),
                                tradeValue   : otcMarketValue.tradeValue
                        ]
                ],
                commodity: [
                        total      : commodityMarketActivities.findAll { it.marketIdentifier == 0 }?.collect {
                            [
                                    value : it.value,
                                    count : it.tradeCount,
                                    volume: it.volume
                            ]
                        }?.find() ?: [value: 0, count: 0, volume: 0],
                        secondary  : commodityMarketActivities.findAll { it.marketIdentifier == 1 }?.collect {
                            [
                                    value : it.value,
                                    count : it.tradeCount,
                                    volume: it.volume
                            ]
                        }?.find() ?: [value: 0, count: 0, volume: 0],
                        petro      : commodityMarketActivities.findAll { it.marketIdentifier == 2 }?.collect {
                            [
                                    value : it.value,
                                    count : it.tradeCount,
                                    volume: it.volume
                            ]
                        }?.find() ?: [value: 0, count: 0, volume: 0],
                        industry   : commodityMarketActivities.findAll { it.marketIdentifier == 3 }?.collect {
                            [
                                    value : it.value,
                                    count : it.tradeCount,
                                    volume: it.volume
                            ]
                        }?.find() ?: [value: 0, count: 0, volume: 0],
                        agriculture: commodityMarketActivities.findAll { it.marketIdentifier == 4 }?.collect {
                            [
                                    value : it.value,
                                    count : it.tradeCount,
                                    volume: it.volume
                            ]
                        }?.find() ?: [value: 0, count: 0, volume: 0]
                ],
                future   : [
                        tradeVolume  : futureContracts.sum { CoinFuture contract -> contract.tradesVolume },
                        tradeValue   : futureContracts.sum { CoinFuture contract -> contract.tradesValue },
                        openInterests: futureContracts.sum { CoinFuture contract -> contract.openInterests },
                        tradeCount   : futureContracts.sum { CoinFuture contract -> contract.tradesCount }
                ],
                energy   : [
                        power   : [
                                tradeValue : powerMarketValue.tradeValue,
                                tradeVolume: powerMarketValue.tradeVolume,
                                tradeCount : powerMarketValue.tradeCount
                        ],
                        physical: [
                                tradeValue: physicalMarketValue.tradeValue,
                                tradeCount: physicalMarketValue.tradeCount
                        ]
                ]
        ] as JSON)
    }

    def news() {
        def result = feedService.news()
        render([
                data      : result.data.collect {
                    [
                            id        : it.id,
                            title     : it.title,
                            time      : it.date.time,
                            dateString: new PrettyTime(new Locale('fa')).format(it.date),
                            link      : it.link,
                            category  : it.category,
                            source    : message(code: "newsSource.${it.source}")
                    ]
                },
                categories: result.categoryList.collect {
                    [
                            value: it,
                            text : message(code: "newsCategory.${it}")
                    ]
                }
        ] as JSON)
    }
}
