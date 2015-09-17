package stocks


class FeedJob {

    static startDelay = 60000
    def timeout = 60000l
    static concurrent = false

    def externalNewsService
    def grailsApplication

    def execute() {

        if (grailsApplication.config.jobsDisabled)
            return

        externalNewsService.refresh()
    }
}
