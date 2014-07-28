package stocks.tse

import org.apache.axis.types.UnsignedByte

class CompanyAutoImportService extends TSEAutoImportService<Company> {
    @Override
    protected def getSampleObject() {
        new Company()
    }

    @Override
    protected String getServiceName() {
        'company'
    }

    @Override
    protected ArrayList getParameters() {
        [
                [new UnsignedByte(0)],
                [new UnsignedByte(1)],
                [new UnsignedByte(2)],
                [new UnsignedByte(3)],
                [new UnsignedByte(4)],
                [new UnsignedByte(5)]
        ]
    }

    @Override
    protected Company find(Company object) {
        Company.findByCode(object.code)
    }
}
