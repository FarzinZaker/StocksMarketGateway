package stocks

import groovy.time.TimeCategory
import stocks.messaging.NewsLetter
import stocks.messaging.NewsLetterInstance
import stocks.messaging.NewsLetterLog


class NewsLetterJob {

    static startDelay = 60000
    static timeout = 5000l
    static concurrent = false

    def grailsApplication
    def newsLetterService

    def execute() {

        if (grailsApplication.config.jobsDisabled)
            return

        newsLetterService.createLogs()
        newsLetterService.executeLogs()
        newsLetterService.updateNewsLetters()
    }
}
