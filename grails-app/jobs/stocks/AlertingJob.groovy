package stocks

import org.springframework.orm.hibernate3.SessionFactoryUtils
import stocks.alerting.QueryInstance

class AlertingJob {

    def queryService
    def sessionFactory
    def grailsApplication

    static startDelay = 60000
    static timeout = 5000l
    static concurrent = false

    def execute() {
        if (grailsApplication.config.jobsDisabled)
            return

        def session = SessionFactoryUtils.getSession(sessionFactory, true)
        QueryInstance.createCriteria().list {
            le('nextExecutionTime', new Date())
            eq('enabled', true)
            eq('deleted', false)
            schedule {
                ne('type', 'eventBased')
            }
        }.each { queryInstance ->
            queryService.applyScheduledQuery(queryInstance)
        }
        QueryInstance.findAllByNextExecutionTimeLessThanEqualsAndEnabledAndDeleted(new Date(), true, false)
        session.flush()
        session.clear()
    }
}
