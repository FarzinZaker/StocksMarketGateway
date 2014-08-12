package stocks.tse.persist

import stocks.tse.Symbol
import stocks.tse.TSEPersistService
import stocks.tse.event.SymbolEvent

class SymbolPersistService extends TSEPersistService<Symbol, SymbolEvent> {
    @Override
    protected getSampleObject() {
        new Symbol()
    }

    @Override
    protected void beforeCreate(SymbolEvent event) {

    }

    @Override
    protected void afterCreate(SymbolEvent event, Symbol data) {

    }

    @Override
    protected void beforeUpdate(SymbolEvent event, Symbol data) {

    }

    @Override
    protected void afterUpdate(SymbolEvent event, Symbol data) {

    }
}
