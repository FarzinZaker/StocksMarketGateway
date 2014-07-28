package stocks

import stocks.tse.SymbolDailyTradeAutoImportService
import stocks.tse.SymbolDailyTradeManualImportService

class AdminController {

    def companyAutoImportService
    def boardAutoImportService
    def industryGroupAutoImportService
    def industryGroupStateAutoImportService
    def industrySubgroupAutoImportService
    def supervisorMessageAutoImportService
    def symbolAutoImportService
    def symbolStateAutoImportService
    def symbolBestRequestAutoImportService
    def symbolBestRequestManualImportService
    def symbolDailyTradeAutoImportService
    def symbolDailyTradeManualImportService
    def marketActivityAutoImportService

    def index() {
    }

    def test(){
//        boardService.execute()
//        companyService.execute()
//        industryGroupService.execute()
//        industryGroupStateService.execute()
//        industrySubgroupService.execute()
//        supervisorMessageService.execute()
//        symbolService.execute()
//        symbolStateService.execute()
//        symbolBestRequestAutoImportService.execute()
//        symbolBestRequestManualImportService.execute(65122215875355555)
//        2ymbolDailyTradeAutoImportService.execute()
//        symbolDailyTradeManualImportService.execute((new Date() - 1), 1)
        marketActivityAutoImportService.execute()
    }
}
