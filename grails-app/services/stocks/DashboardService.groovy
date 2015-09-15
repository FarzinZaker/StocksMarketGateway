package stocks

import groovy.time.TimeCategory
import org.ocpsoft.prettytime.PrettyTime
import org.springframework.cache.annotation.Cacheable
import stocks.codal.Announcement
import stocks.commodity.CommodityMarketActivity
import stocks.rate.Coin
import stocks.rate.CoinFuture
import stocks.rate.Currency
import stocks.rate.Metal
import stocks.rate.Oil
import stocks.tse.EnergyMarketValue
import stocks.tse.Index
import stocks.tse.MarketValue
import stocks.tse.SupervisorMessage
import stocks.tse.SymbolClientType

class DashboardService {

    def messageSource

//    @Cacheable('marketViewCache')
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
        if (!clientTypes.any { it != null }) {
            def date = new Date()
            use(TimeCategory) {
                date = date - 1.day
            }
            clientTypes = SymbolClientType.createCriteria().list {
                gte('date', date.clearTime())
                projections {
                    sum('individualBuyVolume')
                    sum('individualSellVolume')
                    sum('legalBuyVolume')
                    sum('legalSellVolume')
                }
            }?.first()
        }
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
        [
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
                                totalIndividualBuyVolume : clientTypes[0] ?: 0,
                                totalIndividualSellVolume: clientTypes[1] ?: 0,
                                totalLegalBuyVolume      : clientTypes[2] ?: 0,
                                totalLegalSellVolume     : clientTypes[3] ?: 0,
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
        ]
    }

    @Cacheable('announcementsCache')
    def announcements() {
        [
                codal             : Announcement.createCriteria().list {
                    order('publishDate', ORDER_DESCENDING)
                    maxResults(10)
                }.collect {
                    def date = it.publishDate
                    use(TimeCategory) {
                        date = date - 1.minute
                    }
                    [
                            id        : "announcement${it.id}",
                            title     : it.title,
                            time      : it.publishDate.time,
                            dateString: new PrettyTime(new Locale('fa')).format(date),
                            source    : it.symbol ? "${it.symbol?.persianName} (${it.symbol?.persianCode})" : '',
                            link      : it.detailsUrl
                    ]
                },
                supervisorMessages: SupervisorMessage.createCriteria().list {
                    order('date', ORDER_DESCENDING)
                    maxResults(10)
                }.collect {
                    [
                            id        : "announcement${it.id}",
                            title     : it.title,
                            time      : it.date.time,
                            dateString: new PrettyTime(new Locale('fa')).format(it.date)
                    ]
                }
        ]
    }

    @Cacheable('ratesCache')
    def rates() {
        def currency = [:]
        ['us-dollar', 'euro', 'gbp', 'aed', 'lear-turkey'].each {
            currency.put(it.replace('-', '_'), [price: Currency.findBySymbol(it)?.price, unit: messageSource.getMessage('rial', null, 'ریال', Locale.ENGLISH)])
        }
        def gold = [:]
        ['ons', 'n-coin', 'o-coin', 'h-coin', 'q-coin', 'geram18'].each {
            gold.put(it.replace('-', '_'), [price: Coin.findBySymbol(it)?.price, unit: ['ons'].contains(it) ? messageSource.getMessage('dollar', null, 'دلار', Locale.ENGLISH) : messageSource.getMessage('rial', null, 'ریال', Locale.ENGLISH)])
        }
        def metal = [:]
        ['copper', 'aluminium', 'nickel', 'tin', 'zinc'].each {
            metal.put(it.replace('-', '_'), [price: Metal.findBySymbol(it)?.price, unit: messageSource.getMessage('dollar', null, 'دلار', Locale.ENGLISH)])
        }
        def oil = [:]
        ['WTI-Crude-Oil-Nymex', 'Brent-Crude-ICE', 'Crude-Oil-Tokyo', 'Natural-Gas-Nymex'].each {
            def item = Oil.findBySymbol(it)
            oil.put(it.replace('-', '_'), [price: item?.price, unit: item.unit])
        }

        [
                currency: currency,
                gold    : gold,
                metal   : metal,
                oil     : oil
        ]
    }
}
