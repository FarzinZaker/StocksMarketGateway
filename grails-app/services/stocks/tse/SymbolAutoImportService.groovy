package stocks.tse

import org.apache.axis.types.UnsignedByte

class SymbolAutoImportService extends TSEAutoImportService<Symbol> {
    @Override
    protected getSampleObject() {
        new Symbol()
    }

    @Override
    protected String getServiceName() {
        'instrument'
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
    protected Symbol find(Symbol object) {
        Symbol.findByCode(object.code)
    }
}
