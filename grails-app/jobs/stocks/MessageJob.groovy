package stocks

import org.springframework.orm.hibernate3.SessionFactoryUtils
import stocks.alerting.QueuedMessage

class MessageJob {

    def smsService
    def sessionFactory
    def grailsApplication

    static startDelay = 60000
    static timeout = 5000l
    static concurrent = false

    def execute() {

        if (grailsApplication.config.jobsDisabled)
            return

        def session = SessionFactoryUtils.getSession(sessionFactory, true)
        def message = QueuedMessage.listOrderByLastUpdated([order: 'asc', max: 1])?.find()
        if (message)
            smsService.sendMessage(message)
        session.flush()
        session.clear()
    }
}
