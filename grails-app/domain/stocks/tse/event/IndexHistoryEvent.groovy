package stocks.tse.event

import stocks.tse.Index
import stocks.tse.IndexHistory

class IndexHistoryEvent {

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

    Date creationDate
    IndexHistory data

    static mapping = {
        table 'tse_index_history_ev'
        date column: 'dat'
        index column: 'idx'
        changePercentOfHighestValueTowardYesterday column: 'change_prc_high_val_to_yest'
        changePercentOfLowestValueTowardYesterday column: 'change_prc_low_val_to_yest'
        decreasedSymbolsChangePercent column: 'dec_sym_change_perc'
        baseInvestmentAdjustmentFactor column: 'base_invest_adj_factor'
    }

    static constraints = {
        index nullable: true, xmlNodeName: 'InsCode', fkColumn: 'InternalCode'
        internalCode nullable: true, xmlNodeName: 'InsCode'
        date nullable: true, xmlNodeName: 'DEven', locale: 'en', timeXmlNode: 'HEven'
        finalIndexValue nullable: true, xmlNodeName: 'XNivInuClMresIbs'
        finalIndexValueDate nullable: true, xmlNodeName: 'DEven', locale: 'en', timeXmlNode: 'HNivInuClMresIbs'
        firstIndexValue nullable: true, xmlNodeName: 'XNivInuPrDifMresIbs'
        firstIndexValueDate nullable: true, xmlNodeName: 'DEven', locale: 'en', timeXmlNode: 'HNivInuPrDifMresIbs'
        highestIndexValue nullable: true, xmlNodeName: 'XNivInuPhMresIbs'
        highestIndexValueDate nullable: true, xmlNodeName: 'DEven', locale: 'en', timeXmlNode: 'HNivInuPhMresIbs'
        changePercentOfHighestValueTowardYesterday nullable: true, xmlNodeName: 'XVarIdxPhJClV'
        lowestIndexValue nullable: true, xmlNodeName: 'XNivInuPbMresIbs'
        lowestIndexValueDate nullable: true, xmlNodeName: 'DEven', locale: 'en', timeXmlNode: 'HNivInuPbMresIbs'
        changePercentOfLowestValueTowardYesterday nullable: true, xmlNodeName: 'XVarIdxPbJClV'
        decreasedSymbolsChangePercent nullable: true, xmlNodeName: 'XVarDrInuClV'
        netPaidBenefit nullable: true, xmlNodeName: 'QDvdNetJValIbs'
        yesterdayInvestment nullable: true, xmlNodeName: 'QCapBsRfVIbs'
        baseInvestmentAdjustmentFactor nullable: true, xmlNodeName: 'KAjCapBzIbs'
        netCashReturnIndex nullable: true, xmlNodeName: 'XNivIrteNetClIbs'

        data nullable: true
    }
}
