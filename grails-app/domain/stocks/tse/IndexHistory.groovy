package stocks.tse

class IndexHistory {

    Index index
    Long internalCode
    Date date
    Double finalIndexValue
    Date finalIndexValueDate
    Double firstIndexValue
    Date firstIndexValueDate
    Double highestIndexValue
    Date highestIndexValueDate
    Double changePercentOfHighestValueTowardYesterday
    Double lowestIndexValue
    Double lowestIndexValueDate
    Double changePercentOfLowestValueTowardYesterday
    Double decreasedSymbolsChangePercent
    Double netPaidBenefit
    Double yesterdayInvestment
    Double baseInvestmentAdjustmentFactor
    Double netCashReturnIndex

    static snapshotGroupProperty = 'index'
    static snapshotDateProperty = 'date'

    Date dailySnapshot
    Date weeklySnapshot
    Date monthlySnapshot


    Date creationDate
    Date modificationDate

    static mapping = {
        table 'tse_index_history'
        date column: 'dat'
        index column: 'idx'
        changePercentOfHighestValueTowardYesterday column: 'change_prc_high_val_to_yest'
        changePercentOfLowestValueTowardYesterday column: 'change_prc_low_val_to_yest'
        decreasedSymbolsChangePercent column: 'dec_sym_change_perc'
        baseInvestmentAdjustmentFactor column: 'base_invest_adj_factor'
    }

    static constraints = {
        index nullable: true
        internalCode nullable: true
        date nullable: true
        finalIndexValue nullable: true
        finalIndexValueDate nullable: true
        firstIndexValue nullable: true
        firstIndexValueDate nullable: true
        highestIndexValue nullable: true
        highestIndexValueDate nullable: true
        changePercentOfHighestValueTowardYesterday nullable: true
        lowestIndexValue nullable: true
        lowestIndexValueDate nullable: true
        changePercentOfLowestValueTowardYesterday nullable: true
        decreasedSymbolsChangePercent nullable: true
        netPaidBenefit nullable: true
        yesterdayInvestment nullable: true
        baseInvestmentAdjustmentFactor nullable: true
        netCashReturnIndex nullable: true

        dailySnapshot nullable: true
        weeklySnapshot nullable: true
        monthlySnapshot nullable: true
    }
}
