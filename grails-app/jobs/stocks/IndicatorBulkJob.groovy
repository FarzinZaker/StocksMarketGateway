package stocks

import grails.converters.JSON
import stocks.tse.Symbol


class IndicatorBulkJob {
//
    static startDelay = 50000
    static timeout = 100l
    static concurrent = false

    def symbolIndicatorBulkService
    def grailsApplication
    def lowLevelDataService
    def smsService

    def execute() {

        if (grailsApplication.config.jobsDisabled)
            return

        def symbol = findNextSymbol(getLastState())
        if(symbol) {
            log.error "[9] bulk indicator calculate: ${symbol.shortCode}"
            symbolIndicatorBulkService.bulkCalculateIndicator(symbol)
            logState(symbol?.id)
        }
        else{
            smsService.sendCustomMessage('09122110811', 'bulk indicator calculation completed')
            log.error "[9] no adjustment"
            logState(0)
        }
    }


    def findNextSymbol(Long minId) {
        Symbol.executeQuery("from Symbol s where exists (from SymbolDailyTrade t where t.symbol.id = s.id) and s.id > :id", [id: minId, max: 1]).find()
    }

    def logState(Long symbolId) {
        def data = [symbolId: symbolId]
        def serviceName = 'indicator-bulk9'
        DataServiceState.executeUpdate("update DataServiceState s set s.isLastState = false where s.serviceName = :serviceName", [serviceName: serviceName])

        DataServiceState state = new DataServiceState()
        state.serviceName = serviceName
        state.data = data as JSON
        state.save(flush: true)
    }

    Long getLastState() {
        def serviceName = 'indicator-bulk9'
        def data = DataServiceState.findByServiceNameAndIsLastState(serviceName, true)?.data
        data ? JSON.parse(data)?.symbolId ?: 0 : 0
    }
}
