package stocks.tse.persist

import stocks.tse.Index
import stocks.tse.TSEPersistService
import stocks.tse.event.IndexEvent

class IndexPersistService extends TSEPersistService<Index, IndexEvent> {

    @Override
    protected getSampleObject() {
        new Index()
    }

    @Override
    protected void beforeCreate(IndexEvent event) {

    }

    @Override
    protected void afterCreate(IndexEvent event, Index data) {

    }

    @Override
    protected void beforeUpdate(IndexEvent event, Index data) {

    }

    @Override
    protected void afterUpdate(IndexEvent event, Index data) {

    }
}
