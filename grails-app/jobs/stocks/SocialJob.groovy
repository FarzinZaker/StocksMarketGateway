package stocks


class SocialJob {
    static startDelay = 60000
    static timeout = 1000l
    static concurrent = false

    def telegramService

    def execute() {
        try {
            telegramService.dispatchUpdates()
        } catch (ignored) {
            log.error(ignored?.message, ignored)
        }
    }
}
