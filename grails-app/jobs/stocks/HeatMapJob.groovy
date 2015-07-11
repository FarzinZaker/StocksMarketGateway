package stocks

import grails.util.Environment


class HeatMapJob {

    def offlineChartService
    def grailsApplication

    static startDelay = 60000
    static timeout = 60000l
    static concurrent = false


    def execute() {

        if (grailsApplication.config.jobsDisabled)
            return

        if (Environment.current == Environment.DEVELOPMENT)
            return
        offlineChartService.heatMap()
    }
}
