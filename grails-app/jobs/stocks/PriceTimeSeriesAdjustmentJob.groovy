package stocks

import grails.converters.JSON
import grails.util.Environment
import stocks.tse.AdjustmentHelper
import stocks.tse.SymbolDailyTrade


class PriceTimeSeriesAdjustmentJob {

    static startDelay = 60000
    static timeout = 100l
    static concurrent = false

    def priceSeriesAdjustmentService
    def grailsApplication
    def smsService


    def execute() {

        if (grailsApplication.config.jobsDisabled)
            return

        if (Environment.current == Environment.DEVELOPMENT)
            return

        def lastState = getLastState()

        def list = SymbolDailyTrade.createCriteria().list {
            symbol {
                gt('id', lastState)
            }
            not {
                like('symbolPersianCode', '%تسه')
            }
            projections {
                symbol {
                    distinct('id')
                    order('id', ORDER_ASCENDING)
                }
            }
        }
        if (list && list?.size() >= 1) {
            log.error "[9] remaining adjustments: ${list.size()}"
            def id = list && list.size() ? list.first() : null
            priceSeriesAdjustmentService.apply(AdjustmentHelper.TYPE_CAPITAL_INCREASE_PLUS_BROUGHT, [id])
            logState(id as Long)
        } else {
            smsService.sendCustomMessage('09122110811', 'adjustment cycle completed')
            log.error "[9] no adjustment"
            logState(0)
        }

    }

    def logState(Long lastId) {
        def data = [lastId: lastId]
        def serviceName = 'PriceTimeSeriesAdjustment9'
        DataServiceState.executeUpdate("update DataServiceState s set s.isLastState = false where s.serviceName = :serviceName", [serviceName: serviceName])

        DataServiceState state = new DataServiceState()
        state.serviceName = serviceName
        state.data = data as JSON
        state.save(flush: true)
    }

    Long getLastState() {
        def serviceName = 'PriceTimeSeriesAdjustment9'
        def data = DataServiceState.findByServiceNameAndIsLastState(serviceName, true)?.data
        data ? JSON.parse(data)?.lastId ?: 0 : 0
    }

}
