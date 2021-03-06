package stocks

import grails.util.Environment
import org.apache.juli.logging.Log
import stocks.portfolio.PortfolioReport


class PortfolioReportJob {

    static startDelay = 60000
    static timeout = 100l
    static concurrent = false

    def portfolioReportService
    def grailsApplication

    def execute() {

        if (grailsApplication.config.jobsDisabled)
            return

        if (Environment.current == Environment.DEVELOPMENT)
            return

        def count = PortfolioReport.countByIsUpdated(false)
        if(count > 0) {
            log.error("Remaining Portfolio Reports: ${count}")

            def report = PortfolioReport.createCriteria().list {
                ne('isUpdated', true)
                order('lastUpdated', ORDER_ASCENDING)
                maxResults(1)
            }?.find()
            if (report)
                portfolioReportService.updateReport(report)
        }
    }
}
