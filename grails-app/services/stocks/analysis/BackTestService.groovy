package stocks.analysis

import com.google.common.cache.CacheBuilder
import com.google.common.cache.CacheLoader
import grails.converters.JSON
import groovy.time.TimeCategory
import org.codehaus.groovy.grails.web.json.JSONArray
import org.hibernate.SessionFactory
import stocks.alerting.Rule
import stocks.filters.FilterServiceBase
import stocks.util.ClassResolver

import java.util.concurrent.TimeUnit

class BackTestService {

    static transactional = false

    def springSecurityService
    def adjustedPriceSeries9Service
    def indicatorSeries9Service
    def bulkDataService
    SessionFactory sessionFactory

    def dailyTradesCache
    def indicatorsCache

    public BackTestService() {
        dailyTradesCache = CacheBuilder.newBuilder().expireAfterWrite(10, TimeUnit.MINUTES).maximumSize(10000).build(new CacheLoader() {
            @Override
            Object load(Object o) throws Exception {
                def backTest = o as BackTest
                adjustedPriceSeries9Service.dailyTradeList(backTest.symbolId, backTest.startDate, backTest.endDate, '', backTest.adjustmentType).sort {
                    it.date
                }
            }
        })
        indicatorsCache = CacheBuilder.newBuilder().expireAfterWrite(10, TimeUnit.SECONDS).maximumSize(10000).build(new CacheLoader() {
            @Override
            Object load(Object o) throws Exception {
                def backTest = o as BackTest
                indicatorList(backTest)
            }
        })
    }

    def runBackTest(BackTest backTest) {

        def dailyTrades = dailyTradesCache.get(backTest) as List
        def indicators = indicatorsCache.get(backTest) as List

        10.times {
            if (backTest.status != BackTestHelper.STATUS_FINISHED)
                stepForwardBackTest(backTest, dailyTrades, indicators)
        }
    }

    void stepForwardBackTest(BackTest backTest, List dailyTrades, List indicators) {
        use(TimeCategory) {
            backTest.currentDate = (backTest.currentDate + 1.day).clearTime()
        }

        if (backTest.currentDate > backTest.endDate) {
            backTest.status = BackTestHelper.STATUS_FINISHED
        } else {
            def dailyTrade = dailyTrades.find { it.date.clearTime() == backTest.currentDate.clearTime() }
            if (dailyTrade) {
                def portfolioLog = getLastPortfolioLog(backTest, dailyTrade.closingPrice as Double)
                def lastSignal = BackTestSignal.createCriteria().list {
                    eq('backTest', backTest)
                    order('date', ORDER_DESCENDING)
                    maxResults(1)
                }?.find()
                if (canBuy(backTest, portfolioLog, dailyTrade.closingPrice as Double) && shouldBuy(backTest, dailyTrades, indicators)) {
                    def signal = buy(backTest, portfolioLog, dailyTrade, indicators)
                    updatePortfolioLog(backTest, portfolioLog, dailyTrade, signal)
                } else if (canSell(portfolioLog)) {
                    def signal = null
                    def limitsResult = shouldSellDueToLimits(backTest, portfolioLog, dailyTrade, lastSignal as BuySignal)
                    if (limitsResult || shouldSell(backTest, dailyTrades, indicators)) {
                        signal = sell(backTest, portfolioLog, dailyTrade, limitsResult ?: BackTestHelper.REASON_RULES_MATCHED, indicators)
                    }
                    updatePortfolioLog(backTest, portfolioLog, dailyTrade, signal)
                } else
                    updatePortfolioLog(backTest, portfolioLog, dailyTrade)
            }
        }

        bulkDataService.save(backTest)
//        backTest.save(flush: true)
    }

    Boolean canBuy(BackTest backTest, PortfolioLog portfolioLog, Double price) {
        portfolioLog.remainingOutlay >= price * (1 + backTest.buyWage + backTest.buyTax)
    }

    Boolean shouldBuy(BackTest backTest, List dailyTrades, List indicators) {
        checkRules(backTest, Rule.findAllByParent(backTest.buyRule), dailyTrades, indicators)
    }

    BackTestSignal buy(BackTest backTest, PortfolioLog portfolioLog, dailyTrade, List indicators) {
        def signal = new BuySignal()
        fillSignalCommonFields(signal, backTest, dailyTrade, indicators)
        signal.stockCount = Math.floor(portfolioLog.remainingOutlay / (dailyTrade.closingPrice * (1 + backTest.buyWage + backTest.buyTax)))
        signal.wage = backTest.buyWage * signal.stockCount * dailyTrade.closingPrice
        signal.tax = backTest.buyTax * signal.stockCount * dailyTrade.closingPrice
        signal.reason = BackTestHelper.REASON_RULES_MATCHED
        bulkDataService.save(signal)
        signal
//        signal.save(flush: true)
    }

    Boolean canSell(PortfolioLog portfolioLog) {
        portfolioLog.stockCount > 0
    }

    Boolean shouldSell(BackTest backTest, List dailyTrades, List indicators) {
        checkRules(backTest, Rule.findAllByParent(backTest.sellRule), dailyTrades, indicators)
    }

    String shouldSellDueToLimits(BackTest backTest, PortfolioLog portfolioLog, dailyTrade, BuySignal lastSignal) {
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

            if (backTest.profitLimit && ((sellValue - lastSignal.totalValue) / lastSignal.totalValue) >= backTest.profitLimitValue)
                return BackTestHelper.REASON_PROFIT_LIMIT

            if (backTest.lossLimit && ((lastSignal.totalValue - sellValue) / lastSignal.totalValue) >= backTest.lossLimitValue)
                return BackTestHelper.REASON_LOSS_LIMIT
        }
        null
    }

    BackTestSignal sell(BackTest backTest, PortfolioLog portfolioLog, dailyTrade, String reason, List indicators) {
        def signal = new SellSignal()
        fillSignalCommonFields(signal, backTest, dailyTrade, indicators)
        signal.stockCount = portfolioLog.stockCount
        signal.wage = backTest.sellWage * signal.stockCount * dailyTrade.closingPrice
        signal.tax = backTest.sellTax * signal.stockCount * dailyTrade.closingPrice
        signal.reason = reason

        //calculate effect
        def previousBuySignals = BuySignal.executeQuery("from BuySignal bs where bs.backTest.id = ${backTest.id} and not exists (select id from SellSignal ss where ss.backTest.id = ${backTest.id} and ss.date > bs.date)")
        signal.effect = signal.totalValue - (previousBuySignals?.sum { it.totalValue } as Double) ?: 0

        bulkDataService.save(signal)
        signal
//        signal.save(flush: true)
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
            bulkDataService.save(portfolioLog)
            portfolioLog
//            portfolioLog.save(flush: true)
        }
        portfolioLog
    }

    Boolean checkRules(BackTest backTest, List<Rule> rules, List dailyTrades, List indicators) {

        def filters = rules.collect { rule ->
            [
                    service  : ClassResolver.loadServiceByName(rule.field),
                    parameter: rule.inputType,
                    operator : rule.operator,
                    value    : JSON.parse(rule.value)
            ]
        }

        if (filters.size() == 0)
            return false

        for (def i = 0; i < filters.size(); i++) {
            def service = filters[i].service as FilterServiceBase
            if (!service.check(filters[i].parameter?.toString(), filters[i].operator?.toString(), filters[i].value, backTest.currentDate, dailyTrades, indicators))
                return false
        }

        true
    }

    void fillSignalCommonFields(BackTestSignal signal, BackTest backTest, dailyTrade, List indicators) {
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

        signal.indicators = extractIndicators(backTest, indicators)
    }

    void updatePortfolioLog(BackTest backTest, PortfolioLog previousLog, dailyTrade, BackTestSignal signal = null) {
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
        bulkDataService.save(portfolioLog)
        portfolioLog
//        portfolioLog.save(flush: true)
    }

    def extractIndicators(BackTest backTest, List indicators) {
        def rules = Rule.findAllByParentInList([backTest.buyRule, backTest.sellRule])
        def indicatorList = []
        rules.each { rule ->
            def indicatorName = rule.field.replace('.filters.', '.indicators.').replace('FilterService', '')
            if (ClassResolver.serviceExists(indicatorName + "Service"))
                if (!indicatorList.any { it.name == indicatorName && it.parameter == rule.inputType })
                    indicatorList << [name: indicatorName, parameter: rule.inputType]

            def value = JSON.parse(rule.value)?.sort { -it[0]?.size() }?.first()
            if (value instanceof JSONArray) {
                indicatorName = value?.first()?.replace('.filters.', '.indicators.')?.replace('FilterService', '')
                if (ClassResolver.serviceExists(indicatorName + "Service"))
                    if (!indicatorList.any { it.name == indicatorName && it.parameter == value?.last() })
                        indicatorList << [name: indicatorName, parameter: value?.last()]
            }
        }
        indicatorList.each { indicator ->
            indicator.value = indicators.find {
                it.clazz.canonicalName == indicator.name && it.parameter == indicator.parameter
            }?.values?.find { it.date?.clearTime() == backTest.currentDate?.clearTime() }?.value
        }
        indicatorList

    }

    def indicatorList(BackTest backTest) {
        def indicators = []
        def rules = Rule.findAllByParentInList([backTest.buyRule, backTest.sellRule])
        rules.each { rule ->
            def indicatorName = rule.field.replace('.filters.', '.indicators.').replace('FilterService', '')
            if (ClassResolver.serviceExists(indicatorName + "Service"))
                indicators << [clazz: ClassResolver.loadDomainClassByName(indicatorName), parameter: rule.inputType?.toString()]

            def value = JSON.parse(rule.value)?.sort { -it[0].size() }?.first()
            if (value instanceof JSONArray) {
                indicatorName = value?.first()?.replace('.filters.', '.indicators.')?.replace('FilterService', '')
                if (ClassResolver.serviceExists(indicatorName + "Service"))
                    indicators << [clazz: ClassResolver.loadDomainClassByName(indicatorName), parameter: value?.last()?.toString()]
            }
        }
        indicators = indicators.unique()
        indicators.each { indicator ->
            indicator.values = indicatorSeries9Service.indicatorList(backTest.symbolId, indicator.clazz, indicator.parameter, backTest.startDate, backTest.endDate, '', backTest.adjustmentType).sort {
                it.date
            }
        }

        indicators
    }

    def lastDailyTrade(List dailyTrades, Date date) {
        def lastItem = null
        for (def i = 0; i < dailyTrades.size(); i++)
            if (dailyTrades[i].date > date)
                return lastItem
            else
                lastItem = dailyTrades[i]
    }
}
