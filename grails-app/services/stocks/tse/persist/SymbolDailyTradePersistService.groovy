package stocks.tse.persist

import stocks.tse.SymbolDailyTrade
import stocks.tse.TSEPersistService
import stocks.tse.event.SymbolDailyTradeEvent

class SymbolDailyTradePersistService extends TSEPersistService<SymbolDailyTrade, SymbolDailyTradeEvent> {
    static transactional = false
    @Override
    protected getSampleObject() {
        new SymbolDailyTrade()
    }

    @Override
    protected List<String> getPropertyExcludeList(){
        ['oldClosingPrice', 'indicatorsCalculated']
    }

    @Override
    protected void beforeCreate(SymbolDailyTradeEvent event) {

    }

    @Override
    protected void afterCreate(SymbolDailyTradeEvent event, SymbolDailyTrade data) {

    }

    @Override
    protected void beforeUpdate(SymbolDailyTradeEvent event, SymbolDailyTrade data) {

    }

    @Override
    protected void afterUpdate(SymbolDailyTradeEvent event, SymbolDailyTrade data) {

    }
}
