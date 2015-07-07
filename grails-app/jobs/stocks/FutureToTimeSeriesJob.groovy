package stocks

import grails.converters.JSON
import grails.util.Environment
import stocks.rate.CoinFuture
import stocks.rate.event.CoinFutureEvent
import stocks.tse.IndexHistory


class FutureToTimeSeriesJob {

    static startDelay = 60000
    static timeout = 100l
    static concurrent = false

    def futureSeriesService
    def grailsApplication

    def execute() {

        if (grailsApplication.config.jobsDisabled)
            return

        if (Environment.current == Environment.DEVELOPMENT)
            return

        def lastState = getLastState()
        def count =
                CoinFutureEvent.createCriteria().count {
                    gt('id', lastState)
                }
        if (count > 0)
            println "remaining futures: ${count}"


        def list = CoinFutureEvent.createCriteria().list {
            gt('id', lastState)
            order('id', ORDER_ASCENDING)
            maxResults(1000)
        }
        if (list.size()) {
            futureSeriesService.write(list)
            logState(list.collect { it.id }.max())
        }
//        else
//            println "no future to import to time series"
    }

    def logState(Long lastId) {
        def data = [lastId: lastId]
        def serviceName = 'FutureToTimeSeries'
        DataServiceState.executeUpdate("update DataServiceState s set s.isLastState = false where s.serviceName = :serviceName", [serviceName: serviceName])

        DataServiceState state = new DataServiceState()
        state.serviceName = serviceName
        state.data = data as JSON
        state.save(flush: true)
    }

    Long getLastState() {
        def serviceName = 'FutureToTimeSeries'
        def data = DataServiceState.findByServiceNameAndIsLastState(serviceName, true)?.data
        data ? JSON.parse(data)?.lastId ?: 0 : 0
    }
}
