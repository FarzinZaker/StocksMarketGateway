package stocks

import grails.converters.JSON
import grails.util.Environment
import stocks.tse.IndexHistory


class IndexesToTimeSeries13Job {

    static startDelay = 60000
    static timeout = 100l
    static concurrent = false

    def indexSeries9Service
    def grailsApplication

    def execute() {

        if (grailsApplication.config.jobsDisabled)
            return

        if (Environment.current == Environment.DEVELOPMENT)
            return

        def lastState = getLastState()
        def count = IndexHistory.createCriteria().count {
            lt('id', lastState)
        }
        if (count > 0)
            log.error "[13] remaining indexes: ${count}"


        def list = IndexHistory.createCriteria().list {
            lt('id', lastState)
            order('id', ORDER_DESCENDING)
            maxResults(1000)
        }
        if (list.size()) {
            indexSeries9Service.write(list, true)
            logState(list.collect { it.id }.min())
        }
//        else
//            log.error "[13] no index to import to time series"
    }

    def logState(Long lastId) {
        def data = [lastId: lastId]
        def serviceName = 'IndexesToTimeSeries13'
        DataServiceState.executeUpdate("update DataServiceState s set s.isLastState = false where s.serviceName = :serviceName", [serviceName: serviceName])

        DataServiceState state = new DataServiceState()
        state.serviceName = serviceName
        state.data = data as JSON
        state.save(flush: true)
    }

    Long getLastState() {
        def serviceName = 'IndexesToTimeSeries13'
        def data = DataServiceState.findByServiceNameAndIsLastState(serviceName, true)?.data
        def startData = DataServiceState.findByServiceNameAndIsLastState('IndexesToTimeSeries92', true)?.data
        data ? (JSON.parse(data)?.lastId ?: (startData ? (JSON.parse(startData)?.lastId ?: 0) : 0)) : (startData ? (JSON.parse(startData)?.lastId ?: 0) : 0)
    }
}
