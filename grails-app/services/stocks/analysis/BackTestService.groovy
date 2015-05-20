package stocks.analysis

import grails.converters.JSON
import groovy.time.TimeCategory
import org.codehaus.groovy.grails.web.json.JSONArray
import stocks.alerting.Rule
import stocks.filters.FilterServiceBase
import stocks.tse.AdjustmentHelper
import stocks.tse.SymbolAdjustedDailyTrade
import stocks.util.ClassResolver

class BackTestService {

    def springSecurityService
    def priceService

    def runBackTest(BackTest backTest) {
        10.times {
            if (backTest.status != BackTestHelper.STATUS_FINISHED) {
                stepForwardBackTest(backTest)
            }
        }
    }

    void stepForwardBackTest(BackTest backTest) {
        use(TimeCategory) {
            backTest.currentDate = (backTest.currentDate + 1.day).clearTime()
        }

        if (backTest.currentDate > backTest.endDate) {
            backTest.status = BackTestHelper.STATUS_FINISHED
        } else {
            def dailyTrade = priceService.dailyTrade(backTest?.symbol, backTest?.currentDate, AdjustmentHelper.defaultType)
            if (dailyTrade) {
                def portfolioLog = getLastPortfolioLog(backTest, dailyTrade.closingPrice)
                def lastSignal = BackTestSignal.createCriteria().list {
                    eq('backTest', backTest)
                    order('date', ORDER_DESCENDING)
                    maxResults(1)
                }?.find()
                if (canBuy(backTest, portfolioLog, dailyTrade.closingPrice) && shouldBuy(backTest)) {
                    def signal = buy(backTest, portfolioLog, dailyTrade)
                    updatePortfolioLog(backTest, portfolioLog, dailyTrade, signal)
                } else if (canSell(portfolioLog)) {
                    def signal = null
                    def limitsResult = shouldSellDueToLimits(backTest, portfolioLog, dailyTrade, lastSignal as BuySignal)
                    if (limitsResult || shouldSell(backTest)) {
                        signal = sell(backTest, portfolioLog, dailyTrade, limitsResult ?: BackTestHelper.REASON_RULES_MATCHED)
                    }
                    updatePortfolioLog(backTest, portfolioLog, dailyTrade, signal)
                } else
                    updatePortfolioLog(backTest, portfolioLog, dailyTrade)
            }
        }

        backTest.save(flush: true)
    }

    Boolean canBuy(BackTest backTest, PortfolioLog portfolioLog, Double price) {
        portfolioLog.remainingOutlay >= price * (1 + backTest.buyWage + backTest.buyTax)
    }

    Boolean shouldBuy(BackTest backTest) {
        checkRules(backTest, Rule.findAllByParent(backTest.buyRule))
    }

    BackTestSignal buy(BackTest backTest, PortfolioLog portfolioLog, SymbolAdjustedDailyTrade dailyTrade) {
        def signal = new BuySignal()
        fillSignalCommonFields(signal, backTest, dailyTrade)
        signal.stockCount = Math.floor(portfolioLog.remainingOutlay / (dailyTrade.closingPrice * (1 + backTest.buyWage + backTest.buyTax)))
        signal.wage = backTest.buyWage * signal.stockCount * dailyTrade.closingPrice
        signal.tax = backTest.buyTax * signal.stockCount * dailyTrade.closingPrice
        signal.reason = BackTestHelper.REASON_RULES_MATCHED
        signal.save(flush: true)
    }

    Boolean canSell(PortfolioLog portfolioLog) {
        portfolioLog.stockCount > 0
    }

    Boolean shouldSell(BackTest backTest) {
        checkRules(backTest, Rule.findAllByParent(backTest.sellRule))
    }

    String shouldSellDueToLimits(BackTest backTest, PortfolioLog portfolioLog, SymbolAdjustedDailyTrade dailyTrade, BuySignal lastSignal) {
        if (!lastSignal)
            return null

        if (backTest.timeLimit) {
            def maxAcceptableDate = null
            use(TimeCategory) {
                maxAcceptableDate = backTest.currentDate - backTest.timeLimit.days
            }
            if (maxAcceptableDate >= lastSignal.date)
                return BackTestHelper.REASON_TIME_LIMIT
        }

        if (backTest.profitLimit || backTest.lossLimit) {
            def sellValue = portfolioLog.stockCount * dailyTrade.closingPrice * (1 - backTest.sellWage - backTest.sellTax)

            if (backTest.profitLimit && sellValue - lastSignal.totalValue >= backTest.profitLimitValue)
                return BackTestHelper.REASON_PROFIT_LIMIT

            if (backTest.lossLimit && lastSignal.totalValue - sellValue >= backTest.lossLimitValue)
                return BackTestHelper.REASON_LOSS_LIMIT
        }
        null
    }

    BackTestSignal sell(BackTest backTest, PortfolioLog portfolioLog, SymbolAdjustedDailyTrade dailyTrade, String reason) {
        def signal = new SellSignal()
        fillSignalCommonFields(signal, backTest, dailyTrade)
        signal.stockCount = portfolioLog.stockCount
        signal.wage = backTest.sellWage * signal.stockCount * dailyTrade.closingPrice
        signal.tax = backTest.sellTax * signal.stockCount * dailyTrade.closingPrice
        signal.reason = reason

        //calculate effect
        def previousBuySignals = BuySignal.executeQuery("from BuySignal bs where bs.backTest.id = ${backTest.id} and not exists (select id from SellSignal ss where ss.backTest.id = ${backTest.id} and ss.date > bs.date)")
        signal.effect = signal.totalValue - (previousBuySignals?.sum { it.totalValue } as Double) ?: 0

        signal.save(flush: true)
    }

    PortfolioLog getLastPortfolioLog(BackTest backTest, Double price) {
        def portfolioLog = PortfolioLog.createCriteria().list {
            eq('backTest', backTest)
            order('id', ORDER_DESCENDING)
            maxResults(1)
        }?.find()
        if (!portfolioLog) {
            portfolioLog = new PortfolioLog()
            portfolioLog.backTest = backTest
            use(TimeCategory) {
                portfolioLog.date = backTest.startDate - 1.day
            }
            portfolioLog.price = price
            portfolioLog.remainingOutlay = backTest.outlay
            portfolioLog.stockCount = 0
            portfolioLog.save(flush: true)
        }
        portfolioLog
    }

    Boolean checkRules(BackTest backTest, List<Rule> rules) {

        def filters = rules.collect { rule ->
            [
                    service  : ClassResolver.loadServiceByName(rule.field),
                    parameter: rule.inputType,
                    operator : rule.operator,
                    value    : JSON.parse(rule.value)
            ]
        }

        for (def i = 0; i < filters.size(); i++) {
            def service = filters[i].service as FilterServiceBase
            if (!service.check(backTest.symbol, filters[i].parameter?.toString(), filters[i].operator?.toString(), filters[i].value, backTest.currentDate))
                return false
        }

        true
    }

    void fillSignalCommonFields(BackTestSignal signal, BackTest backTest, SymbolAdjustedDailyTrade dailyTrade) {
        signal.backTest = backTest
        signal.symbol = backTest.symbol
        signal.date = backTest.currentDate
        signal.closingPrice = dailyTrade.closingPrice
        signal.firstTradePrice = dailyTrade.firstTradePrice
        signal.lastTradePrice = dailyTrade.lastTradePrice
        signal.maxPrice = dailyTrade.maxPrice
        signal.minPrice = dailyTrade.minPrice
        signal.price = dailyTrade.closingPrice
        signal.priceChange = dailyTrade.priceChange
        signal.totalTradeCount = dailyTrade.totalTradeCount
        signal.totalTradeValue = dailyTrade.totalTradeValue
        signal.totalTradeVolume = dailyTrade.totalTradeVolume
        signal.yesterdayPrice = dailyTrade.yesterdayPrice

        signal.indicators = extractIndicators(backTest)
    }

    void updatePortfolioLog(BackTest backTest, PortfolioLog previousLog, SymbolAdjustedDailyTrade dailyTrade, BackTestSignal signal = null) {
        def portfolioLog = new PortfolioLog()
        portfolioLog.backTest = backTest
        portfolioLog.date = backTest.currentDate
        portfolioLog.price = dailyTrade.closingPrice
        if (signal) {
            if (signal instanceof BuySignal) {
                portfolioLog.remainingOutlay = previousLog.remainingOutlay - signal.price * signal.stockCount - signal.wage - signal.tax
                portfolioLog.stockCount = previousLog.stockCount + signal.stockCount
            } else { //sell signal
                portfolioLog.remainingOutlay = previousLog.remainingOutlay + signal.price * signal.stockCount - signal.wage - signal.tax
                portfolioLog.stockCount = 0
            }
        } else {
            portfolioLog.remainingOutlay = previousLog.remainingOutlay
            portfolioLog.stockCount = previousLog.stockCount
        }
        portfolioLog.save(flush: true)
    }

    def extractIndicators(BackTest backTest) {
        def rules = Rule.findAllByParentInList([backTest.buyRule, backTest.sellRule])
        def indicatorList = []
        rules.each { rule ->
            def indicatorName = rule.field.replace('.filters.', '.indicators.').replace('FilterService', '')
            if (ClassResolver.serviceExists(indicatorName + "Service"))
                if (!indicatorList.any { it.name == indicatorName && it.parameter == rule.inputType })
                    indicatorList << [name: indicatorName, parameter: rule.inputType]

            def value = JSON.parse(rule.value)?.first()
            if (value instanceof JSONArray) {
                indicatorName = value?.first()?.replace('.filters.', '.indicators.')?.replace('FilterService', '')
                if (ClassResolver.serviceExists(indicatorName + "Service"))
                    if (!indicatorList.any { it.name == indicatorName && it.parameter == value?.last() })
                        indicatorList << [name: indicatorName, parameter: value?.last()]
            }
        }
        indicatorList.each { indicator ->
            indicator.value = ClassResolver.loadDomainClassByName(indicator.name?.toString()).createCriteria().list {
                eq('symbol', backTest.symbol)
                eq('parameter', indicator.parameter)
                lte('calculationDate', backTest.currentDate)
                order('calculationDate', ORDER_DESCENDING)
                maxResults(1)
                projections {
                    property('value')
                }
            }?.find()
        }
        indicatorList
    }
}
