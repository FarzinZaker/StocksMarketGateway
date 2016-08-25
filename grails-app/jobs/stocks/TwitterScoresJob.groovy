package stocks

import grails.util.Environment


class TwitterScoresJob {
    static startDelay = 60000
    static timeout = 1000l
    static concurrent = false

    def sharingService

    def execute() {

        if(Environment.isDevelopmentMode())
            return

        sharingService.applyATwitScore()
    }
}
