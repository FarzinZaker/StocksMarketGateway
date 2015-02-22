package stocks.tse.persist

import stocks.tse.SymbolPriceAdjustment
import stocks.tse.TSEPersistService
import stocks.tse.event.SymbolPriceAdjustmentEvent

class SymbolPriceAdjustmentPersistService extends TSEPersistService<SymbolPriceAdjustment, SymbolPriceAdjustmentEvent> {

    static transactional = false

    @Override
    protected getSampleObject() {
        return new SymbolPriceAdjustment()
    }

    @Override
    protected void beforeCreate(SymbolPriceAdjustmentEvent event) {

    }

    @Override
    protected void afterCreate(SymbolPriceAdjustmentEvent event, SymbolPriceAdjustment data) {

    }

    @Override
    protected void beforeUpdate(SymbolPriceAdjustmentEvent event, SymbolPriceAdjustment data) {

    }

    @Override
    protected void afterUpdate(SymbolPriceAdjustmentEvent event, SymbolPriceAdjustment data) {

    }
}
