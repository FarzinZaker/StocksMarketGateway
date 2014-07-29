package stocks

class AdminController {

    def companyService
    def boardService
    def industryGroupService
    def industryGroupStateService
    def industrySubgroupService
    def supervisorMessageService
    def symbolService
    def symbolStateService
    def symbolBestRequestService
    def symbolBestRequestManualService
    def symbolDailyTradeService
    def symbolDailyTradeManualService
    def marketActivityService

    def index() {
    }

    def test(){
//        println boardService.importData()
//        println companyService.importData()
//        println industryGroupService.importData()
//        println industryGroupStateService.importData()
//        println industrySubgroupService.importData()
//        println supervisorMessageService.importData()
//        println symbolService.importData()
//        println symbolStateService.importData()
//        println symbolBestRequestService.importData()
//        println symbolBestRequestService.importData(65122215875355555)
//        println symbolDailyTradeService.importData()
//        println symbolDailyTradeService.importData((new Date() - 1), 1)
        println symbolDailyTradeService.importData(65122215875355555, (new Date() - 5), new Date())
//        println marketActivityService.importData()
    }
}
