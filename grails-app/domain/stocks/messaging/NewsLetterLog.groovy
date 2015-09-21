package stocks.messaging

import stocks.User

class NewsLetterLog {

    NewsLetterInstance newsLetterInstance
    User customer
    Date sendDate
    Date scheduleDate
    String status
    String errorMessage
    String stackTrace

    static mapping = {
        table 'msg_newsletter_log'
        stackTrace type: 'text', sqlType: 'clob'
    }

    static constraints = {
        customer()
        sendDate nullable: true
        status inList: ['scheduled', 'sent', 'error']
        errorMessage nullable: true
        stackTrace nullable: true
        newsLetterInstance()
    }
}
