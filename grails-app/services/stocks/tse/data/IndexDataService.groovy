package stocks.tse.data

import org.apache.axis.types.UnsignedByte
import stocks.tse.Index
import stocks.tse.TSEDataService
import stocks.tse.event.IndexEvent

class IndexDataService extends TSEDataService<Index, IndexEvent> {
    static transactional = false
    static schedules = [
            [
                    method : 'importData',
                    trigger: [
                            type      : 'Simple',
                            parameters: [repeatInterval: 600000l, startDelay: 60000]
                    ]
            ]
    ]

    @Override
    protected IndexEvent getSampleEventObject() {
        new IndexEvent()
    }

    @Override
    protected Index find(IndexEvent object) {
        Index.findByPersianName(object.persianName)
    }

    public void importData(){
        importData('indexB1LastDayLastData',
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
