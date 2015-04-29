package stocks.tse.persist

import stocks.indicators.IndicatorServiceBase
import stocks.tse.SymbolDailyTrade
import stocks.tse.TSEPersistService
import stocks.tse.event.SymbolDailyTradeEvent
import stocks.util.ClassResolver

class SymbolDailyTradePersistService extends TSEPersistService<SymbolDailyTrade, SymbolDailyTradeEvent> {
    static transactional = false

    def symbolIndicatorService
    def grailsApplication

    @Override
    protected getSampleObject() {
        new SymbolDailyTrade()
    }

    @Override
    protected List<String> getPropertyExcludeList() {
        ['oldClosingPrice', 'indicatorsCalculated']
    }

    @Override
    protected void beforeCreate(SymbolDailyTradeEvent event) {

    }

    @Override
    protected void afterCreate(SymbolDailyTradeEvent event, SymbolDailyTrade data) {
        calculateOnlineIndicators(data)
    }

    @Override
    protected void beforeUpdate(SymbolDailyTradeEvent event, SymbolDailyTrade data) {
    }

    @Override
    protected void afterUpdate(SymbolDailyTradeEvent event, SymbolDailyTrade data) {
        calculateOnlineIndicators(data)
    }

    def calculateOnlineIndicators(SymbolDailyTrade dailyTrade){
        grailsApplication.getArtefacts('Service').findAll {
            it.fullName.startsWith("stocks.indicators.symbol.")
        }.each { serviceClass ->
            def service = ClassResolver.loadServiceByName(serviceClass.fullName) as IndicatorServiceBase
            if (service.enabled) {
                service.commonParameters.each { parameter ->
                    symbolIndicatorService.calculateIndicator(dailyTrade, service, parameter, true)
                }
            }
        }
    }
}
