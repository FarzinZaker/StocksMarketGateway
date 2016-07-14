package stocks

import grails.converters.JSON
import grails.util.Environment
import stocks.rate.event.OilEvent


class OilToTimeSeries13Job {

    static startDelay = 60000
    static timeout = 100l
    static concurrent = false

    def oilSeries9Service
    def grailsApplication

    def execute() {

        if (grailsApplication.config.jobsDisabled)
            return

        if (Environment.current == Environment.DEVELOPMENT)
            return

        def lastState = getLastState()
        def count =
                OilEvent.createCriteria().count {
                    lt('id', lastState)
                }
        if (count > 0)
            log.error "[13] remaining oils: ${count}"


        def list = OilEvent.createCriteria().list {
            lt('id', lastState)
            order('id', ORDER_DESCENDING)
            maxResults(1000)
        }
        if (list.size()) {
            oilSeries9Service.write(list, true)
            logState(list.collect { it.id }.min())
        }
//        else
//            log.error "[13] no oil to import to time series"
    }

    def logState(Long lastId) {
        def data = [lastId: lastId]
        def serviceName = 'OilToTimeSeries13'
        DataServiceState.executeUpdate("update DataServiceState s set s.isLastState = false where s.serviceName = :serviceName", [serviceName: serviceName])

        DataServiceState state = new DataServiceState()
        state.serviceName = serviceName
        state.data = data as JSON
        state.save(flush: true)
    }

    Long getLastState() {
        def serviceName = 'OilToTimeSeries13'
        def data = DataServiceState.findByServiceNameAndIsLastState(serviceName, true)?.data
        def startData = DataServiceState.findByServiceNameAndIsLastState('OilToTimeSeries92', true)?.data
        data ? (JSON.parse(data)?.lastId ?: (startData ? (JSON.parse(startData)?.lastId ?: 0) : 0)) : (startData ? (JSON.parse(startData)?.lastId ?: 0) : 0)
    }
}
