package stocks.tse.data

import org.apache.axis.types.UnsignedByte
import stocks.tse.Company
import stocks.tse.TSEDataService
import stocks.tse.event.CompanyEvent

class CompanyDataService extends TSEDataService<Company, CompanyEvent> {
    static transactional = false
    static schedules = [
            [
                    method : 'importData',
                    trigger: [
                            type      : 'Simple',
                            parameters: [repeatInterval: 300000l, startDelay: 60000]
                    ]
//                    trigger: [
//                            type      : 'Cron',
//                            parameters: [cronExpression: '0 10 1 * * ?']
//                    ]
            ]
    ]
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
