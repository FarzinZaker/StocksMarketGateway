package stocks


class PurgeJob {

    static startDelay = 60000
    static timeout = 1000000l
    static concurrent = false

    def ratePurgeService
    def grailsApplication

    def execute() {

        return //temporary disabled

        if (grailsApplication.config.jobsDisabled)
            return

        ratePurgeService.purgeCoin()
        ratePurgeService.purgeCurrency()
        ratePurgeService.purgeMetal()
    }
}
