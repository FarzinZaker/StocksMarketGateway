package stocks.tse.data

import org.apache.axis.types.UnsignedByte
import stocks.tse.MarketActivity
import stocks.tse.TSEDataService
import stocks.tse.event.MarketActivityEvent

class MarketActivityDataService extends TSEDataService<MarketActivity, MarketActivityEvent> {
    static transactional = false

    def marketStatusService

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
    protected MarketActivityEvent getSampleEventObject() {
        new MarketActivityEvent()
    }

    @Override
    protected MarketActivity find(MarketActivityEvent object) {
        MarketActivity.findByMarketIdentifierAndDate(object.marketIdentifier, object.date)
    }

    public void importData(Boolean ignoreMarketStatus = false){

        if(!ignoreMarketStatus && marketStatusService.isCloseWithMargin(marketStatusService.MARKET_STOCK))
            return

        (0..5).each { index ->
            Thread.startDaemon {
                importData('marketActivityLast',
                        [[new UnsignedByte(index)]]
                )
            }
        }
    }
}
