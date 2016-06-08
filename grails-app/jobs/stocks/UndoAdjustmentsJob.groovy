package stocks

import grails.converters.JSON
import stocks.tse.AdjustmentHelper
import stocks.tse.Symbol


class UndoAdjustmentsJob {
//
    static startDelay = 50000
    static timeout = 100l
    static concurrent = false

    def priceSeriesAdjustmentService

    def execute() {

        def symbol = findNextSymbol(getLastState())
        if(symbol) {
            log.error "undo adjustment: ${symbol.shortCode}"
            priceSeriesAdjustmentService.undo(AdjustmentHelper.TYPE_CAPITAL_INCREASE_PLUS_BROUGHT, [symbol?.id])
            logState(symbol?.id)
        }
        else{
            smsService.sendCustomMessage('09122110811', 'bulk indicator calculation completed')
            log.error "no adjustment to undo"
            logState(0)
        }
    }


    def findNextSymbol(Long minId) {
        Symbol.executeQuery("from Symbol s where exists (from SymbolDailyTrade t where t.symbol.id = s.id) and s.id > :id", [id: minId, max: 1]).find()
    }

    def logState(Long lastId) {
        def data = [lastId: lastId]
        def serviceName = 'UndoAdjustments'
        DataServiceState.executeUpdate("update DataServiceState s set s.isLastState = false where s.serviceName = :serviceName", [serviceName: serviceName])

        DataServiceState state = new DataServiceState()
        state.serviceName = serviceName
        state.data = data as JSON
        state.save(flush: true)
    }

    Long getLastState() {
        def serviceName = 'UndoAdjustments'
        def data = DataServiceState.findByServiceNameAndIsLastState(serviceName, true)?.data
        data ? JSON.parse(data)?.lastId ?: 0 : 0
    }
}
