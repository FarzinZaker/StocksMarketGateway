package stocks


class TwitterScoresJob {
    static startDelay = 60000
    static timeout = 1000l
    static concurrent = false

    def sharingService

    def execute() {
        sharingService.applyATwitScore()
    }
}
