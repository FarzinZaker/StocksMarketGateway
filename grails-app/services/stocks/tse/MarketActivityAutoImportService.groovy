package stocks.tse

import org.apache.axis.types.UnsignedByte

class MarketActivityAutoImportService extends TSEAutoImportService<MarketActivity> {
    @Override
    protected List<List> getParameters() {
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
    protected getSampleObject() {
        new MarketActivity()
    }

    @Override
    protected String getServiceName() {
        'marketActivityLast'
    }

    @Override
    protected MarketActivity find(MarketActivity object) {
        MarketActivity.findByMarketIdentifierAndDate(object.marketIdentifier, object.date)
    }
}
