package stocks

import grails.converters.JSON
import grails.util.Environment
import stocks.tse.AdjustmentHelper
import stocks.tse.SymbolDailyTrade


class DailyTradesToTimeSeries13Job {

    static startDelay = 60000
    static timeout = 100l
    static concurrent = false

    def adjustedPriceSeries9Service
    def grailsApplication

    def execute() {

        if (grailsApplication.config.jobsDisabled)
            return

        if (Environment.current == Environment.DEVELOPMENT)
            return

        def lastState = getLastState()
        def count = SymbolDailyTrade.createCriteria().count {
            lt('id', lastState)
        }
        if (count > 1)
            log.error "[13] remaining daily trades: ${count}"


        def list = SymbolDailyTrade.createCriteria().list {
            lt('id', lastState)
            order('id', ORDER_DESCENDING)
            maxResults(1000)
        }
        if (list.size()) {
            adjustedPriceSeries9Service.write(list, AdjustmentHelper.TYPES, true)
            logState(list.collect { it.id }.min())
        }
//        else
//            log.error "[9] no daily trade to import to time series"
    }

    def logState(Long lastId) {
        def data = [lastId: lastId]
        def serviceName = 'DailyTradesToTimeSeries13'
        DataServiceState.executeUpdate("update DataServiceState s set s.isLastState = false where s.serviceName = :serviceName", [serviceName: serviceName])

        DataServiceState state = new DataServiceState()
        state.serviceName = serviceName
        state.data = data as JSON
        state.save(flush: true)
    }

    Long getLastState() {
        def serviceName = 'DailyTradesToTimeSeries13'
        def data = DataServiceState.findByServiceNameAndIsLastState(serviceName, true)?.data
        def startData = DataServiceState.findByServiceNameAndIsLastState('DailyTradesToTimeSeries9', true)?.data
        data ? (JSON.parse(data)?.lastId ?: (startData ? (JSON.parse(startData)?.lastId ?: 0) : 0)) : (startData ? (JSON.parse(startData)?.lastId ?: 0) : 0)
    }
}
