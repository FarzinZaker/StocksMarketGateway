package stocks

import org.springframework.orm.hibernate3.SessionFactoryUtils
import stocks.alerting.QueryInstance

class AlertingJob {

    def queryService
    def sessionFactory

    static startDelay = 60000
    static timeout = 5000l
    static concurrent = false

    def execute() {
        def session = SessionFactoryUtils.getSession(sessionFactory, true)
        QueryInstance.findAllByNextExecutionTimeLessThanEqualsAndEnabled(new Date(), true).each { queryInstance ->
            queryService.applyScheduledQuery(queryInstance)
        }
        session.flush()
        session.clear()
    }
}
