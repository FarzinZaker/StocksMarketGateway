package stocks

import grails.converters.JSON
import grails.util.Environment
import stocks.tse.AdjustmentHelper
import stocks.tse.SymbolDailyTrade


class DailyTradesToTimeSeriesJob {

    static startDelay = 60000
    static timeout = 100l
    static concurrent = false

    def adjustedPriceSeriesService
    def grailsApplication

    def execute() {

        if (grailsApplication.config.jobsDisabled)
            return

        if (Environment.current == Environment.DEVELOPMENT)
            return

        def lastState = getLastState()
        def count = SymbolDailyTrade.createCriteria().count {
            gt('id', lastState)
        }
        if (count > 1)
            println "remaining daily trades: ${count}"


        def list = SymbolDailyTrade.createCriteria().list {
            gt('id', lastState)
            order('id', ORDER_ASCENDING)
            maxResults(1000)
        }
        if (list.size()) {
            adjustedPriceSeriesService.write(list, AdjustmentHelper.TYPES)
            logState(list.collect { it.id }.max())
        }
//        else
//            println "no daily trade to import to time series"
    }

    def logState(Long lastId) {
        def data = [lastId: lastId]
        def serviceName = 'DailyTradesToTimeSeries'
        DataServiceState.executeUpdate("update DataServiceState s set s.isLastState = false where s.serviceName = :serviceName", [serviceName: serviceName])

        DataServiceState state = new DataServiceState()
        state.serviceName = serviceName
        state.data = data as JSON
        state.save(flush: true)
    }

    Long getLastState() {
        def serviceName = 'DailyTradesToTimeSeries'
        def data = DataServiceState.findByServiceNameAndIsLastState(serviceName, true)?.data
        data ? JSON.parse(data)?.lastId ?: 0 : 0
    }
}
