package stocks.feed

import stocks.FeedService

class News {

    static searchable = true

    String title
    String identifier
    Date date
    String link
    String category
    String source
    Integer clickCount = 0

    static mapping = {
        table 'feed_news'
        date column: 'dat'
        identifier unique: true
    }

    static constraints = {
        category inList: FeedService.categoryList
    }
}
