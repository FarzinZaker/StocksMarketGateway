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

    def execute() {

        return

        if (grailsApplication.config.jobsDisabled)
            return

        println()
        println "-----------------------------------------"
        println "---- calculating indicators started ----"
        println()
        def symbol = findNextSymbol(getLastState())
        if(symbol) {
            println "--------- ${symbol.persianName} ---------"
            symbolIndicatorBulkService.bulkCalculateIndicator(symbol)
            logState(symbol?.id)
        }
        else{
            println()
            println "---- calculating indicators finished ----"
            println "-----------------------------------------"
            println()
        }
    }


    def findNextSymbol(Long minId) {
        Symbol.executeQuery("from Symbol s where exists (from SymbolDailyTrade t where t.symbol.id = s.id) and s.id > :id", [id: minId, max: 1]).find()
    }

    def logState(Long symbolId) {
        def data = [symbolId: symbolId]
        def serviceName = 'indicator-bulk'
        DataServiceState.executeUpdate("update DataServiceState s set s.isLastState = false where s.serviceName = :serviceName", [serviceName: serviceName])

        DataServiceState state = new DataServiceState()
        state.serviceName = serviceName
        state.data = data as JSON
        state.save(flush: true)
    }

    Long getLastState() {
        def serviceName = 'indicator-bulk'
        def data = DataServiceState.findByServiceNameAndIsLastState(serviceName, true)?.data
        data ? JSON.parse(data)?.symbolId ?: 0 : 0
    }
}
