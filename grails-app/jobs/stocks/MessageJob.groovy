package stocks

import stocks.alerting.QueuedMessage

class MessageJob {

    def smsService

    static startDelay = 60000
    static timeout = 5000l
    static concurrent = false

    def execute() {
        def message = QueuedMessage.listOrderByLastUpdated([order: 'asc', max: 1])?.find()
        if(message)
            smsService.sendMessage(message)
    }
}
