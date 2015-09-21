package stocks.messaging

class NewsLetterCategory {

    static auditable = true

    String name
    Boolean deleted = false

    static hasMany = [newsLetters: NewsLetter]

    static mapping = {
        table 'msg_newsletter_category'
        newsLetters joinTable: [name: 'msg_mm_newsletter_category', key: 'category_id']
    }

    static constraints = {
        name()
        newsLetters()
    }

    @Override
    String toString() {
        name
    }
}
