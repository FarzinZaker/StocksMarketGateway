package stocks


class HeatMapJob {

    def offlineChartService

    static startDelay = 60000
    static timeout = 5000l
    static concurrent = false


    def execute() {
        offlineChartService.heatMap()
    }
}
