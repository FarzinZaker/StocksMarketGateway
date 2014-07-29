package stocks.tse

import org.apache.axis.types.UnsignedByte

class SymbolDailyTradeService extends TSEService<SymbolDailyTrade> {

    @Override
    protected getSampleObject() {
        new SymbolDailyTrade()
    }

    @Override
    protected SymbolDailyTrade find(SymbolDailyTrade object) {
        SymbolDailyTrade.findBySymbolInternalCodeAndDate(object.symbolInternalCode, object.date)
    }

    public List<SymbolDailyTrade> importData(){
        importData('tradeLastDay',
                [
                        [new UnsignedByte(0)],
                        [new UnsignedByte(1)],
                        [new UnsignedByte(2)],
                        [new UnsignedByte(3)],
                        [new UnsignedByte(4)],
                        [new UnsignedByte(5)]
                ])
    }

    public List<SymbolDailyTrade> importData(Date date, int marketIdentifier){
        importData('tradeOneDay',[[date.format('yyyyMMdd').toInteger(), new UnsignedByte(marketIdentifier)]])
    }

    public List<SymbolDailyTrade> importData(Long symbolInternalCode, Date startDate, Date endDate){
        importData('instTrade',[[symbolInternalCode, startDate.format('yyyyMMdd').toInteger(), endDate.format('yyyyMMdd').toInteger()]])
    }
}
