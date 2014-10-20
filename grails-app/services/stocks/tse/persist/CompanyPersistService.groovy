package stocks.tse.persist

import stocks.tse.Company
import stocks.tse.TSEPersistService
import stocks.tse.event.CompanyEvent

class CompanyPersistService extends TSEPersistService<Company, CompanyEvent> {
    static transactional = false
    @Override
    protected getSampleObject() {
        new Company()
    }

    @Override
    protected void beforeCreate(CompanyEvent event) {

    }

    @Override
    protected void afterCreate(CompanyEvent event, Company data) {

    }

    @Override
    protected void beforeUpdate(CompanyEvent event, Company data) {

    }

    @Override
    protected void afterUpdate(CompanyEvent event, Company data) {

    }
}
