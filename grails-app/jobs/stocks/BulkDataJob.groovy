package stocks


class BulkDataJob {
    def timeout = 10000l // execute job once in 5 seconds
    def bulkDataService

    def execute() {
        bulkDataService.release()
    }
}
