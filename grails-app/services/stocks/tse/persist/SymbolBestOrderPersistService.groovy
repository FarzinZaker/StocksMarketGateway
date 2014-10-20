package stocks.tse.persist

import stocks.tse.SymbolBestOrder
import stocks.tse.TSEPersistService
import stocks.tse.event.SymbolBestOrderEvent

class SymbolBestOrderPersistService extends TSEPersistService<SymbolBestOrder, SymbolBestOrderEvent> {
    static transactional = false
    @Override
    protected getSampleObject() {
        new SymbolBestOrder()
    }

    @Override
    protected void beforeCreate(SymbolBestOrderEvent event) {

    }

    @Override
    protected void afterCreate(SymbolBestOrderEvent event, SymbolBestOrder data) {

    }

    @Override
    protected void beforeUpdate(SymbolBestOrderEvent event, SymbolBestOrder data) {

    }

    @Override
    protected void afterUpdate(SymbolBestOrderEvent event, SymbolBestOrder data) {

    }
}
