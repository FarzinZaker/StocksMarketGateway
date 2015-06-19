package stocks

import grails.converters.JSON
import stocks.tse.SymbolDailyTrade


class DailyTradesToTimeSeriesJob {

    static startDelay = 60000
    static timeout = 100l
    static concurrent = false

    def adjustedPriceSeriesService

    def execute() {
//        return

        def lastState = getLastState()
        println """remaining items: ${
            SymbolDailyTrade.createCriteria().count {
                gt('id', lastState)
            }
        }"""


        def list = SymbolDailyTrade.createCriteria().list {
            gt('id', lastState)
            order('id', ORDER_ASCENDING)
            maxResults(1000)
        }
        if (list.size()) {
            adjustedPriceSeriesService.write(list)
            logState(list.collect { it.id }.max())
        } else
            println "no daily trade to import to time series"
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
