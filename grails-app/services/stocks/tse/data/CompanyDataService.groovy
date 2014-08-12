package stocks.tse.data

import org.apache.axis.types.UnsignedByte
import stocks.tse.Company
import stocks.tse.TSEDataService
import stocks.tse.event.CompanyEvent

class CompanyDataService extends TSEDataService<Company, CompanyEvent> {
    @Override
    protected CompanyEvent getSampleEventObject() {
        new CompanyEvent()
    }

    @Override
    protected Company find(CompanyEvent object) {
        Company.findByCode(object.code)
    }

    public void importData(){
        importData('company',
                [
                        [new UnsignedByte(0)],
                        [new UnsignedByte(1)],
                        [new UnsignedByte(2)],
                        [new UnsignedByte(3)],
                        [new UnsignedByte(4)],
                        [new UnsignedByte(5)]
                ])
    }
}
