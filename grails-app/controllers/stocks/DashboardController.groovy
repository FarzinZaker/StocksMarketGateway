package stocks

import grails.converters.JSON

class DashboardController {

    def externalNewsService
    def externalAnalysisService
    def dashboardService

    def index() {
    }

    def marketView() {
        render(dashboardService.marketView() as JSON)
    }

    def news() {
        render(externalNewsService.newsList() as JSON)
    }

    def analysis() {
        render(externalAnalysisService.analysisList() as JSON)
    }

    def announcements() {
        render(dashboardService.announcements() as JSON)
    }

    def rates() {
        render(dashboardService.rates() as JSON)
    }
}
