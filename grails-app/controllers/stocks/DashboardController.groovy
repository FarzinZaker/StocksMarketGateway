package stocks

import grails.converters.JSON

class DashboardController {

    def externalNewsService
    def externalAnalysisService
    def dashboardService
    def materialGraphService
    def groupGraphService
    def sharingService
    def propertyGraphService
    def rateGraphService

    def index() {
        [
                tops   : sharingService.tops(),
                symbols: dashboardService.selectedSymbols()
        ]
    }

    def marketView() {
        render(dashboardService.marketView() as JSON)
    }

    def twits() {
        render(materialGraphService.twitList(params.id as String) as JSON)
    }

    def news() {
        render(externalNewsService.newsList(params.id) as JSON)
    }

    def analysis() {
        render(externalAnalysisService.analysisList(params.id) as JSON)
    }

    def announcements() {
        render(dashboardService.announcements() as JSON)
    }

    def rates() {
        render(dashboardService.rates() as JSON)
    }

    def selectedSymbols() {
        render(template: 'dashLets/selectedSymbols', model: [symbols: dashboardService.selectedSymbols()])
    }

    def mostActivePersons() {
        [
                list: materialGraphService.mostActiveUsers(1, 10)
        ]
    }

    def mostRatedPersons() {
        [
                list: rateGraphService.mostRatedPersons(30, 10)
        ]
    }

    def mostActiveProperties() {
        [
                list: propertyGraphService.mostActiveProperties(7, 10)
        ]
    }

    def largestGroups(){
        [
                list: groupGraphService.largestGroups(10)
        ]
    }
}
