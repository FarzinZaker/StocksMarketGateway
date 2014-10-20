package stocks.tse.persist

import stocks.tse.IndexSymbol
import stocks.tse.TSEPersistService
import stocks.tse.event.IndexSymbolEvent

class IndexSymbolPersistService extends TSEPersistService<IndexSymbol, IndexSymbolEvent> {
    static transactional = false
    @Override
    protected getSampleObject() {
        new IndexSymbol()
    }

    @Override
    protected void beforeCreate(IndexSymbolEvent event) {

    }

    @Override
    protected void afterCreate(IndexSymbolEvent event, IndexSymbol data) {

    }

    @Override
    protected void beforeUpdate(IndexSymbolEvent event, IndexSymbol data) {

    }

    @Override
    protected void afterUpdate(IndexSymbolEvent event, IndexSymbol data) {

    }
}
