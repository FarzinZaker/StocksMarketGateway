package stocks


class SocialJob {
    static startDelay = 60000
    static timeout = 1000l
    static concurrent = false

    def telegramService

    def execute() {

        telegramService.dispatchUpdates()
    }
}
