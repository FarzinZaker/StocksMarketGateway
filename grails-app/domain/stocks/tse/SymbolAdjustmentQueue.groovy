package stocks.tse

class SymbolAdjustmentQueue {

    Symbol symbol
    String adjustmentType

    Date dateCreated
    Date lastUpdated

    Boolean applied = false

    static constraints = {
        adjustmentType inList: AdjustmentHelper.TYPES
    }
}
