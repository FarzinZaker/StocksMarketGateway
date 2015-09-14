package stocks.feed

import org.ocpsoft.prettytime.PrettyTime
import stocks.FeedService

class NewsController {

    def index() {}

    def view() {
//        if (!session["linkClick_${params.id}"]) {
            News.executeUpdate("update News n set n.clickCount = n.clickCount + 1 where n.identifier = :identifier", [identifier: params.id as String])
//            session["linkClick_${params.id}"] = 1
//        }

        redirect(url: News.executeQuery("select n.link from News n where n.identifier = :identifier", [identifier: params.id as String])?.find())
    }

    def archive() {
        [
                sources: ['farsNews', 'asrIran', 'bourseNews', 'tabnak', 'tasnim', 'irna'],
                categories: FeedService.categoryList
        ]
    }

    def jsonList(){
        def feeds = News.createCriteria().list {
            order('date', ORDER_DESCENDING)
            maxResults(100)
        }.collect {
            [
                    identifier: it.identifier,
                    title     : it.title,
                    time      : it.date.time,
                    link      : it.link,
                    category  : it.category,
                    source    : message(code: "newsSource.${it.source}"),
                    dateString: new PrettyTime(new Locale('fa')).format(it.date),
                    clickCount: it.clickCount

            ]
        }
        [
                data      : feeds,
                categories: FeedService.categoryList.collect {
                    [
                            value: it,
                            text : message(code:"newsCategory.${it}")
                    ]
                }
        ]
    }
}
