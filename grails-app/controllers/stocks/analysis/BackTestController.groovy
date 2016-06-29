package stocks.analysis

import fi.joensuu.joyds1.calendar.JalaliCalendar
import grails.converters.JSON
import groovy.time.TimeCategory
import org.apache.lucene.search.BooleanQuery
import org.codehaus.groovy.grails.web.json.JSONArray
import stocks.User
import stocks.filters.FilterServiceBase
import stocks.indicators.symbol.trend.MACDSignal
import stocks.tse.AdjustmentHelper
import stocks.tse.BlackListedSymbol
import stocks.tse.event.IndexEvent
import stocks.tse.SymbolAdjustedDailyTrade
import stocks.tse.Symbol
import stocks.alerting.Rule

class BackTestController {

    def springSecurityService
    def adjustedPriceSeries9Service

    def index() {}

    def build() {
        def tradeStrategy = TradeStrategy.get(params.id as Long)

        def startDate = new Date()
        def endDate = new Date()
        use(TimeCategory) {
            startDate = endDate - 6.months
        }
        [tradeStrategy: tradeStrategy, startDate: startDate, endDate: endDate]
    }


    def symbolAutoComplete() {
        def queryStr = params."filter[filters][0][value]"?.toString() ?: ''
        BooleanQuery.setMaxClauseCount(1000000)

        def result = Symbol.search("*${queryStr}* AND (marketCode:MCNO AND (type:300 OR type:303 OR type:309) AND -boardCode:4)", max: 20).results.unique { a, b -> a?.id <=> b?.id }.collect {
            [
                    name : "${it.persianCode} - ${it.persianName}",
                    value: it.id
            ]
        }
        render([data: result] as JSON)
    }

    def save() {
        def backTest = new BackTest(params)
        backTest.buyRule = backTest.tradeStrategy.buyRule
        backTest.sellRule = backTest.tradeStrategy.sellRule
        backTest.startDate = parseDate(params.startDateFa as String)
        backTest.endDate = parseDate(params.endDateFa as String)
        use(TimeCategory) {
            backTest.currentDate = backTest.startDate - 1
        }
        backTest.status = BackTestHelper.STATUS_WAITING
        backTest.owner = springSecurityService.currentUser as User
        backTest.save(flush: true)
        redirect(action: 'waitToStart', id: backTest.id)
    }

    def waitToStart() {
        [backTest: BackTest.get(params.id as Long)]
    }

    def waitToStartJson() {
        def backTest = BackTest.get(params.id as Long)
        if (PortfolioLog.countByBackTest(backTest) < 1)
            render 0
        else
            render 1
    }

    def view() {
        def backTest = BackTest.get(params.id as Long)
        while (PortfolioLog.countByBackTest(backTest) < 1)
            Thread.sleep(500)
        def signals = decorateBackTestSignals(backTest, null)

        def buyRules = Rule.findAllByParent(backTest.buyRule)
        def sellRules = Rule.findAllByParent(backTest.buyRule)
        def logs = decoratePortfolioLogs(backTest, null)
        [
                backTest  : backTest,
                signals   : signals,
                logs      : logs,
                summery   : backTest.status == BackTestHelper.STATUS_FINISHED ? calculateSummary(backTest, logs, signals) : null,
                indicators: extractIndicators(backTest),
                buyRules  : buyRules.collect { rule ->
                    def value = JSON.parse(rule.value)
                    [
                            filter   : rule.field,
                            parameter: rule.inputType,
                            operator : rule.operator,
                            value    : value,
                            text     : (stocks.util.ClassResolver.loadServiceByName(rule.field) as FilterServiceBase)?.formatQueryValue(value, rule.operator)
                    ]

                },
                sellRules : sellRules.collect { rule ->
                    def value = JSON.parse(rule.value)
                    [
                            filter   : rule.field,
                            parameter: rule.inputType,
                            operator : rule.operator,
                            value    : value,
                            text     : (stocks.util.ClassResolver.loadServiceByName(rule.field) as FilterServiceBase)?.formatQueryValue(value, rule.operator)
                    ]

                }
        ]
    }

    def extractIndicators(BackTest backTest) {
        def rules = stocks.alerting.Rule.findAllByParentInList([backTest.buyRule, backTest.sellRule])
        def indicatorList = []
        rules.each { rule ->
            def indicatorName = rule.field.replace('.filters.', '.indicators.').replace('FilterService', '')
            if (stocks.util.ClassResolver.serviceExists(indicatorName + "Service"))
                if (!indicatorList.any { it.name == indicatorName && it.parameter == rule.inputType })
                    indicatorList << [name: indicatorName, parameter: rule.inputType]
            if (indicatorName.endsWith('MACD')) {
                if (!JSON.parse(rule.value).contains('constant_switch'))
                    if (!indicatorList.any { it.name == indicatorName + "Signal" && it.parameter == rule.inputType })
                        indicatorList << [name: indicatorName + "Signal", parameter: rule.inputType]
            }

            def value = JSON.parse(rule.value)?.sort { it.size() ? -it[0].size() : 0 }?.first()
            if (value instanceof JSONArray) {
                indicatorName = value?.first()?.replace('.filters.', '.indicators.')?.replace('FilterService', '')
                if (stocks.util.ClassResolver.serviceExists(indicatorName + "Service"))
                    if (!indicatorList.any { it.name == indicatorName && it.parameter == value?.last() })
                        indicatorList << [name: indicatorName, parameter: value?.last()]
            }
        }

        indicatorList
    }

    def viewJson() {
        def backTest = BackTest.get(params.id as Long)
        def date = new Date(params.startDate as Long)
        render([
                currentDate: format.jalaliDate(date: backTest.currentDate),
                status     : backTest.status,
                signalList : decorateBackTestSignals(backTest, date),
                logs       : decoratePortfolioLogs(backTest, date)
        ] as JSON)
    }

    def list() {
        [tradeStrategy: TradeStrategy.get(params.id)]
    }

    def jsonList() {

        def tradeStrategy = TradeStrategy.get(params.id)

        def value = [:]
        def parameters = [offset: params.skip, max: params.pageSize, sort: params["sort[0][field]"] ?: "dateCreated", order: params["sort[0][dir]"] ?: "desc"]

        def list
        list = BackTest.findAllByTradeStrategyAndDeleted(tradeStrategy, false, parameters)
        value.total = BackTest.countByTradeStrategyAndDeleted(tradeStrategy, false)

        value.data = list.collect {
            [
                    id         : it.id,
                    symbol     : "${it.symbol?.persianName} - ${it.symbol?.persianCode}",
                    startDate  : format.jalaliDate(date: it.startDate),
                    endDate    : format.jalaliDate(date: it.endDate),
                    outlay     : it.outlay,
                    buyWage    : it.buyWage,
                    sellWage   : it.sellWage,
                    buyTax     : it.buyTax,
                    sellTax    : it.sellTax,
                    status     : message(code: "backTest.status.${it.status}"),
                    dateCreated: format.jalaliDate(date: it.dateCreated)
            ]
        }

        render value as JSON
    }

    def delete() {

        def backTest = BackTest.get(params.id as Long)
        backTest.deleted = true
        render(backTest.save() ? '1' : '0')
    }

    private def decorateBackTestSignals(BackTest backTest, Date startDate) {
        (startDate ? BackTestSignal.findAllByBackTestAndDateGreaterThanEquals(backTest, startDate) : BackTestSignal.findAllByBackTest(backTest)).sort {
            it.date.time
        }.collect {
            def item = [
                    id              : it.id,
                    time            : it.date.clearTime().time,
                    date            : format.jalaliDate(date: it.date.clearTime()),
                    reason          : message(code: "${it.class.simpleName}.${it.reason}"),
                    price           : Math.round(it.price),
                    stockCount      : Math.round(it.stockCount),
                    value           : Math.round(it.price * it.stockCount),
                    wage            : Math.round(it.wage),
                    tax             : Math.round(it.tax),
                    effect          : Math.round((it.effect ?: 0) as float),
                    //details
                    totalTradeCount : it.totalTradeCount,
                    totalTradeVolume: it.totalTradeVolume,
                    totalTradeValue : it.totalTradeValue,
                    closingPrice    : it.closingPrice,
                    firstTradePrice : it.firstTradePrice,
                    lastTradePrice  : it.lastTradePrice,
                    priceChange     : it.priceChange,
                    minPrice        : it.minPrice,
                    maxPrice        : it.maxPrice,
                    yesterdayPrice  : it.yesterdayPrice
            ]
            //indicators
            it.indicators.each { indicator ->
                item.put("${indicator.name.replace('.', '_')}_${indicator.parameter.replace(',', '_')}", indicator.value)
            }
            item
        }
    }

    private def decoratePortfolioLogs(BackTest backTest, Date startDate) {
        def list = []
        if (startDate) {
            list = PortfolioLog.findAllByBackTestAndDateGreaterThan(backTest, startDate).sort {
                it.date.time
            }.collect {
                [
                        it.date.clearTime().time,
                        Math.round(it.remainingOutlay + it.price * it.stockCount),
                        Math.round(it.price * it.stockCount)
                ]
            }
        } else {
            def logs = PortfolioLog.findAllByBackTest(backTest)
//            def dailyTrades = SymbolAdjustedDailyTrade.findAllBySymbolAndAdjustmentTypeAndDateBetween(backTest.symbol, AdjustmentHelper.defaultType, backTest.startDate, backTest.endDate)
            def dailyTrades = adjustedPriceSeries9Service.dailyTradeList(backTest.symbolId, backTest.startDate, backTest.endDate, '', backTest.adjustmentType)
            dailyTrades.sort {
                it.date.time
            }.each { dailyTrade ->
                def log = logs.find { it.date.clearTime() == dailyTrade.date.clearTime() }
                list << [
                        dailyTrade.date.clearTime().time,
                        log ? Math.round(log.remainingOutlay + log.price * log.stockCount) : 0,
                        log ? Math.round(log.price * log.stockCount) : 0
                ]
            }
        }
        list
    }

    def summary() {
        def backTest = BackTest.get(params.id as Long)
        def signals = decorateBackTestSignals(backTest, null)
        def logs = decoratePortfolioLogs(backTest, null)
        render template: 'summary', model: [summary: calculateSummary(backTest, logs, signals)]
    }

    private def calculateSummary(BackTest backTest, List logs, List signals) {
        def totalDays
        use(TimeCategory) {
            totalDays = (backTest.endDate - backTest.startDate).days + 1
        }
//        def openDays = SymbolAdjustedDailyTrade.findAllBySymbolAndAdjustmentTypeAndDateBetween(backTest.symbol, AdjustmentHelper.defaultType, backTest.startDate, backTest.endDate)
        def openDays = adjustedPriceSeries9Service.dailyTradeList(backTest.symbolId, backTest.startDate, backTest.endDate, '', backTest.adjustmentType)
        def maxDrawDown = 0
        def sortedLogs = logs.sort { it[0] }
        for (def i = 1; i < sortedLogs.size(); i++) {
            def currentDrawDown = (sortedLogs[i][1] - sortedLogs[i - 1][1]) / sortedLogs[i - 1][1]
            if (currentDrawDown < maxDrawDown)
                maxDrawDown = currentDrawDown
        }

        //success rate
        def successRate = signals.size() > 0 ? signals.count { it.effect > 0 } * 100 / ((signals.count {
            it.effect > 0
        } ?: 1) + signals.count {
            it.effect < 0
        }) : 0

        //performance
        def performance = logs.size() > 0 ? logs.last()[2] / backTest.outlay - 1 : 0

        //index info
        def indexFirstValue = IndexEvent.createCriteria().list {
            eq('internalCode', 32097828799138957)
            lte('date', backTest.startDate)
            order('date', ORDER_DESCENDING)
            maxResults(1)
        }?.find()
        if (!indexFirstValue)
            indexFirstValue = IndexEvent.createCriteria().list {
                eq('internalCode', 32097828799138957)
                order('date', ORDER_ASCENDING)
                maxResults(1)
            }?.find()
        def indexLastValue = IndexEvent.createCriteria().list {
            eq('internalCode', 32097828799138957)
            lte('date', backTest.endDate)
            order('date', ORDER_DESCENDING)
            maxResults(1)
        }?.find()

        //holding condition
        def hcStockCount = openDays.size() > 0 ? backTest.outlay / (openDays.first()?.closingPrice * (1 + backTest.buyWage + backTest.buyTax)) : 0
        def hcBuyValue = openDays.size() > 0 ? hcStockCount * openDays.first()?.closingPrice * (1 + backTest.buyWage + backTest.buyTax) : 0
        def hcSellValue = openDays.size() > 0 ? hcStockCount * openDays.last()?.closingPrice * (1 - backTest.sellWage - backTest.sellTax) : 0
        def hcPerformance = indexLastValue?.finalIndexValue / indexFirstValue?.finalIndexValue
        def yearlyBenefit = logs.size() > 0 ? (logs.last()[1] - backTest.outlay) * 100 * 365 / backTest.outlay / totalDays / 2 : 0
        def indexYearlyBenefit = (hcPerformance - 1) * 100 * 365 / totalDays
        [
                initialValue                        : backTest.outlay,
                finalValue                          : logs.size() > 0 ? logs.last()[1] : 0,
                profitableTradesCount               : signals.count { it.effect > 0 },
                lossingTradesCount                  : signals.count { it.effect < 0 },
                successRate                         : successRate,
                returnOfInvestment                  : logs.size() > 0 ? (logs.last()[1] - backTest.outlay) * 100 / backTest.outlay : 0,
                yearlyBenefit                       : yearlyBenefit,
                dailyBenefit                        : logs.size() > 0 ? (logs.last()[1] - backTest.outlay) * 100 / backTest.outlay / openDays.size() : 0,
                totalWage                           : signals.sum { it.wage } ?: 0,
                totalTax                            : signals.sum { it.tax } ?: 0,
                maxDrawDown                         : Math.abs(maxDrawDown * 100),
                indexYearlyBenefit                  : indexYearlyBenefit,
                indexDailyBenefit                   : (hcPerformance - 1) * 100 / openDays.size(),
                performanceCompareToIndex           : yearlyBenefit - indexYearlyBenefit,//(performance - (hcPerformance - 1)) * 100,
                dailyBenefitInSimpleHoldingCondition: (performance - ((backTest.outlay - hcBuyValue + hcSellValue) / backTest.outlay - 1)) * 100
        ]
    }

    private static Date parseDate(String date) {
        if (!date || date.trim() == '' || date.trim() == 'null')
            return null
        def dateParts = date.split("/").collect { it as Integer }
        new JalaliCalendar(dateParts[0], dateParts[1], dateParts[2]).toJavaUtilGregorianCalendar().time
    }
}
