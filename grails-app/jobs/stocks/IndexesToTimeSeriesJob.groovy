package stocks

import grails.converters.JSON
import stocks.tse.IndexHistory


class IndexesToTimeSeriesJob {

    static startDelay = 60000
    static timeout = 100l
    static concurrent = false

    def indexSeriesService

    def execute() {

        def lastState = getLastState()
        println """remaining items: ${
            IndexHistory.createCriteria().count {
                gt('id', lastState)
            }
        }"""


        def list = IndexHistory.createCriteria().list {
            gt('id', lastState)
            order('id', ORDER_ASCENDING)
            maxResults(1000)
        }
        if (list.size()) {
            indexSeriesService.write(list)
            logState(list.collect { it.id }.max())
        } else
            println "no index to import to time series"
    }

    def logState(Long lastId) {
        def data = [lastId: lastId]
        def serviceName = 'IndexesToTimeSeries'
        DataServiceState.executeUpdate("update DataServiceState s set s.isLastState = false where s.serviceName = :serviceName", [serviceName: serviceName])

        DataServiceState state = new DataServiceState()
        state.serviceName = serviceName
        state.data = data as JSON
        state.save(flush: true)
    }

    Long getLastState() {
        def serviceName = 'IndexesToTimeSeries'
        def data = DataServiceState.findByServiceNameAndIsLastState(serviceName, true)?.data
        data ? JSON.parse(data)?.lastId ?: 0 : 0
    }
}
