package stocks.tse

import org.apache.axis.types.UnsignedByte

class CompanyService extends TSEService<Company> {
    @Override
    protected def getSampleObject() {
        new Company()
    }

    @Override
    protected Company find(Company object) {
        Company.findByCode(object.code)
    }

    public List<Company> importData(){
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
