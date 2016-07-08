package stocks

import com.tinkerpop.blueprints.impls.orient.OrientVertex
import fi.joensuu.joyds1.calendar.JalaliCalendar
import grails.converters.JSON
import groovy.time.TimeCategory
import org.apache.lucene.search.BooleanQuery
import org.codehaus.groovy.grails.web.json.JSONArray
import stocks.accounting.Transaction
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
import stocks.tse.Future
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
import stocks.alerting.QueryInstance
import stocks.twitter.Search.TwitterPerson
import stocks.twitter.Article

class MobileController {

    def springSecurityService
    def marketStatusService
    def filterService
    def adjustedPriceSeries9Service
    def searchableService
    def sharingService
    def materialGraphService
    def groupGraphService
    def personGraphService
    def accountingService
    def commonGraphService
    def priceService
    def followGraphService
    def rateGraphService
    def graphDBService
    def commentGraphService
    def likeGraphService

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
                maxResults(20)
            }
            if (!before && !after) {
                maxResults(20)
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
                    date : format.jalaliDate(date: it[3], hm: true)
            ]
        }

        render(list as JSON)
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
                ['us-dollar-exchange', 'euro', 'gbp', 'aed', 'lear-turkey', 'chinese-yuan'].each {
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
                ['copper', 'aluminium', 'nickel', 'tin', 'zinc', 'cobalt'].each {
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

        render(AdjustmentHelper.ENABLED_TYPES as JSON)
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
                    def daysCount = JSON.parse(rule.value).find{!(it instanceof String)}.find { !it.contains('.') }
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

    def ohlcv() {
        def symbolId = params.id as Long
        def adjustmentType = params.adjustmentType as String
        def startDate = params.start ? new Date(params.start as Long) : null
        def endDate = params.end ? new Date(params.end as Long) : new Date()
        if (!startDate)
            use(TimeCategory) {
                startDate = new Date() - 30.days
            }

        render(
                adjustedPriceSeries9Service.dailyTradeList(symbolId, startDate, endDate, '', adjustmentType)
                        .collect {
                    [
                            format.jalaliDate(date: it.date),
                            it.firstTradePrice,
                            it.maxPrice,
                            it.minPrice,
                            it.lastTradePrice,
                            it.totalTradeVolume
                    ]
                } as JSON)
    }

    def queryList() {
        if (!params.user) {
            render([
                    status: 'f',
                    body  : ''
            ] as JSON)
            return
        }
        def user = User.get(params.user as Long)

        render(QueryInstance.findAllByOwnerAndDeleted(user, false).collect {
            [
                    id   : it.id,
                    title: it.title
            ]
        } as JSON)
    }

    def queryDetails() {
        if (!params.user || !params.id) {
            render([
                    status: 'f',
                    body  : ''
            ] as JSON)
            return
        }
        def user = User.get(params.user as Long)
        def item = QueryInstance.get(params.id as Long)
        if (item?.ownerId != user?.id) {
            render([
                    status: 'f',
                    body  : ''
            ] as JSON)
            return
        }

        render(
                [
                        id         : item.id,
                        title      : item.title,
                        imageId    : item.query?.imageId,
                        description: item.description,
                        enabled    : item.enabled,
                        parameters : item.parameterValues?.collect { [name: it.parameter?.name, value: it.text] }
                ] as JSON)
    }

    def toggleQueryStatus() {
        if (!params.user || !params.id) {
            render([
                    status: 'f',
                    body  : ''
            ] as JSON)
            return
        }
        def user = User.get(params.user as Long)
        def item = QueryInstance.get(params.id as Long)
        if (item?.ownerId != user?.id) {
            render([
                    status: 'f',
                    body  : ''
            ] as JSON)
            return
        }

        item.enabled = !item.enabled;
        item.save(flush: true)

        render([
                status: 's',
                body  : ''
        ] as JSON)
    }

    def sendTwit() {
        if (!params.user || !params.body) {
            render([
                    status: 'f',
                    body  : ''
            ] as JSON)
            return
        }

        def owner = User.get(params.user)
        if (owner) {
            try {
                def body = refineTwitBody(FarsiNormalizationFilter.apply(params.body?.toString()))
                def tags = sharingService.extractTextRelations(body)
                sharingService.shareTalk(owner, tags.text,
                        tags.tagList,
                        tags.mentionList)
                render "1"
            } catch (Exception exception) {
                render exception.message
            }
        }
    }

    private String refineTwitBody(String input) {
        def parts = input.split(' ')
        def result = []
        parts.findAll { it && it != '' }.each { String item ->
            if (item.startsWith("@"))
                result << refineMention(item)
            else if (item.startsWith("#"))
                result << refineTag(item)
            else
                result << item
        }
        result.join(' ')
    }

    String refineMention(String input) {
        def queryStr = input?.trim()?.replace('@', '') ?: ''
        BooleanQuery.setMaxClauseCount(1000000)
        def item = TwitterPerson.search("*${queryStr?.replace('_', '* AND *')}*", max: 1)?.results?.find()

        "<a data-clazz=\"Person\" data-id=\"${item.rid}\" href=\"${createLink(controller: 'user', action: 'wall', id: item.identifier, absolute: true)}\" class=\"hashAuthor\">@${queryStr}</a>"
    }

    String refineTag(String input) {
        def queryStr = input?.trim()?.replace('#', '') ?: ''
        BooleanQuery.setMaxClauseCount(1000000)
        def items = searchableService.search("*${queryStr?.replace('_', '* AND *')}*"?.toString(), max: 100)?.results
        def result = ""
        items.each { item ->
            if (result == "") {
                if (item instanceof Symbol) {
                    def casted = item as Symbol
                    if (casted.marketCode == "MCNO" && ['300', '303'].contains(casted.type) && casted.boardCode != '4')
                        result = formatTag('Symbol', casted.id, queryStr)
                }
                if (item instanceof Index
                        || item instanceof Future
                        || item instanceof Currency
                        || item instanceof Coin
                        || item instanceof Metal
                        || item instanceof Oil) {
                    result = formatTag(item.class.simpleName, item.id, queryStr)
                }
            }
        }
        result
    }

    def userBalance() {
        if (!params.user) {
            render([
                    status: 'f',
                    body  : ''
            ] as JSON)
            return
        }

        def user = User.get(params.user as Long)
        render([
                balance: accountingService.userBalance(user?.id),
                maxDept: user?.maxDept ?: 0
        ] as JSON)
    }

    def twitterHomeOld() {
        if (!params.user) {
            render([
                    status: 'f',
                    body  : ''
            ] as JSON)
            return
        }

        def user = User.get(params.user as Long)
        def list = materialGraphService.listOldForHome(user?.id, params.minDate ? new Date(params.minDate as Long) : new Date(), 20)
        render(
                [
                        data   : list.collect {
                            appendMeta(user, "format${it.label}"(it))
                        },
                        minDate: list.collect { it.publishDate.time }.min(),
                        maxDate: list.collect { it.publishDate.time }.max()
                ] as JSON)
    }

    def twitterHomeNew() {
        if (!params.user) {
            render([
                    status: 'f',
                    body  : ''
            ] as JSON)
            return
        }

        def user = User.get(params.user as Long)
        def list = materialGraphService.listNewForHome(user?.id, new Date(params.maxDate as Long), 20)
        render(
                [
                        data   : list.collect {
                            appendMeta(user, "format${it.label}"(it))
                        },
                        minDate: list.collect { it.publishDate.time }.min(),
                        maxDate: list.collect { it.publishDate.time }.max()
                ] as JSON)
    }


    def groupHomeOld() {
        if (!params.user || !params.id) {
            render([
                    status: 'f',
                    body  : ''
            ] as JSON)
            return
        }

        def user = User.get(params.user as Long)
        def list = materialGraphService.listByGroup(params.id as String, params.skip as Integer, params.limit as Integer)
        render(list.collect {
            appendMeta(user, "format${it.label}"(it))
        } as JSON)
    }

    def groupMembership() {
        if (!params.user || !params.id) {
            render([
                    status: 'f',
                    body  : ''
            ] as JSON)
            return
        }

        def user = User.get(params.user as Long)
        def membership = groupGraphService.getUserMembershipInGroup(params.id as String, user?.id)

        render([
                id    : membership ? membership.id : 0,
                status: membership ? (membership.endDate ? jalaliDate(membership.endDate as Date) : 1) : 0
        ] as JSON)
    }

    def groupRevokeMembership() {
        if (!params.user || !params.id) {
            render([
                    status: 'f',
                    body  : ''
            ] as JSON)
            return
        }
        groupGraphService.deleteMember(params.id as String)
        render '1'
    }

    def groupRegisterMembership() {
        if (!params.user || !params.id || !params.period) {
            render([
                    status: 'f',
                    body  : ''
            ] as JSON)
            return
        }

        def user = User.get(params.user as Long)
        def group = commonGraphService.getAndUnwrap(params.id as String)

        def startDate = new Date()
        def endDate = null
        use(TimeCategory) {
            endDate = startDate + (params.period as Integer).months
        }
        groupGraphService.addMember(params.id as String, user, startDate, endDate)

        def price = group."membership${params.period}MonthPrice"
        if (price > 0) {
            def transaction = new Transaction()
            transaction.date = new Date()
            transaction.accountId = grailsApplication.config.accounts.find { it.default }.id
            transaction.creator = user
            transaction.customer = user
            transaction.type = AccountingHelper.TRANSACTION_TYPE_WITHDRAWAL
            transaction.value = price
            transaction.description = message(code: 'transaction.description.group.register', args: [group.title])
            transaction.save()
        }

        render '1'
    }

    def propertyHomeOld() {
        if (!params.user || !params.id) {
            render([
                    status: 'f',
                    body  : ''
            ] as JSON)
            return
        }

        def user = User.get(params.user as Long)
        def list = materialGraphService.listByProperty(params.id as String, params.skip as Integer, params.limit as Integer)
        render(list.collect {
            appendMeta(user, "format${it.label}"(it))
        } as JSON)
    }

    def authorInfo() {
        if (!params.user || !params.id) {
            render([
                    status: 'f',
                    body  : ''
            ] as JSON)
            return
        }

        def list = personGraphService.authorInfo(params?.id?.toString());
        def followStatus = followGraphService.getUserFollowshipForItem(params.id?.toString(), params.user as Long)
        render([
                info        : list,
                followStatus: followStatus ? "1" : "0"
        ] as JSON)
    }

    def infoSymbol() {
        if (!params.user || !params.id || !params.identifier) {
            render([
                    status: 'f',
                    body  : ''
            ] as JSON)
            return
        }
        def propertyInfo = priceService.lastDailyTrade(Symbol.get(params.identifier as Long))
        def followStatus = followGraphService.getUserFollowshipForItem(params.id?.toString(), params.user as Long)
        if (!propertyInfo) {
            render([:] as JSON)
            return
        }
        def lastTradePriceChangePercent = Math.round(propertyInfo.priceChange * 10000 / (propertyInfo.lastTradePrice - propertyInfo.priceChange)) / 100
        def closingPriceChangePercent = Math.round((propertyInfo.priceChange + propertyInfo.closingPrice - propertyInfo.lastTradePrice) * 10000 / (propertyInfo.closingPrice - (propertyInfo.priceChange + propertyInfo.closingPrice - propertyInfo.lastTradePrice))) / 100
        def totalTradeVolume = propertyInfo.totalTradeVolume
        def totalTradeVolumeFlag = ""
        if (totalTradeVolume > 1000) {
            totalTradeVolume = totalTradeVolume.toDouble() / 1000
            totalTradeVolumeFlag = "K"
        }
        if (totalTradeVolume > 1000) {
            totalTradeVolume = totalTradeVolume.toDouble() / 1000
            totalTradeVolumeFlag = "M"
        }
        if (totalTradeVolume > 1000) {
            totalTradeVolume = totalTradeVolume.toDouble() / 1000
            totalTradeVolumeFlag = "B"
        }
        def totalTradeValue = propertyInfo.totalTradeValue
        def totalTradeValueFlag = ""
        if (totalTradeValue > 1000) {
            totalTradeValue = totalTradeValue.toDouble() / 1000
            totalTradeValueFlag = "K"
        }
        if (totalTradeValue > 1000) {
            totalTradeValue = totalTradeValue.toDouble() / 1000
            totalTradeValueFlag = "M"
        }
        if (totalTradeValue > 1000) {
            totalTradeValue = totalTradeValue.toDouble() / 1000
            totalTradeValueFlag = "B"
        }

        render([
                price                    : propertyInfo.lastTradePrice,
                priceChange              : propertyInfo.priceChange,
                priceChangePercent       : lastTradePriceChangePercent,
                closingPrice             : propertyInfo.closingPrice,
                closingPriceChange       : propertyInfo.priceChange + propertyInfo.closingPrice - propertyInfo.lastTradePrice,
                closingPriceChangePercent: closingPriceChangePercent,
                date                     : jalaliDate(propertyInfo.modificationDate),
                high                     : propertyInfo.maxPrice,
                low                      : propertyInfo.minPrice,
                count                    : propertyInfo.totalTradeCount,
                open                     : propertyInfo.firstTradePrice,
                volume                   : (Math.round(totalTradeVolume.toDouble() * 100) / 100) + totalTradeVolumeFlag,
                value                    : (Math.round(totalTradeValue * 100) / 100) + totalTradeValueFlag,
                followStatus             : followStatus ? "1" : "0"
        ] as JSON)
    }

    def infoIndex() {
        if (!params.user || !params.id || !params.identifier) {
            render([
                    status: 'f',
                    body  : ''
            ] as JSON)
            return
        }
        def propertyInfo = Index.get(params.identifier as Long)
        def followStatus = followGraphService.getUserFollowshipForItem(params.id?.toString(), params.user as Long)
        def todayIndexChangeValue = Math.round(propertyInfo.todayIndexChangePercent / (propertyInfo.finalIndexValue / 100 + 1))

        render([
                price             : propertyInfo.finalIndexValue,
                priceChange       : todayIndexChangeValue,
                priceChangePercent: propertyInfo.todayIndexChangePercent,
                date              : jalaliDate(propertyInfo.modificationDate),
                high              : propertyInfo.highestIndexValue,
                low               : propertyInfo.lowestIndexValue,
                count             : propertyInfo.tradedSymbolCount,
                decreasedCount    : propertyInfo.decreasedSymbolCount,
                increasedCount    : propertyInfo.increasedSymbolCount,
                unchangedCount    : propertyInfo.unchangedSymbolCount,
                notTradedCount    : propertyInfo.notTradedSymbolCount,
                reservedCount     : propertyInfo.reservedSymbolCount,
                suspendedCount    : propertyInfo.suspendedSymbolCount,
                symbolCount       : propertyInfo.totalSymbolCount,
                followStatus      : followStatus ? "1" : "0"

        ] as JSON)
    }

    def infoCoin() {
        if (!params.user || !params.id || !params.identifier) {
            render([
                    status: 'f',
                    body  : ''
            ] as JSON)
            return
        }
        def propertyInfo = Coin.get(params.identifier as Long)
        def followStatus = followGraphService.getUserFollowshipForItem(params.id?.toString(), params.user as Long)

        render([
                price             : propertyInfo.price,
                priceChange       : propertyInfo.change,
                priceChangePercent: propertyInfo.percent,
                date              : jalaliDate(propertyInfo.time),
                high              : propertyInfo.high,
                low               : propertyInfo.low,
                followStatus      : followStatus ? "1" : "0"
        ] as JSON)
    }

    def infoCurrency() {
        if (!params.user || !params.id || !params.identifier) {
            render([
                    status: 'f',
                    body  : ''
            ] as JSON)
            return
        }
        def propertyInfo = Currency.get(params.identifier as Long)
        def followStatus = followGraphService.getUserFollowshipForItem(params.id?.toString(), params.user as Long)

        render([
                price             : propertyInfo.price,
                priceChange       : propertyInfo.change,
                priceChangePercent: propertyInfo.percent,
                date              : jalaliDate(propertyInfo.time),
                high              : propertyInfo.high,
                low               : propertyInfo.low,
                followStatus      : followStatus ? "1" : "0"
        ] as JSON)

    }

    def infoMetal() {
        if (!params.user || !params.id || !params.identifier) {
            render([
                    status: 'f',
                    body  : ''
            ] as JSON)
            return
        }
        def propertyInfo = Metal.get(params.identifier as Long)
        def followStatus = followGraphService.getUserFollowshipForItem(params.id?.toString(), params.user as Long)
        render([
                price             : propertyInfo.price,
                priceChange       : propertyInfo.change,
                priceChangePercent: propertyInfo.percent,
                date              : jalaliDate(propertyInfo.time),
                high              : propertyInfo.high,
                low               : propertyInfo.low,
                followStatus      : followStatus ? "1" : "0"
        ] as JSON)
    }

    def infoFuture() {
        if (!params.user || !params.id || !params.identifier) {
            render([
                    status: 'f',
                    body  : ''
            ] as JSON)
            return
        }
        def propertyInfo = CoinFuture.get(params.identifier as Long)
        def followStatus = followGraphService.getUserFollowshipForItem(params.id?.toString(), params.user as Long)
        def totalTradeVolume = propertyInfo.tradesVolume
        def totalTradeVolumeFlag = ""
        if (totalTradeVolume > 1000) {
            totalTradeVolume = totalTradeVolume.toDouble() / 1000
            totalTradeVolumeFlag = "K"
        }
        if (totalTradeVolume > 1000) {
            totalTradeVolume = totalTradeVolume.toDouble() / 1000
            totalTradeVolumeFlag = "M"
        }
        if (totalTradeVolume > 1000) {
            totalTradeVolume = totalTradeVolume.toDouble() / 1000
            totalTradeVolumeFlag = "B"
        }
        def totalTradeValue = propertyInfo.tradesValue
        def totalTradeValueFlag = ""
        if (totalTradeValue > 1000) {
            totalTradeValue = totalTradeValue.toDouble() / 1000
            totalTradeValueFlag = "K"
        }
        if (totalTradeValue > 1000) {
            totalTradeValue = totalTradeValue.toDouble() / 1000
            totalTradeValueFlag = "M"
        }
        if (totalTradeValue > 1000) {
            totalTradeValue = totalTradeValue.toDouble() / 1000
            totalTradeValueFlag = "B"
        }

        render([
                price             : propertyInfo.lastTradedPrice,
                priceChange       : propertyInfo.lastTradedPriceChanges,
                priceChangePercent: propertyInfo.lastTradedPriceChangesPercent,
                closingPrice      : propertyInfo.closingPrice,
                date              : jalaliDate(propertyInfo.lastTradingDate),
                high              : propertyInfo.highTradedPrice,
                low               : propertyInfo.lowTradedPrice,
                count             : propertyInfo.tradesCount,
                open              : propertyInfo.firstTradedPrice,
                volume            : (Math.round(totalTradeVolume.toDouble() * 100) / 100) + totalTradeVolumeFlag,
                value             : (Math.round(totalTradeValue * 100) / 100) + totalTradeValueFlag,
                followStatus      : followStatus ? "1" : "0"
        ] as JSON)
    }

    def infoOil() {
        if (!params.user || !params.id || !params.identifier) {
            render([
                    status: 'f',
                    body  : ''
            ] as JSON)
            return
        }
        def propertyInfo = Oil.get(params.identifier as Long)
        def followStatus = followGraphService.getUserFollowshipForItem(params.id?.toString(), params.user as Long)
        render([
                price             : propertyInfo.price,
                priceChange       : propertyInfo.change,
                priceChangePercent: propertyInfo.percent,
                date              : jalaliDate(propertyInfo.time),
                high              : propertyInfo.high,
                low               : propertyInfo.low,
                open              : propertyInfo.open,
                followStatus      : followStatus ? "1" : "0"
        ] as JSON)
    }

    def follow() {
        if (!params.user || !params.id) {
            render([
                    status: 'f',
                    body  : ''
            ] as JSON)
            return
        }
        def user = User.get(params.user as Long)
        followGraphService.follow(user, params.id as String)
        render "1"
    }

    def unFollow() {
        if (!params.user || !params.id) {
            render([
                    status: 'f',
                    body  : ''
            ] as JSON)
            return
        }
        followGraphService.unfollow(params.user as Long, params.id as String)
        render "1"
    }

    def followList() {
        if (!params.user) {
            render([
                    status: 'f',
                    body  : ''
            ] as JSON)
            return
        }

        render((materialGraphService.getFollowList(params.user as Long, params.skip as Integer, params.limit as Integer) ?: []) as JSON)
    }

    def suggestList() {
        if (!params.user) {
            render([
                    status: 'f',
                    body  : ''
            ] as JSON)
            return
        }

        render((sharingService.suggestFollowList(params.user as Long, params.skip as Integer, params.limit as Integer) ?: []) as JSON)
    }

    def authorHomeOld() {
        if (!params.user || !params.id) {
            render([
                    status: 'f',
                    body  : ''
            ] as JSON)
            return
        }

        def user = User.get(params.user as Long)
        def list = materialGraphService.listByAuthor(params.id as String, params.skip as Integer, params.limit as Integer)
        render(list.collect {
            appendMeta(user, "format${it.label}"(it))
        } as JSON)
    }

    def rate() {
        if (!params.user || !params.id || !params.value) {
            render([
                    status: 'f',
                    body  : ''
            ] as JSON)
            return
        }

        def user = User.get(params.user as Long)
        rateGraphService.saveRate(user, params.id as String, params.value as Integer)
        render rateGraphService.getPersonRateForMaterial(user, graphDBService.getVertex(params.id?.toString()))?.value?.toString() ?: 0
    }

    private def formatTalk(material) {
        [
                id      : material.id,
                title   : material.description?.replaceAll("<(.|\n)*?>", ''),
                date    : jalaliDate(material.publishDate as Date),
                dateLong: material.publishDate.time,
                type    : material.label
        ]
    }

    private def formatArticle(material) {
        def article = Article.get(material.identifier as Long)
        [
                id      : material.id,
                title   : material.title,
                summary : article.summary?.replaceAll("<(.|\n)*?>", ''),
                body    : article.body?.replaceAll("<(.|\n)*?>", ''),
                date    : jalaliDate(material.publishDate as Date),
                dateLong: material.publishDate.time,
                type    : material.label,
                imageId : material.imageId
        ]
    }

    private appendMeta(User user, Map item) {
        def meta = materialGraphService.getMeta(item.id as String)
        def groups = meta.findAll { it.label == 'Group' && it.ownerType == 'user' }
        def hasAccess = groups.size() == 0
        if (!hasAccess) {
            def userGroups = groupGraphService.memberGroups(user)
            hasAccess = userGroups.any { userGroup -> groups.any { group -> group.idNumber == userGroup.idNumber } }
        }
        item.groups = groups
        item.hasAccess = hasAccess
        item.author = meta.find { it.label == 'Person' }
        item.propertyList = materialGraphService.getPropertyList(item.id?.toString()?.replace('#', ''))
        item.rate = rateGraphService.getAverageRate(item.id?.toString()?.replace('#', '')) ?: 0
        item.userRate = rateGraphService.getPersonRateForMaterial(user, graphDBService.getVertex(item.id?.toString()?.replace("#", "")))?.value?.toString()
        if (item.userRate)
            item.userRate = message(code: "rating.options.${item.userRate}");
        else
            item.userRate = "";
        switch (item.type) {
            case "Talk":
                item.image = createLink(controller: 'image', action: 'profile', id: item.author.identifier, params: [size: 150])
                break
            case "Article":
                item.image = createLink(controller: 'image', action: 'index', id: item.imageId, params: [size: 150])
                break
        }
        item
    }

    def commentList() {

        if (!params.user || !params.id) {
            render([
                    status: 'f',
                    body  : ''
            ] as JSON)
            return
        }

        def user = User.get(params.user as Long)
        render(commentGraphService.getCommentList(params.id as String).collect {
            [
                    id           : it.id,
                    body         : it.body,
                    date         : jalaliDate(it.lastUpdated as Date),
                    author       : it.author,
                    likesCount   : likeGraphService.getLikesCount(it.idNumber as String),
                    dislikesCount: likeGraphService.getDislikesCount(it.idNumber as String),
                    hasLiked     : likeGraphService.hasLiked(user, it.idNumber as String),
                    hasDisliked  : likeGraphService.hasDisliked(user, it.idNumber as String)
            ]
        } as JSON)
    }

    private String formatTag(clazz, id, title) {
        "<a data-clazz=\"${clazz}\" data-id=\"${id}\" href=\"${createLink(controller: clazz.toLowerCase(), action: 'info', id: id, absolute: true)}\" class=\"hashTag\">#${title}</a>"
    }

    private static jalaliDate = { Date date ->
        def cal = Calendar.getInstance()
        cal.setTime(date)

        def result = ''
        def jc = new JalaliCalendar(cal)
        result += String.format("%02d:%02d", cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE))
        if (date < new Date().clearTime() || date > (new Date() + 1).clearTime()) {
            result += ' '
            result += String.format("%04d/%02d/%02d", jc.getYear(), jc.getMonth(), jc.getDay())
        }
        result
    }
}
