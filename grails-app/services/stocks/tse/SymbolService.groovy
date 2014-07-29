package stocks.tse

import org.apache.axis.types.UnsignedByte

class SymbolService extends TSEService<Symbol> {
    @Override
    protected getSampleObject() {
        new Symbol()
    }

    @Override
    protected Symbol find(Symbol object) {
        Symbol.findByCode(object.code)
    }

    public List<Symbol> importData(){
        importData('instrument',
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
