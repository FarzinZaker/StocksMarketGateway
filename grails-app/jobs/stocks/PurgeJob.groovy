package stocks


class PurgeJob {
    static startDelay = 60000
    static timeout = 1000000l
    static concurrent = false

    def ratePurgeService

    def execute() {
        ratePurgeService.purgeCoin()
        ratePurgeService.purgeCurrency()
        ratePurgeService.purgeMetal()
    }
}
