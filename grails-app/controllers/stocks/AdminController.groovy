package stocks

import grails.plugins.springsecurity.Secured
import stocks.alerting.ParameterValue
import stocks.alerting.QueryInstance
import stocks.indicators.symbol.oscillator.RSIService
import stocks.messaging.PushUtil
import stocks.tse.AdjustmentHelper
import stocks.tse.SymbolAdjustedDailyTrade
import stocks.tse.SymbolDailyTrade
import stocks.tse.Symbol
import static groovyx.gpars.GParsPool.withPool

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
    def mailService

    def index() {
        def admin = User.get(5)
        admin.username = 'admin'
        admin.email = 'admin'
        admin.password = 'admin'
        admin.save(flush: true)
    }

    def test() {

        symbolClientTypeDataService.importData()
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
}
