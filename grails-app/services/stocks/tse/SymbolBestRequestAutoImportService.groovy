package stocks.tse

import org.apache.axis.types.UnsignedByte

class SymbolBestRequestAutoImportService extends TSEAutoImportService<SymbolBestRequest> {
    @Override
    protected getSampleObject() {
        new SymbolBestRequest()
    }

    @Override
    protected String getServiceName() {
        'bestLimitsAllIns'
    }

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
    protected SymbolBestRequest find(SymbolBestRequest object) {
        SymbolBestRequest.findBySymbolInternalCodeAndNumber(object.symbolInternalCode, object.number)
    }
}
