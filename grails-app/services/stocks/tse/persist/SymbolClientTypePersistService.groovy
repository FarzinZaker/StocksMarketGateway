package stocks.tse.persist

import stocks.tse.SymbolClientType
import stocks.tse.TSEPersistService
import stocks.tse.event.SymbolClientTypeEvent

class SymbolClientTypePersistService extends TSEPersistService<SymbolClientType, SymbolClientTypeEvent> {

    @Override
    protected getSampleObject() {
        new SymbolClientType()
    }

    @Override
    protected List<String> getPropertyExcludeList() {
        ['date']
    }

    @Override
    protected void beforeCreate(SymbolClientTypeEvent event) {
        event.date = new Date().clearTime()
    }

    @Override
    protected void afterCreate(SymbolClientTypeEvent event, SymbolClientType data) {

    }

    @Override
    protected void beforeUpdate(SymbolClientTypeEvent event, SymbolClientType data) {

    }

    @Override
    protected void afterUpdate(SymbolClientTypeEvent event, SymbolClientType data) {

    }
}
