package stocks

import grails.converters.JSON
import org.ocpsoft.prettytime.PrettyTime

class LandingController {

    def feedService

    def index() {

    }

    def news() {
        def result = feedService.news()
        render([
                data      : result.data.collect {
                    [
                            id        : it.id,
                            title     : it.title,
                            time      : it.date.time,
                            dateString: new PrettyTime(new Locale('fa')).format(it.date),
                            link      : it.link,
                            category  : it.category,
                            source    : message(code: "newsSource.${it.source}")
                    ]
                },
                categories: result.categoryList.collect {
                    [
                            value: it,
                            text : message(code: "newsCategory.${it}")
                    ]
                }
        ] as JSON)
    }
}
