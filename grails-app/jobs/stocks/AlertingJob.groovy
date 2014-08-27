package stocks

import stocks.alerting.QueryInstance

class AlertingJob {

    def queryService

    static concurrent = false

    static triggers = {
      simple repeatInterval: 5000l, startDelay: 60000 // execute job once in 5 seconds
    }

    def execute() {
        QueryInstance.findAllByNextExecutionTimeLessThanEqualsAndEnabled(new Date(), true).each{queryInstance ->
            queryService.applyScheduledQuery(queryInstance)
        }
    }
}
