package stocks.messaging

class NewsLetterInstance {

    static auditable = true

    NewsLetter newsLetter
    Date startDate
    Date finishDate
    String status

    static hasMany = [logs: NewsLetterLog]

    static mapping = {
        table 'msg_newsletter_instance'
    }
    static constraints = {
        status inList: ['scheduled', 'started', 'finished', 'suspended']
        startDate nullable: true
        finishDate nullable: true
        newsLetter()
    }
}
