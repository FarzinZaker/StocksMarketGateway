package stocks.tse

import org.apache.axis.types.UnsignedByte

class SymbolStateAutoImportService extends TSEAutoImportService<SymbolState> {
    @Override
    protected getSampleObject() {
        new SymbolState()
    }

    @Override
    protected String getServiceName() {
        'instrumentsState'
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
    protected SymbolState find(SymbolState object) {
        SymbolState.findBySymbolInternalCode(object.symbolInternalCode)
    }
}
