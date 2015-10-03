package stocks.tse

class SymbolAdjustmentQueue {

    Symbol symbol
    String adjustmentType

    Date dateCreated
    Date lastUpdated

    Boolean applied = false

    static mapping = {
        table 'tse_symbol_adjustment_queue'
    }

    static constraints = {
        adjustmentType inList: AdjustmentHelper.TYPES
    }
}
