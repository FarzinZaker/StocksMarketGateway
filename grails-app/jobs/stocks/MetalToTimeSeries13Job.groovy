package stocks

import grails.converters.JSON
import grails.util.Environment
import stocks.rate.event.MetalEvent


class MetalToTimeSeries13Job {

    static startDelay = 60000
    static timeout = 100l
    static concurrent = false

    def metalSeries9Service
    def grailsApplication

    def execute() {

        return

        if (grailsApplication.config.jobsDisabled)
            return

        if (Environment.current == Environment.DEVELOPMENT)
            return

        def lastState = getLastState()
        if(lastState >= getMaxState())
            return

        def count =
                MetalEvent.createCriteria().count {
                    gt('id', lastState)
                }
        if (count > 0)
            log.error "[13] remaining metals: ${count}"


        def list = MetalEvent.createCriteria().list {
            gt('id', lastState)
            order('id', ORDER_ASCENDING)
            maxResults(1000)
        }
        if (list.size()) {
            metalSeries9Service.write(list, true)
            logState(list.collect { it.id }.max())
        }
//        else
//            log.error "[13] no metal to import to time series"
    }

    def logState(Long lastId) {
        def data = [lastId: lastId]
        def serviceName = 'MetalToTimeSeries13'
        DataServiceState.executeUpdate("update DataServiceState s set s.isLastState = false where s.serviceName = :serviceName", [serviceName: serviceName])

        DataServiceState state = new DataServiceState()
        state.serviceName = serviceName
        state.data = data as JSON
        state.save(flush: true)
    }

    Long getLastState() {
        def serviceName = 'MetalToTimeSeries13'
        def data = DataServiceState.findByServiceNameAndIsLastState(serviceName, true)?.data
        data ? JSON.parse(data)?.lastId ?: 0 : 0
    }

    Long getMaxState() {
        def serviceName = 'MetalToTimeSeries92'
        def data = DataServiceState.findByServiceNameAndIsLastState(serviceName, true)?.data
        data ? JSON.parse(data)?.lastId ?: 0 : 0
    }
}
