package stocks.analysis

import stocks.User
import stocks.alerting.Rule

class Screener {

    static auditable = true

    static searchable = true

    String title
    Rule rule
    User owner

    Boolean deleted = false

    Date dateCreated
    Date lastUpdated
    static mapping = {
        table 'analysis_screener'
    }

    static constraints = {
    }
}
