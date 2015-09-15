package stocks.feed

import stocks.FeedService

class News {

    static searchable = {
        'date' format:'yyyyMMdd hh:mm:ss'
        'date' name: 'day', format:'yyyyMMdd'
    }

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
