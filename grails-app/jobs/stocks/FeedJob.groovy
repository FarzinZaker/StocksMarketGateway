package stocks


class FeedJob {

    static startDelay = 60000
    def timeout = 60000l
    static concurrent = false

    def feedService

    def execute() {
        feedService.refresh()
    }
}
