package stocks

import grails.converters.JSON
import grails.util.Environment
import stocks.rate.event.CoinFutureEvent


class FutureToTimeSeries9Job {

    static startDelay = 60000
    static timeout = 100l
    static concurrent = false

    def futureSeries9Service
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
            log.error "[9] remaining futures: ${count}"


        def list = CoinFutureEvent.createCriteria().list {
            gt('id', lastState)
            order('id', ORDER_ASCENDING)
            maxResults(1000)
        }
        if (list.size()) {
            futureSeries9Service.write(list)
            logState(list.collect { it.id }.max())
        }
//        else
//            log.error "[9] no future to import to time series"
    }

    def logState(Long lastId) {
        def data = [lastId: lastId]
        def serviceName = 'FutureToTimeSeries92'
        DataServiceState.executeUpdate("update DataServiceState s set s.isLastState = false where s.serviceName = :serviceName", [serviceName: serviceName])

        DataServiceState state = new DataServiceState()
        state.serviceName = serviceName
        state.data = data as JSON
        state.save(flush: true)
    }

    Long getLastState() {
        def serviceName = 'FutureToTimeSeries92'
        def data = DataServiceState.findByServiceNameAndIsLastState(serviceName, true)?.data
        data ? JSON.parse(data)?.lastId ?: 0 : 0
    }
}
