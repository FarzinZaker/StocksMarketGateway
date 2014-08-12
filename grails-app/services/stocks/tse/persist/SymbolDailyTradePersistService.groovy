package stocks.tse.persist

import stocks.tse.SymbolDailyTrade
import stocks.tse.TSEPersistService
import stocks.tse.event.SymbolDailyTradeEvent

class SymbolDailyTradePersistService extends TSEPersistService<SymbolDailyTrade, SymbolDailyTradeEvent> {
    @Override
    protected getSampleObject() {
        new SymbolDailyTrade()
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
