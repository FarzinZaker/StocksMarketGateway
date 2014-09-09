package stocks

import stocks.alerting.QueryInstance

class AlertingJob {

    def queryService

    static startDelay = 60000
    static timeout = 5000l
    static concurrent = false

    def execute() {
        QueryInstance.findAllByNextExecutionTimeLessThanEqualsAndEnabled(new Date(), true).each{queryInstance ->
            queryService.applyScheduledQuery(queryInstance)
        }
    }
}
