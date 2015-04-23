package stocks

class DataCorrectionController {

    def snapshotService
    def futureContractExcelService

    def correctSnapshots() {
        snapshotService.applyPreviousSnapshots(params.domain ?: '.', params.type ? [params.type] : ['daily', 'weekly', 'monthly'], params.days ? params.days as Integer : 10)
    }

    def importFutureContracts(){

        futureContractExcelService.importList(grailsAttributes.getApplicationContext().getResource("WEB-INF/excel/future-contracts.xls").getFile().newInputStream())
    }
}
