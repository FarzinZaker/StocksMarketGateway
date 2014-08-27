package stocks

import stocks.alerting.QueuedMessage


class MessageJob {

    def smsService

    static concurrent = false

    static triggers = {
        simple repeatInterval: 5000l, startDelay: 60000 // execute job once in 5 seconds
    }

    def execute() {
        def message = QueuedMessage.listOrderByLastUpdated([order: 'asc', max: 1])?.find()
        if(message)
            smsService.sendMessage(message)
    }
}
