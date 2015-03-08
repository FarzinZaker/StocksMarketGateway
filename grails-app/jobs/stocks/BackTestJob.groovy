package stocks

import groovyx.gpars.GParsPool
import stocks.analysis.BackTest
import stocks.analysis.BackTestHelper
import static groovyx.gpars.GParsPool.withPool


class BackTestJob {

    def backTestService

    static startDelay = 60000
    static timeout = 1000l
    static concurrent = false

    def execute() {
        // execute task
        def waitingBackTests = BackTest.findAllByStatus(BackTestHelper.STATUS_WAITING)
        withPool {
            waitingBackTests.eachParallel{ BackTest backTest ->
                backTestService.startBackTest(backTest)
            }
        }
    }
}
