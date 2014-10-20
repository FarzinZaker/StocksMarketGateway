package stocks.tse.data

import org.apache.axis.types.UnsignedByte
import stocks.tse.MarketActivity
import stocks.tse.TSEDataService
import stocks.tse.event.MarketActivityEvent

class MarketActivityDataService extends TSEDataService<MarketActivity, MarketActivityEvent> {
    static transactional = false
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
    protected MarketActivityEvent getSampleEventObject() {
        new MarketActivityEvent()
    }

    @Override
    protected MarketActivity find(MarketActivityEvent object) {
        MarketActivity.findByMarketIdentifierAndDate(object.marketIdentifier, object.date)
    }

    public void importData(){
        importData('marketActivityLast',
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
