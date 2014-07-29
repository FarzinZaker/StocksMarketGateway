package stocks.tse

import org.apache.axis.types.UnsignedByte

class SymbolBestRequestService extends TSEService<SymbolBestRequest> {
    @Override
    protected getSampleObject() {
        new SymbolBestRequest()
    }

    @Override
    protected SymbolBestRequest find(SymbolBestRequest object) {
        SymbolBestRequest.findBySymbolInternalCodeAndNumber(object.symbolInternalCode, object.number)
    }

    public List<SymbolBestRequest> importData(){
        importData('bestLimitsAllIns',
                [
                        [new UnsignedByte(0)],
                        [new UnsignedByte(1)],
                        [new UnsignedByte(2)],
                        [new UnsignedByte(3)],
                        [new UnsignedByte(4)],
                        [new UnsignedByte(5)]
                ])
    }

    public List<SymbolBestRequest> importData(Long symbolInternalCode){
        importData('bestLimitOneIns',[[symbolInternalCode]])
    }
}
