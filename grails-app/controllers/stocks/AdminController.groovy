package stocks

import grails.plugins.springsecurity.Secured

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

    def queryService
    def smsService

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
        announcementDataService.importData()
//        indexDataService.importData()
//        indexSymbolDataService.importData()
//        def queryInstance = QueryInstance.get(14)
//        render smsService.sendMessage(queryInstance, queryService.get(queryInstance))
    }

    def throwException(){
        throw new Exception('test exception')
    }
}
