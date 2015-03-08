package stocks.analysis

import stocks.User
import stocks.alerting.Rule
import stocks.tse.Symbol

class TradeStrategy {

    static auditable = true

    static searchable = true

    String name
    Rule buyRule
    Rule sellRule
    Integer lossLimit = 0
    Integer profitLimit = 0
    Integer timeLimit = 0
    User owner

    Boolean deleted = false

    Date dateCreated
    Date lastUpdated
    static mapping = {
        table 'analysis_trade_strategy'
    }

    static constraints = {
    }
}
