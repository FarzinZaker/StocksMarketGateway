package stocks.analysis

class BuySignal extends BackTestSignal  {

    transient Double getTotalValue(){
        closingPrice * count + wage + tax
    }

    static constraints = {
    }
}
