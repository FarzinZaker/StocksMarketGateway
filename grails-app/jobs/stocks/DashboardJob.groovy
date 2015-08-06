package stocks


class DashboardJob {

    static startDelay = 60000
    static timeout = 5000l
    static concurrent = false

    def dashboardService
    def execute() {
        dashboardService.rates()
        dashboardService.announcements()
        dashboardService.marketView()
    }
}
