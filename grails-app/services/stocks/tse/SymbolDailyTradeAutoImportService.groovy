package stocks.tse

import org.apache.axis.types.UnsignedByte

class SymbolDailyTradeAutoImportService extends TSEAutoImportService<SymbolDailyTrade> {
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
    protected getSampleObject() {
        new SymbolDailyTrade()
    }

    @Override
    protected String getServiceName() {
        'tradeLastDay'
    }

    @Override
    protected SymbolDailyTrade find(SymbolDailyTrade object) {
        SymbolDailyTrade.findBySymbolInternalCodeAndDate(object.symbolInternalCode, object.date)
    }
}
