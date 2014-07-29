package stocks.tse

import org.apache.axis.types.UnsignedByte

class MarketActivityService extends TSEService<MarketActivity> {

    @Override
    protected getSampleObject() {
        new MarketActivity()
    }

    @Override
    protected MarketActivity find(MarketActivity object) {
        MarketActivity.findByMarketIdentifierAndDate(object.marketIdentifier, object.date)
    }

    public List<MarketActivity> importData(){
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
