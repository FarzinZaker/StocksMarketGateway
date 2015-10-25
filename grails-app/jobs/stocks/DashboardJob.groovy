package stocks

import grails.util.Environment


class DashboardJob {

    static startDelay = 60000
    static timeout = 5000l
    static concurrent = false

    def dashboardService
    def execute() {

        if(Environment.isDevelopmentMode())
            return

        dashboardService.rates()
        dashboardService.announcements()
        dashboardService.marketView()
    }
}
