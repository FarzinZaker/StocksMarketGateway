package stocks.analysis

import stocks.tse.Symbol

class SellSignal extends BackTestSignal {

    Double effect

    transient Double getTotalValue(){
        closingPrice * count - wage - tax
    }

    static constraints = {
    }
}
