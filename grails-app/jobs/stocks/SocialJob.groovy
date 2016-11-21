package stocks

import grails.util.Environment


class SocialJob {
    static startDelay = 60000
    static timeout = 1000l
    static concurrent = false

    def telegramService

    def execute() {
        if(Environment.isDevelopmentMode())
            return
        try {
            telegramService.dispatchUpdates()
        } catch (ignored) {
            log.error(ignored?.message, ignored)
        }
    }
}
