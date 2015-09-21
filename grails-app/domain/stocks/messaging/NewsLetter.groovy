package stocks.messaging

class NewsLetter {

    static auditable = true

    String subject
    String body
    Date sendDate

    Boolean deleted = false

    static hasMany = [categories: NewsLetterCategory]

    static belongsTo = [NewsLetterCategory]

    static mapping = {
        table 'msg_newsletter'
        body type: "text", sqlType: 'clob'
        categories joinTable: [name: 'msg_mm_newsletter_category', key: 'newsletter_id']
    }

    static constraints = {
        subject()
        categories()
        sendDate(nullable: true, persian: true)
        body()
    }

    @Override
    String toString() {
        subject
    }
}
