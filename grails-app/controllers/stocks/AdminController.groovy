package stocks

import grails.plugins.springsecurity.Secured
import stocks.alerting.ParameterValue
import stocks.alerting.QueryInstance
import stocks.tse.SymbolAdjustedDailyTrade
import stocks.tse.SymbolDailyTrade
import stocks.tse.Symbol
import static groovyx.gpars.GParsPool.withPool

@Secured([RoleHelper.ROLE_ADMIN])
class AdminController {

    def companyDataService
    def boardDataService
    def industryGroupDataService
    def industryGroupStateDataService
    def industrySubgroupDataService
    def supervisorMessageDataService
    def symbolDataService
    def symbolStateDataService
    def symbolBestOrderDataService
    def symbolDailyTradeDataService
    def marketActivityDataService
    def futureDataService
    def announcementDataService
    def indexDataService
    def indexSymbolDataService
    def dataService
    def symbolDailyTradeMissingDataService


    def queryService
    def smsService
    def scheduleService

    def tradeStatisticsDataService

    def coinDataService
    def metalDataService
    def coinFutureDataService
    def currencyDataService

    def symbolIndicatorService

    def searchableService

    def ratePurgeService

    def SMAService

    def timeSeriesDBService
    def adjustedPriceSeriesService

    def index() {
    }

    def test() {
//        boardDataService.importData()
//        companyDataService.importData()
//        industryGroupDataService.importData()
//        industryGroupStateDataService.importData()
//        industrySubgroupDataService.importData()
//        supervisorMessageDataService.importData()
//        symbolDataService.importData()
//        symbolStateDataService.importData()
//        symbolBestOrderDataService.importData()
//        symbolBestOrderDataService.importData(65122215875355555)
//        symbolDailyTradeDataService.importData()
//        symbolDailyTradeDataService.importData((new Date() - 1), 1)
//        symbolDailyTradeDataService.importData(65122215875355555, (new Date() - 5), new Date())
//        marketActivityDataService.importData()
//        futureDataService.importData(new Date() - 2)
//        announcementDataService.importData()
//        indexDataService.importData()
//        indexSymbolDataService.importData()
//        def queryInstance = QueryInstance.get(14)
//        render smsService.sendEventBasedMessage(queryInstance, queryService.get(queryInstance))
//        dataService.initializeJobs()
//        render dataService.printJobList()

//        boardDataService.importData()
//        def test = 'کا'
//        def test2 = FarsiNormalizationFilter.apply(test)
//        println(test.replace(test, FarsiNormalizationFilter.apply(test)) == test2)
//        queryService.getDomainParameterValues(ParameterValue.get(39));
//        currencyDataService.importData()

//        def dailyTrade = SymbolDailyTrade.get(35645) //mellat bank
//        render symbolIndicatorService.calculateIndicators(Symbol.get(dailyTrade.symbolId), dailyTrade.date).replace('\n', '<br/>')

//        symbolDailyTradeMissingDataService.importData()

//        println ratePurgeService.purgeMetal()

//        render SMAService.bulkCalculate(Symbol.get(17853), 7)

//        timeSeriesDBService.query('')

        def list = SymbolDailyTrade.list(max: 1)
        adjustedPriceSeriesService.write(list)

//        render timeSeriesDBService.query('select * from /.*/')

//        println 'finished'
    }

    def throwException() {
        throw new Exception('test exception')
    }

    def scheduleItem() {
        scheduleService.calculateQueryInstanceNextExecutionTime(QueryInstance.get(params.id))
    }

    def reindexAll() {
        searchableService.reindexAll()
        render 'done'
    }

    def indexAll() {
        searchableService.indexAll()
        render 'done'
    }
}
