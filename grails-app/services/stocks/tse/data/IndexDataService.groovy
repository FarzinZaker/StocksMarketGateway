package stocks.tse.data

import org.apache.axis.types.UnsignedByte
import stocks.tse.Index
import stocks.tse.TSEDataService
import stocks.tse.event.IndexEvent

class IndexDataService extends TSEDataService<Index, IndexEvent> {
    static transactional = false

    def marketStatusService

    static schedules = [
            [
                    method : 'importData',
                    trigger: [
                            type      : 'Simple',
                            parameters: [repeatInterval: 60000l, startDelay: 60000]
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

    public void importData(Boolean ignoreMarketStatus = false){

//        if(!ignoreMarketStatus && marketStatusService.isCloseWithMargin(marketStatusService.MARKET_STOCK))
//            return

        (0..5).each { index ->
            Thread.startDaemon {
                importData('indexB1LastDayLastData',
                        [[new UnsignedByte(index)]]
                )
            }
        }
    }
}
