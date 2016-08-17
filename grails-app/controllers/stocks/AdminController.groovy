package stocks

import stocks.alerting.QueryInstance
import stocks.analysis.BackTest
import stocks.analysis.BackTestHelper
import stocks.tse.IndustryGroup
import stocks.tse.Symbol
import stocks.feed.ExternalNews
import stocks.feed.ExternalAnalysis
import stocks.tse.SymbolClientType
import stocks.tse.data.SymbolClientTypeDataService
import stocks.twitter.Search.*

//@Secured([RoleHelper.ROLE_ADMIN])
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
    def commodityMarketActivityDataService
    def energyMarketValueDataService
    def oilDataService
    def symbolClientTypeDataService

    def initDBService

    def migrationService

    def adjustedPriceSeries9Service

    def priceSeriesAdjustmentService
    def RSIService

    def externalNewsService
    def externalAnalysisService
    def marketValueDataService

    def groupGraphService

    def sharingService
    def springSecurityService
    def backTestService

    def symbolAverageTradeDataService

    def telegramService

    def index() {
//        def admin = User.get(5)
//        admin.username = 'admin'
//        admin.email = 'admin'
//        admin.password = 'admin'
//        admin.save(flush: true)
    }

    def suggestTest() {
    }

    def test() {

//        symbolClientTypeDataService.importData()
//        render externalAnalysisService.refresh()
//        render oilDataService.importData()

//        announcementDataService.importData()

//        commodityMarketActivityDataService.importData()

//        indexDataService.importData(true)
//        symbolDailyTradeDataService.importData(true)
//        commodityMarketActivityDataService.importData(true)
//        marketActivityDataService.importData(true)
//        marketValueDataService.importData(true)
//        energyMarketValueDataService.importData(true)
//        symbolBestOrderDataService.importData(true)
//        symbolClientTypeDataService.importData(true)
//        symbolStateDataService.importData(true)

//        render oilDataService.importData()

//        symbolAverageTradeDataService.importData()
//        telegramService.dispatchUpdates()
//        render 'ok'

//        println 'deserialize important data'
//        render externalNewsService.kurdPress()
//        oilDataService.importData()

//        // execute task
//        stocks.analysis.BackTest.findAllByStatus(BackTestHelper.STATUS_WAITING).each {
//            it.status = BackTestHelper.STATUS_IN_PROGRESS
//            it.save(flush: true)
//        }
//        def waitingBackTests = stocks.analysis.BackTest.findAllByStatus(BackTestHelper.STATUS_IN_PROGRESS)
////        withPool(12)  {
////            waitingBackTests.eachParallel { BackTest backTest ->
//        waitingBackTests.each { BackTest backTest ->
//            BackTest.withTransaction {
//                backTestService.runBackTest(backTest)
//            }
//        }
//        }
//        Symbol.list().each {symbol ->
//            symbol.industryGroup = IndustryGroup.findByCode(symbol.industryGroupCode)
//            println(symbol.industryGroup)
//            symbol.save(flush: true)
//        }
//        announcementDataService.importData()
//        migrationService.testAuthorization()
        symbolDataService.importData()
    }


    def throwException() {
        throw new Exception('test exception')
    }

    def scheduleItem() {
        scheduleService.calculateQueryInstanceNextExecutionTime(QueryInstance.get(params.id))
    }

    def reindexAll() {
        searchableService.reindexAll()
        Symbol.reindexAll()
        render 'done'
    }

    def indexAll() {
        searchableService.indexAll()
        render 'done'
    }

    def reindexNews() {
        ExternalNews.reindexAll()
        render 'done'
    }

    def reindexTwitter() {
        TwitterGroup.reindexAll()
        TwitterPerson.reindexAll()
        TwitterProperty.reindexAll()
        TwitterMaterial.reindexAll()
        render 'done'
    }

    def reindexAnalysis() {
        ExternalAnalysis.reindexAll()
        render 'done'
    }

}
