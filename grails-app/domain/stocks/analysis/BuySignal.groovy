package stocks.analysis

class BuySignal extends BackTestSignal  {

    transient Double getTotalValue(){
        price * stockCount + wage + tax
    }

    static constraints = {
    }
}
