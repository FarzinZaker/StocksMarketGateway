package stocks

import fi.joensuu.joyds1.calendar.JalaliCalendar
import grails.converters.JSON
import groovy.time.TimeCategory
import org.codehaus.groovy.grails.web.json.JSONArray
import stocks.alerting.MessageHelper
import stocks.alerting.QueuedMessage
import stocks.alerting.SentMessage
import stocks.filters.Operators
import stocks.rate.CoinFuture
import stocks.rate.Currency
import stocks.rate.Coin
import stocks.rate.Oil
import stocks.rate.Metal
import stocks.tse.AdjustmentHelper
import stocks.tse.Index
import stocks.tse.MarketValue
import stocks.tse.Symbol
import stocks.tse.SymbolClientType
import stocks.commodity.CommodityMarketActivity
import stocks.tse.EnergyMarketValue
import stocks.rate.CoinFuture
import stocks.analysis.Screener
import stocks.alerting.Rule
import stocks.util.ClassResolver

class MobileController {

    def springSecurityService
    def marketStatusService
    def filterService

    def authenticate() {
        if (!params.username || !params.password) {
            render([
                    status: 'f',
                    id    : 0
            ] as JSON)
            return
        }

        def user = User.findByUsername(params.username?.toString()?.toLowerCase())
        if (!user || !user.enabled || user.accountExpired || user.passwordExpired) {
            render([
                    status: 'f',
                    id    : 0
            ] as JSON)
            return
        }

        if (user.password == springSecurityService.encodePassword(params.password?.toString())) {
            if (!user.useMobilePushNotification) {
                user.useMobilePushNotification = true
                user.save()
            }
            render([
                    status: 's',
                    id    : user.id?.toString()
            ] as JSON)
        } else {
            render([
                    status: 'f',
                    id    : 0
            ] as JSON)
        }
    }

    def messageDelivery() {
        if (!params.id || !params.user) {
            render([
                    status: 'f'
            ] as JSON)
            return
        }
        def message = QueuedMessage.get(params.id as Long)
        if (message && message.receiverId == (params.user as Long)) {

            message.status = MessageHelper.STATUS_SUCCEED
            message.save()
            render([
                    status: 's'
            ] as JSON)
        } else {
            render([
                    status: 'f'
            ] as JSON)
        }
    }

    def messageList() {

        println(params)

        if (!params.user) {
            render([
                    status: 'f',
                    list  : []
            ] as JSON)
            return
        }

        def user = params.user as Long
        def type = params.type as String
        def after = params.after as Long
        def before = params.before as Long

        def list = SentMessage.createCriteria().list {
            eq('receiverId', user)
            if (type)
                eq('type', type)
            if (after)
                gt('id', after)
            if (before) {
                lt('id', before)
                maxResults(10)
            }
            if (!before && !after) {
                maxResults(10)
            }
            if (after) {
                order('id', ORDER_ASCENDING)
            } else {
                order('id', ORDER_DESCENDING)
            }
            projections {
                property('id')
                property('type')
                property('title')
                property('dateCreated')
            }
        }.collect {
            [
                    id   : it[0],
                    type : it[1],
                    title: it[2],
                    date : it[3]
            ]
        }

        render([
                status: 's',
                list  : list
        ] as JSON)
    }

    def messageBody() {

        if (!params.user || !params.id) {
            render([
                    status: 'f',
                    body  : ''
            ] as JSON)
            return
        }
        def user = params.user as Long
        def id = params.id as Long

        def message = SentMessage.get(id)
        if (message.receiverId != user) {
            render([
                    status: 'f',
                    body  : ''
            ] as JSON)
            return
        }

        render([
                status: 's',
                body  : message?.body
        ] as JSON)
    }

    def rates() {

        if (!params.id) {
            render([
                    status: 'f',
                    body  : ''
            ] as JSON)
            return
        }

        def result = [:]
        switch (params.id) {
            case 'currency':
                ['us-dollar', 'euro', 'gbp', 'aed', 'lear-turkey'].each {
                    def item = Currency.findBySymbol(it)
                    result.put(it.replace('-', '_'), [price: item?.price, date: item?.modificationDate, unit: message(code: 'rial')])
                }
                break
            case 'gold':
                ['ons', 'n-coin', 'o-coin', 'h-coin', 'q-coin', 'geram18'].each {
                    def item = Coin.findBySymbol(it)
                    result.put(it.replace('-', '_'), [price: item?.price, date: item?.modificationDate, unit: ['ons'].contains(it) ? message(code: 'dollar') : message(code: 'rial')])
                }
                break
            case 'metal':
                ['copper', 'aluminium', 'nickel', 'tin', 'zinc'].each {
                    def item = Metal.findBySymbol(it)
                    result.put(it.replace('-', '_'), [price: item?.price, date: item?.modificationDate, unit: message(code: 'dollar')])
                }
                break
            case 'oil':
                ['WTI-Crude-Oil-Nymex', 'Brent-Crude-ICE', 'Crude-Oil-Tokyo', 'Natural-Gas-Nymex'].each {
                    def item = Oil.findBySymbol(it)
                    result.put(it.replace('-', '_'), [price: item?.price, date: item?.modificationDate, unit: item.unit])
                }
                break
        }

        def date = result.values().collect {
            it.date
        }.max() as Date
        result.values().each { Map item -> item.remove('date') }
        result.date = jalaliDate(date)

        render(result as JSON)
    }

    def marketView() {

        if (!params.id) {
            render([
                    status: 'f',
                    body  : ''
            ] as JSON)
            return
        }

        render("${params.id}MarketView"() as JSON)

    }

    def bourseMarketView() {

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
        [
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
                        value        : otcTotalIndex.finalIndexValue,
                        change       : otcTotalIndex.finalIndexValue - (100 - otcTotalIndex.todayIndexChangePercentTowardYesterday) * otcTotalIndex.finalIndexValue / 100,
                        changePercent: otcTotalIndex.todayIndexChangePercentTowardYesterday
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
                ],
                date         : jalaliDate(marketStatusService.correctMarketLastDataUpdateTime(marketStatusService.MARKET_STOCK, [totalIndex.modificationDate, priceIndex.modificationDate, otcTotalIndex.modificationDate].max()))

        ]
    }

    def commodityMarketView() {

        def lastCommodityDate = CommodityMarketActivity.createCriteria().list {
            projections {
                max('date')
            }
        }?.find()
        def commodityMarketActivities = CommodityMarketActivity.findAllByDate(lastCommodityDate)
        [
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
                }?.find() ?: [value: 0, count: 0, volume: 0],
                date       : jalaliDate(marketStatusService.correctMarketLastDataUpdateTime(MarketStatusService.MARKET_COMMODITY, commodityMarketActivities.collect {
                    it.modificationDate
                }.max()))
        ]
    }

    def futureMarketView() {

        def futureContracts = CoinFuture.findAllByLastTradingDateGreaterThanEquals(new Date())
        [
                tradeVolume  : futureContracts.sum { CoinFuture contract -> contract.tradesVolume },
                tradeValue   : futureContracts.sum { CoinFuture contract -> contract.tradesValue },
                openInterests: futureContracts.sum { CoinFuture contract -> contract.openInterests },
                tradeCount   : futureContracts.sum { CoinFuture contract -> contract.tradesCount },
                date         : jalaliDate(marketStatusService.correctMarketLastDataUpdateTime(marketStatusService.MARKET_FUTURE, futureContracts.collect {
                    it.modificationDate
                }.max()))
        ]
    }

    def energyMarketView() {

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
        [
                power   : [
                        tradeValue : powerMarketValue.tradeValue,
                        tradeVolume: powerMarketValue.tradeVolume,
                        tradeCount : powerMarketValue.tradeCount
                ],
                physical: [
                        tradeValue: physicalMarketValue.tradeValue,
                        tradeCount: physicalMarketValue.tradeCount
                ],
                date    : jalaliDate(marketStatusService.correctMarketLastDataUpdateTime(marketStatusService.MARKET_ENERGY, [
                        powerMarketValue.collect {
                            it.modificationDate
                        }.max(),
                        physicalMarketValue.collect {
                            it.modificationDate
                        }.max()
                ].max()))
        ]
    }

    def adjustmentList() {
        if (!params.user) {
            render([
                    status: 'f',
                    body  : ''
            ] as JSON)
            return
        }

        render (AdjustmentHelper.ENABLED_TYPES as JSON)
    }

    def screenerList() {
        if (!params.user) {
            render([
                    status: 'f',
                    body  : ''
            ] as JSON)
            return
        }
        def user = User.get(params.user as Long)

        render(Screener.findAllByOwnerAndDeleted(user, false).collect {
            [
                    title: it.title,
                    id   : it.id
            ]
        } as JSON)
    }

    def screenerMeta() {
        if (!params.user || !params.id) {
            render([
                    status: 'f',
                    body  : ''
            ] as JSON)
            return
        }
        def user = User.get(params.user as Long)
        def id = params.id as Long
        def screener = Screener.get(id)
        if (screener.ownerId != user?.id) {
            render([
                    status: 'f',
                    body  : ''
            ] as JSON)
            return
        }

        def rules = Rule.findAllByParent(screener?.rule)
        def indicatorColumns = [:]
        rules.each { rule ->
            def indicatorName = rule.field.replace('.filters.', '.indicators.').replace('FilterService', '')
            if (ClassResolver.serviceExists(indicatorName + "Service"))
                indicatorColumns.put("${indicatorName.replace('.', '_')}_${rule.inputType}".replace('stocks_indicators_symbol_', ''), "(${message(code: rule.field)} (${rule.inputType}")

            if (indicatorName.endsWith('.Volume')) {
                if (rule.operator.contains('average')) {
                    def daysCount = JSON.parse(rule.value).find().find { !it.contains('.') }
                    indicatorColumns.put("symVolAvg${daysCount}", message(code: 'volume.average.title', args: [daysCount]))
                }
            } else if (indicatorName.endsWith('MACD')) {
                if (!JSON.parse(rule.value).contains('constant_switch'))
                    indicatorColumns.put("trend_MACDSignal_${rule.inputType.replace(',', '_')}", "(${message(code: 'MACDSignal')} (${rule.inputType}")
            } else if (JSON.parse(rule.value).any { it.any { it.startsWith('stocks') } }) {
                def value = JSON.parse(rule.value).find { it.sort().last().startsWith('stocks') }.sort()
                if (value && value instanceof JSONArray) {
                    indicatorName = value?.last()?.replace('.filters.', '.indicators.')?.replace('FilterService', '')
                    if (ClassResolver.serviceExists(indicatorName + "Service"))
                        indicatorColumns.put("${indicatorName.replace('.', '_')}_${value?.first()}".replace('stocks_indicators_symbol_', ''), "(${message(code: value?.last())} (${value?.first()}")
                }
            }
        }

        def columns = [
                [
                        key: 'id'
                ],
                [
                        key: 'symbol'
                ],
                [
                        key: 'symbolName'
                ],
                [
                        key  : 'lastTradePrice',
                        title: message(code: 'symbol.lastTradePrice.label')
                ],
                [
                        key  : 'priceChange',
                        title: message(code: 'symbol.priceChange.label')
                ],
                [
                        key  : 'closingPrice',
                        title: message(code: 'symbol.closingPrice.label')
                ],
                [
                        key  : 'firstTradePrice',
                        title: message(code: 'symbol.firstTradePrice.label')
                ],
                [
                        key  : 'maxPrice',
                        title: message(code: 'symbol.maxPrice.label')
                ],
                [
                        key  : 'minPrice',
                        title: message(code: 'symbol.minPrice.label')
                ],
                [
                        key  : 'yesterdayPrice',
                        title: message(code: 'symbol.yesterdayPrice.label')
                ],
                [
                        key  : 'totalTradeValue',
                        title: message(code: 'symbol.totalTradeValue.label')
                ],
                [
                        key  : 'totalTradeVolume',
                        title: message(code: 'symbol.totalTradeVolume.label')
                ],
                [
                        key  : 'totalTradeCount',
                        title: message(code: 'symbol.totalTradeCount.label')
                ]
        ]
        def visibleColumns = []

        indicatorColumns.each { column ->
            columns.add([
                    key  : column.key,
                    title: column.value
            ])
            visibleColumns.add([
                    key  : column.key,
                    title: column.value
            ])
        }

        if (rules.any { rule ->
            rule.field.endsWith('PriceFilterService') &&
                    [Operators.INCREASE_PERCENT_COMPARE_TO_PREVIOUS_DAY_GREATER_THAN,
                     Operators.DECREASE_PERCENT_COMPARE_TO_PREVIOUS_DAY_GREATER_THAN,
                     Operators.INCREASE_PERCENT_COMPARE_TO_FIRST_PRICE_GREATER_THAN,
                     Operators.DECREASE_PERCENT_COMPARE_TO_FIRST_PRICE_GREATER_THAN].contains(rule.operator)
        }) {
            visibleColumns.add(
                    [
                            key  : 'lastTradePrice',
                            title: message(code: 'symbol.lastTradePrice.label')
                    ])
            visibleColumns.add(
                    [
                            key  : 'priceChange',
                            title: message(code: 'symbol.priceChange.label')
                    ]
            )
        }

        if (visibleColumns.empty)
            visibleColumns.add(
                    [
                            key  : 'lastTradePrice',
                            title: message(code: 'symbol.lastTradePrice.label')
                    ])


        render([
                columns       : columns,
                visibleColumns: visibleColumns
        ] as JSON)
    }

    def screenerData() {
        if (!params.user || !params.id || !params.adjustment) {
            render([
                    status: 'f',
                    body  : ''
            ] as JSON)
            return
        }

        def user = User.get(params.user as Long)
        def id = params.id as Long
        def screener = Screener.get(id)
        if (screener.ownerId != user?.id) {
            render([
                    status: 'f',
                    body  : ''
            ] as JSON)
            return
        }

        render(filterService.applyFilters(Symbol, Rule.findAllByParent(screener?.rule), params.adjustment?.toString()) as JSON)
    }

    private static jalaliDate = { Date date ->
        def cal = Calendar.getInstance()
        cal.setTime(date)

        def result = ''
        def jc = new JalaliCalendar(cal)
        result += String.format("%02d:%02d", cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE))
        if (date < new Date().clearTime()) {
            result += ' '
            result += String.format("%04d/%02d/%02d", jc.getYear(), jc.getMonth(), jc.getDay())
        }
        result
    }
}
