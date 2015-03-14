package stocks.analysis

import stocks.tse.Symbol

class SellSignal extends BackTestSignal {

    transient Double getTotalValue(){
        price * stockCount - wage - tax
    }

    static constraints = {
    }
}
