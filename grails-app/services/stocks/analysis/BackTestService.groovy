package stocks.analysis
import grails.converters.JSON
import groovy.time.TimeCategory
import org.codehaus.groovy.grails.web.json.JSONArray
import stocks.alerting.Rule
import stocks.filters.FilterServiceBase
import stocks.tse.SymbolDailyTrade
import stocks.util.ClassResolver

class BackTestService {

    def springSecurityService
    def priceService

    def startBackTest(BackTest backTest) {
        backTest.status = BackTestHelper.STATUS_IN_PROGRESS
        backTest.save(flush: true)
        while (backTest.status != BackTestHelper.STATUS_FINISHED) {
            stepForwardBackTest(backTest)
        }
    }

    void stepForwardBackTest(BackTest backTest) {
        use(TimeCategory) {
            backTest.currentDate + 1.day
        }

        if (backTest.currentDate >= backTest.endDate) {
            backTest.status = BackTestHelper.STATUS_FINISHED
        } else {
            def dailyTrade = priceService.lastDailyTrade(backTest?.symbol, backTest?.startDate)
            def portfolioLog = getLastPortfolioLog(backTest, dailyTrade.closingPrice)
            def lastSignal = BackTestSignal.createCriteria().list {
                eq('backTest', backTest)
                order('date', ORDER_DESCENDING)
                maxResults(1)
            }?.find()
            if (canBuy(portfolioLog, dailyTrade.closingPrice) && shouldBuy(backTest)) {
                def signal = buy(backTest, portfolioLog, dailyTrade)
                updatePortfolioLog(backTest, portfolioLog, dailyTrade, signal)
            } else if (canSell(portfolioLog)) {
                def limitsResult = shouldSellDueToLimits(backTest, portfolioLog, dailyTrade, lastSignal as BuySignal)
                if (limitsResult || shouldSell(backTest)) {
                    def signal = sell(backTest, portfolioLog, dailyTrade, limitsResult ?: BackTestHelper.REASON_RULES_MATCHED)
                    updatePortfolioLog(backTest, portfolioLog, dailyTrade, signal)
                }
            } else
                updatePortfolioLog(backTest, portfolioLog, dailyTrade)
        }

        backTest.save()
        backTest
    }

    Boolean canBuy(PortfolioLog portfolioLog, Double price) {
        portfolioLog.remainingOutlay >= price
    }

    Boolean shouldBuy(BackTest backTest) {
        checkRules(backTest, Rule.findAllByParent(backTest.buyRule))
    }

    BackTestSignal buy(BackTest backTest, PortfolioLog portfolioLog, SymbolDailyTrade dailyTrade) {
        def signal = new BuySignal()
        fillSignalCommonFields(signal, backTest, dailyTrade)
        signal.count = Math.floor(portfolioLog.remainingOutlay / (dailyTrade.closingPrice * (1 + backTest.buyWage + backTest.buyTax)))
        signal.wage = backTest.buyWage * signal.count * dailyTrade.closingPrice
        signal.tax = backTest.buyTax * signal.count * dailyTrade.closingPrice
        signal.reason = BackTestHelper.REASON_RULES_MATCHED
        signal.save(flush: true)
    }

    Boolean canSell(PortfolioLog portfolioLog) {
        portfolioLog.stockCount > 0
    }

    Boolean shouldSell(BackTest backTest) {
        checkRules(backTest, Rule.findAllByParent(backTest.sellRule))
    }

    String shouldSellDueToLimits(BackTest backTest, PortfolioLog portfolioLog, SymbolDailyTrade dailyTrade, BuySignal lastSignal) {
        if (!lastSignal || lastSignal)
            return null

        def maxAcceptableDate = null
        use(TimeCategory) {
            maxAcceptableDate = backTest.currentDate - backTest.timeLimit.days
        }
        if (maxAcceptableDate >= lastSignal.date)
            return BackTestHelper.REASON_TIME_LIMIT

        def sellValue = portfolioLog.stockCount * dailyTrade.closingPrice -
                backTest.sellWage * portfolioLog.stockCount * dailyTrade.closingPrice -
                backTest.sellTax * portfolioLog.stockCount * dailyTrade.closingPrice

        if (sellValue - lastSignal.totalValue >= backTest.profitLimitValue)
            return BackTestHelper.REASON_PROFIT_LIMIT

        if (lastSignal.totalValue - sellValue >= backTest.lossLimitValue)
            return BackTestHelper.REASON_PROFIT_LIMIT

        null
    }

    BackTestSignal sell(BackTest backTest, PortfolioLog portfolioLog, SymbolDailyTrade dailyTrade, String reason) {
        def signal = new SellSignal()
        fillSignalCommonFields(signal, backTest, dailyTrade)
        signal.count = portfolioLog.stockCount
        signal.wage = backTest.sellWage * signal.count * dailyTrade.closingPrice
        signal.tax = backTest.sellTax * signal.count * dailyTrade.closingPrice
        signal.reason = reason
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

    void fillSignalCommonFields(BackTestSignal signal, BackTest backTest, SymbolDailyTrade dailyTrade) {
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

    void updatePortfolioLog(BackTest backTest, PortfolioLog previousLog, SymbolDailyTrade dailyTrade, BackTestSignal signal = null) {
        def portfolioLog = new PortfolioLog()
        portfolioLog.backTest = backTest
        portfolioLog.date = backTest.currentDate
        portfolioLog.price = dailyTrade.closingPrice
        if (signal) {
            if (signal instanceof BuySignal) {
                portfolioLog.remainingOutlay = previousLog.remainingOutlay - signal.closingPrice * signal.count - signal.wage - signal.tax
                portfolioLog.stockCount = previousLog.stockCount + signal.count
            } else { //sell signal
                portfolioLog.remainingOutlay = previousLog.remainingOutlay + signal.closingPrice * signal.count - signal.wage - signal.tax
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
                indicatorList << [name: indicatorName, parameter: rule.inputType]

            def value = JSON.parse(rule.value)?.first()
            if (value instanceof JSONArray) {
                indicatorName = value?.first()?.replace('.filters.', '.indicators.')?.replace('FilterService', '')
                if (ClassResolver.serviceExists(indicatorName + "Service"))
                    indicatorList << [name: indicatorName, parameter: value?.last()]
            }
        }
        indicatorList.each {
            it.value = ClassResolver.loadDomainClassByName(it.name).list{
                eq('symbol', backTest.symbol)
                eq('parameter', it.parameter)
                lte('calculationDate', backTest.currentDate)
                order('calculationDate', ORDER_DESCENDING)
                maxResults(1)
            }?.find()
        }
        indicatorList
    }
}
