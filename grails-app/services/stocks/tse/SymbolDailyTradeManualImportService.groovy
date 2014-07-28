package stocks.tse

import org.apache.axis.types.UnsignedByte

class SymbolDailyTradeManualImportService extends TSEManualImportService<SymbolDailyTrade> {
    @Override
    protected getSampleObject() {
        new SymbolDailyTrade()
    }

    @Override
    protected String getServiceName() {
        'tradeOneDay'
    }

    @Override
    protected SymbolDailyTrade find(SymbolDailyTrade object) {
        SymbolDailyTrade.findBySymbolInternalCodeAndDate(object.symbolInternalCode, object.date)
    }

    public List<SymbolDailyTrade> execute(Date date, int marketIdentifier){
        execute([date.format('yyyyMMdd').toInteger(), new UnsignedByte(marketIdentifier)])
    }
}
