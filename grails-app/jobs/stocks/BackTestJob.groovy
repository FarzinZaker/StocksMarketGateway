package stocks

import groovyx.gpars.GParsPool
import stocks.analysis.BackTest
import stocks.analysis.BackTestHelper
import static groovyx.gpars.GParsPool.withPool


class BackTestJob {

    def backTestService
    def grailsApplication

    static startDelay = 60000
    static timeout = 1000l
    static concurrent = false

    def execute() {

        if (grailsApplication.config.jobsDisabled)
            return

        // execute task
        BackTest.findAllByStatus(BackTestHelper.STATUS_WAITING).each {
            it.status = BackTestHelper.STATUS_IN_PROGRESS
            it.save(flush: true)
        }
        def waitingBackTests = BackTest.findAllByStatus(BackTestHelper.STATUS_IN_PROGRESS)
        withPool {
            waitingBackTests.each { BackTest backTest ->
                backTestService.runBackTest(backTest)
            }
        }
    }
}
