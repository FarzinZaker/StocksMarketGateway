package stocks.analysis

import stocks.User
import stocks.alerting.Rule
import stocks.tse.Symbol

class BackTest {

//    static auditable = true

    static searchable = true

    TradeStrategy tradeStrategy
    Symbol symbol
    Rule buyRule
    Rule sellRule
    Float lossLimit = 0
    Float profitLimit = 0
    Integer timeLimit = 0
    Date startDate
    Date endDate
    Integer outlay
    Float buyWage
    Float sellWage
    Float buyTax
    Float sellTax
    String adjustmentType
    Date currentDate
    String status
    User owner

    Boolean deleted = false

    Date dateCreated
    Date lastUpdated
    static mapping = {
        table 'ana_back_test'
        currentDate column: 'current_simulation_date'
    }

    static constraints = {
        adjustmentType nullable: true
        status inlist:BackTestHelper.STATUS_LIST
    }

    transient Double getLossLimitValue(){
        lossLimit * outlay
    }

    transient Double getProfitLimitValue(){
        profitLimit * outlay
    }
}
