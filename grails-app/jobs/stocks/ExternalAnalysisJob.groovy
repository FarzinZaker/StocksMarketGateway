package stocks


class ExternalAnalysisJob {

    static startDelay = 60000
    def timeout = 600000l
    static concurrent = false

    def externalAnalysisService
    def grailsApplication

    def execute() {

        if (grailsApplication.config.jobsDisabled)
            return

        externalAnalysisService.refresh()
    }
}
