package stocks.analysis

import fi.joensuu.joyds1.calendar.JalaliCalendar
import grails.converters.JSON
import groovy.time.TimeCategory
import org.apache.lucene.search.BooleanQuery
import stocks.User
import stocks.tse.event.IndexEvent
import stocks.tse.SymbolDailyTrade
import stocks.tse.Symbol

class BackTestController {

    def springSecurityService

    def index() {}

    def build() {
        def tradeStrategy = TradeStrategy.get(params.id as Long)

        def startDate = new Date()
        def endDate = new Date()
        use(TimeCategory) {
            startDate = endDate - 1.year
        }
        [tradeStrategy: tradeStrategy, startDate: startDate, endDate: endDate]
    }


    def symbolAutoComplete() {
        def term = params."filter[filters][0][value]"?.toString() ?: ''
        BooleanQuery.setMaxClauseCount(1000000)
        def result = Symbol.search("*${term}*", max: 1000000)
        result = result.results.findAll { Symbol item ->
            !(0..9).contains(item.persianCode.charAt(item.persianCode.size() - 1)) &&
                    (item.persianCode.charAt(0) != 'ج' || item.persianCode.charAt(1) != ' ') &&
                    (item.persianName.charAt(0) != 'ح' || (item.persianName.charAt(1) != ' ' && item.persianName.charAt(1) != '.')) &&
                    ['300', '400', '309', '404'].contains(item.type) &&
                    item.marketCode == 'NO'
        }
        result = result.collect { Symbol symbol ->
            [
                    name : "${symbol.persianCode} - ${symbol.persianName}",
                    value: symbol.id
            ]
        }.sort { it.name }
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
        redirect(action: 'view', id: backTest.id)
    }

    def view() {
        def backTest = BackTest.get(params.id as Long)
        def signals = decorateBackTestSignals(backTest, null)
        def logs = decoratePortfolioLogs(backTest, null)
        [
                backTest: backTest,
                signals : signals,
                logs    : logs,
                summery : backTest.status == BackTestHelper.STATUS_FINISHED ? calculateSummary(backTest, logs, signals) : null
        ]
    }

    def viewJson() {
        def backTest = BackTest.get(params.id as Long)
        def date = new Date(params.startDate as Long)
        render([
                status    : backTest.status,
                signalList: decorateBackTestSignals(backTest, date),
                logs      : decoratePortfolioLogs(backTest, date)
        ] as JSON)
    }

    private def decorateBackTestSignals(BackTest backTest, Date startDate) {
        (startDate ? BackTestSignal.findAllByBackTestAndDateGreaterThanEquals(backTest, startDate) : BackTestSignal.findAllByBackTest(backTest)).sort {
            it.date.time
        }.collect {
            [
                    id        : it.id,
                    time      : it.date.clearTime().time,
                    date      : format.jalaliDate(date: it.date.clearTime()),
                    reason    : message(code: "${it.class.simpleName}.${it.reason}"),
                    price     : Math.round(it.price),
                    stockCount: Math.round(it.stockCount),
                    value     : Math.round(it.price * it.stockCount),
                    wage      : Math.round(it.wage),
                    tax       : Math.round(it.tax),
                    effect    : Math.round((it.effect ?: 0) as float)
            ]
        }
    }

    private static def decoratePortfolioLogs(BackTest backTest, Date startDate) {
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
            def dailyTrades = SymbolDailyTrade.findAllBySymbolAndDailySnapshotBetween(backTest.symbol, backTest.startDate, backTest.endDate)
            dailyTrades.sort {
                it.dailySnapshot.time
            }.each { SymbolDailyTrade dailyTrade ->
                def log = logs.find { it.date.clearTime() == dailyTrade.dailySnapshot.clearTime() }
                list << [
                        dailyTrade.dailySnapshot.clearTime().time,
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

    private static def calculateSummary(BackTest backTest, List logs, List signals) {
        def openDays = SymbolDailyTrade.findAllBySymbolAndDailySnapshotBetween(backTest.symbol, backTest.startDate, backTest.endDate)
        def maxDrawDown = 0
        def sortedLogs = logs.sort { it[0] }
        for (def i = 1; i < sortedLogs.size(); i++) {
            def currentDrawDown = (sortedLogs[i][1] - sortedLogs[i - 1][1]) / sortedLogs[i - 1][1]
            if (currentDrawDown < maxDrawDown)
                maxDrawDown = currentDrawDown
        }

        //success rate
        def successRate = signals.count { it.effect > 0 } * 100 / (signals.count { it.effect > 0 } + signals.count {
            it.effect < 0
        })

        //performance
        def performance = logs.last()[2] / backTest.outlay - 1

        //index info
        def indexFirstValue = IndexEvent.createCriteria().list {
            eq('internalCode', 32097828799138957)
            lte('date', backTest.startDate)
            order('date', ORDER_DESCENDING)
            maxResults(1)
        }?.find()
        if(!indexFirstValue)
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
        def hcStockCount = backTest.outlay / (openDays.first()?.closingPrice * (1 + backTest.buyWage + backTest.buyTax))
        def hcBuyValue = hcStockCount * openDays.first()?.closingPrice * (1 + backTest.buyWage + backTest.buyTax)
        def hcSellValue = hcStockCount * openDays.last()?.closingPrice * (1 - backTest.sellWage + backTest.sellTax)
        def hcPerformance = indexLastValue?.finalIndexValue / indexFirstValue?.finalIndexValue

        [
                initialValue                        : backTest.outlay,
                finalValue                          : logs.last()[1],
                profitableTradesCount               : signals.count { it.effect > 0 },
                lossingTradesCount                  : signals.count { it.effect < 0 },
                successRate                         : successRate,
                returnOfInvestment                  : (logs.last()[1] - backTest.outlay) * 100 / backTest.outlay,
                yearlyBenefit                       : (logs.last()[1] - backTest.outlay) * 100 * 365 / backTest.outlay / openDays.size() / 2,
                dailyBenefit                        : (logs.last()[1] - backTest.outlay) * 100 / backTest.outlay / openDays.size(),
                totalWage                           : signals.sum { it.wage },
                totalTax                            : signals.sum { it.tax },
                maxDrawDown                         : Math.abs(maxDrawDown * 100),
                indexYearlyBenefit                  : (hcPerformance - 1) * 100 * 365 / openDays.size() / 2,
                indexDailyBenefit                   : (hcPerformance - 1) * 100 / openDays.size(),
                performanceCompareToIndex           : performance - hcPerformance,
                dailyBenefitInSimpleHoldingCondition: (backTest.outlay - hcBuyValue + hcSellValue) * 100 / backTest.outlay - 100
        ]
    }

    private static Date parseDate(String date) {
        if (!date || date.trim() == '' || date.trim() == 'null')
            return null
        def dateParts = date.split("/").collect { it as Integer }
        new JalaliCalendar(dateParts[0], dateParts[1], dateParts[2]).toJavaUtilGregorianCalendar().time
    }
}
