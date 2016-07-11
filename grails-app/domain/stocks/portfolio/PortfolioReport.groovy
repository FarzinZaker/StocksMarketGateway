package stocks.portfolio

import stocks.PortfolioReportHelper

class PortfolioReport {

    Portfolio portfolio
    String type
    String data
    Boolean isUpdated

    Date dateCreated
    Date lastUpdated

    static belongsTo = [portfolio: Portfolio]

    static mapping = {
        table 'port_report'
        data type: "text", sqlType: "clob"
    }

    static constraints = {
        type inList: PortfolioReportHelper.REPORTS
    }
}
