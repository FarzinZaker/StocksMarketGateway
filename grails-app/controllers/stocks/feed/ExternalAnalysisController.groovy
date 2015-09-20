package stocks.feed

import fi.joensuu.joyds1.calendar.JalaliCalendar
import grails.converters.JSON
import groovy.time.TimeCategory
import org.apache.lucene.search.BooleanQuery
import org.ocpsoft.prettytime.PrettyTime

class ExternalAnalysisController {

    def index() {}

    def view() {

        def analysis = ExternalAnalysis.findByIdentifier(params.id as String)
        if (analysis) {
//        if (!session["linkClick_${params.id}"]) {
            try {
                analysis.clickCount++
                analysis.save(flush: true)
            }
            catch (ignored) {
                try {
                    ExternalAnalysis.executeUpdate("update ExternalAnalysis n set n.clickCount = n.clickCount + 1 where n.identifier = :identifier", [identifier: params.id as String])
                } catch (ignore) {
                }
            }
//            session["linkClick_${params.id}"] = 1
//        }
//            redirect(url: analysis.link)
        }
//        else {
//            redirect(url: ExternalAnalysis.search("identifier:${params.id}")?.results?.find()?.link)
//        }
        render '1'
    }

    def archive() {
        [
                sources   : ['tsn', 'emaco', 'artaTahlil', 'persianTahlil'],
                categories: ExternalAnalysisService.visibleCategoryList
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
        def nextDate = date
        use(TimeCategory){
            nextDate = nextDate + 1.days
        }

        BooleanQuery.setMaxClauseCount(1000000)
        Calendar calendar = Calendar.instance
        if (date)
            calendar.setTime(date)
        def feeds = ExternalAnalysis.search(sort: params.sort?.toString(), order: params.order?.toString(), max: params.pageSize.toInteger(), offset:params.skip.toInteger()) {
            must {
                queryString("*${searchPhrase}* ${categoryFilter != '' ? "AND (${categoryFilter})" : ''} ${sourceFilter != '' ? "AND (${sourceFilter})" : ''}")
            }
            if (date) {
                must {
                    lt('date', nextDate)
                }
            }
        }
        render([
                data : feeds.results.collect {
                    [
                            identifier  : it.identifier,
                            title       : it.title,
                            time        : it.date.time,
                            link        : it.link,
                            category    : it.category,
                            source      : it.source,
                            sourceString: message(code: "analysisSource.${it.source}"),
                            dateString  : it.date < yesterday ? format.jalaliDate(date: it.date, hm: true) : new PrettyTime(new Locale('fa')).format(it.date),
                            clickCount  : it.clickCount

                    ]
                },
                total: feeds.total
        ] as JSON)
    }

    private static String prepareDateForSearch(Date date) {
//        2015-09-19-19-07-01-786-PM
        def calendar = Calendar.instance
        calendar.setTime(date)

        def result = ''
        result += calendar.get(Calendar.YEAR)
        result += '-'
        result += (calendar.get(Calendar.MONTH) + 1).toString().padLeft(2, '0')
        result += '-'
        result += calendar.get(Calendar.DAY_OF_MONTH).toString().padLeft(2, '0')
        result += '-'
        result += calendar.get(Calendar.HOUR).toString().padLeft(2, '0')
        result += '-'
        result += calendar.get(Calendar.MINUTE).toString().padLeft(2, '0')
        result += '-'
        result += calendar.get(Calendar.SECOND).toString().padLeft(2, '0')
        result += '-'
        result += calendar.get(Calendar.MILLISECOND).toString().padLeft(3, '0')
        result += '-'
        result += calendar.get(Calendar.AM) == 1 ? 'AM' : 'PM'
        result
    }

    private static Date parseDate(String date) {
        if (!date || date.trim() == '' || date.trim() == 'null')
            return null
        def dateParts = date.split("/").collect { it as Integer }
        new JalaliCalendar(dateParts[0], dateParts[1], dateParts[2]).toJavaUtilGregorianCalendar().time
    }
}
