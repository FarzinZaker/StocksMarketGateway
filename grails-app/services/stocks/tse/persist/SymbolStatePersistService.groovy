package stocks.tse.persist

import stocks.tse.SymbolState
import stocks.tse.TSEPersistService
import stocks.tse.event.SymbolStateEvent

class SymbolStatePersistService extends TSEPersistService<SymbolState, SymbolStateEvent> {
    static transactional = false
    @Override
    protected getSampleObject() {
        new SymbolState()
    }

    @Override
    protected void beforeCreate(SymbolStateEvent event) {

    }

    @Override
    protected void afterCreate(SymbolStateEvent event, SymbolState data) {

    }

    @Override
    protected void beforeUpdate(SymbolStateEvent event, SymbolState data) {

    }

    @Override
    protected void afterUpdate(SymbolStateEvent event, SymbolState data) {

    }
}
