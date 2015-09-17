package stocks.feed

import fi.joensuu.joyds1.calendar.JalaliCalendar
import grails.converters.JSON
import org.apache.lucene.search.BooleanQuery
import org.ocpsoft.prettytime.PrettyTime

class ExternalNewsController {

    def index() {}

    def view() {

        def news = ExternalNews.findByIdentifier(params.id as String)
        if (news) {
//        if (!session["linkClick_${params.id}"]) {
            try {
                news.clickCount++
                news.save(flush: true)
            }
            catch (ignored) {
                try {
                    ExternalNews.executeUpdate("update ExternalNews n set n.clickCount = n.clickCount + 1 where n.identifier = :identifier", [identifier: params.id as String])
                } catch (ignore) {
                }
            }
//            session["linkClick_${params.id}"] = 1
//        }
            redirect(url: news.link)
        } else {
            redirect(url: ExternalNews.search("identifier:${params.id}")?.results?.find()?.link)
        }
//        if (!session["linkClick_${params.id}"]) {
//        News.executeUpdate("update News n set n.clickCount = n.clickCount + 1 where n.identifier = :identifier", [identifier: params.id as String])
//            session["linkClick_${params.id}"] = 1
//        }

//        redirect(url: News.executeQuery("select n.link from News n where n.identifier = :identifier", [identifier: params.id as String])?.find())
    }

    def archive() {
        [
                sources   : ['farsNews', 'asrIran', 'bourseNews', 'tabnak', 'tasnim', 'irna', /*'sena', */'boursePress', 'mellatBazar'],
                categories: ExternalNewsService.categoryList
        ]
    }

    def jsonList() {

        def categoryFilter = params.findAll { it.key.startsWith('category') && it.value == 'on' }.collect {
            "category:${it.key.toString().replace('category_', '')}"
        }.join(' OR ')
        def sourceFilter = params.findAll { it.key.startsWith('source') && it.value == 'on' }.collect {
            "source:${it.key.toString().replace('source_', '')}"
        }.join(' OR ')
        def date = parseDate(params.date as String)
        def searchPhrase = params.search as String

        def yesterday = new Date().clearTime()

        BooleanQuery.setMaxClauseCount(1000000)
        Calendar calendar = Calendar.instance
        if (date)
            calendar.setTime(date)
        def feeds = ExternalNews.search("*${searchPhrase}* ${categoryFilter != '' ? "AND (${categoryFilter})" : ''} ${sourceFilter != '' ? "AND (${sourceFilter})" : ''} ${date ? "AND (day:${calendar.get(Calendar.YEAR)}${(calendar.get(Calendar.MONTH) + 1).toString().padLeft(2, '0')}${calendar.get(Calendar.DAY_OF_MONTH).toString().padLeft(2, '0')})" : ''}", sort: "date", order: "desc", max: 1000).results.collect {
            [
                    identifier  : it.identifier,
                    title       : it.title,
                    time        : it.date.time,
                    link        : it.link,
                    category    : it.category,
                    source      : it.source,
                    sourceString: message(code: "newsSource.${it.source}"),
                    dateString  : it.date < yesterday ? format.jalaliDate(date: it.date, hm: true) : new PrettyTime(new Locale('fa')).format(it.date),
                    clickCount  : it.clickCount

            ]
        }
        render([
                data      : feeds,
                categories: ExternalNewsService.categoryList.collect {
                    [
                            value: it,
                            text : message(code: "newsCategory.${it}")
                    ]
                }
        ] as JSON)
    }

    private static Date parseDate(String date) {
        if (!date || date.trim() == '' || date.trim() == 'null')
            return null
        def dateParts = date.split("/").collect { it as Integer }
        new JalaliCalendar(dateParts[0], dateParts[1], dateParts[2]).toJavaUtilGregorianCalendar().time
    }
}
