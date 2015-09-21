package stocks.messaging

import fi.joensuu.joyds1.calendar.JalaliCalendar
import grails.converters.JSON
import stocks.NewsLetterJob
import stocks.User

class NewsLetterController {

    static allowedMethods = [save: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def build() {
        def newsLetter
        if (params.id)
            newsLetter = NewsLetter.get(params.id)
        else
            newsLetter = new NewsLetter()
        [newsLetter: newsLetter]
    }

    def list() {
    }

    def save() {
        NewsLetter newsLetter
        if (params.id) {
            newsLetter = NewsLetter.get(params.id)
        } else
            newsLetter = new NewsLetter()

        newsLetter.subject = params.subject
        newsLetter.body = params.body
        newsLetter.categories?.clear()
        newsLetter.sendDate = parseDateTime(params.sendDate_date, params.sendDate_time)
        params.keySet().findAll { it.toString().startsWith('category') }.collect {
            it.split('_').last().toString().toLong()
        }.each {
            newsLetter.addToCategories(NewsLetterCategory.get(it))
        }

        newsLetter.save(flush: true)
        scheduleNewsLetter(newsLetter)
        redirect(action: 'list')
    }

    def scheduleNewsLetter(NewsLetter newsLetter) {
//        if (newsLetter.sendDate > new Date()) {

        NewsLetterInstance.findAllByNewsLetterAndStatusInList(newsLetter, ['scheduled', 'started']).each { newsLetterInstance ->
            newsLetterInstance.status = 'suspended'
            newsLetterInstance.finishDate = new Date()
            newsLetterInstance.save()
        }

        def newsLetterInstance = new NewsLetterInstance()
        newsLetterInstance.newsLetter = newsLetter
        newsLetterInstance.status = 'scheduled'
        newsLetterInstance.startDate = newsLetter.sendDate
        newsLetterInstance.save()

//            NewsLetterJob.schedule(newsLetter.sendDate, [newsLetterInstance: newsLetterInstance])
//        }
    }

    def jsonList() {

        def value = [:]
        def parameters = [offset: params.skip, max: params.pageSize, sort: params["sort[0][field]"] ?: "id", order: params["sort[0][dir]"] ?: "desc"]

        def list = NewsLetter.findAllByDeleted(false, parameters)
        value.total = NewsLetter.countByDeleted(false)

        value.data = list.collect {
            [
                    id        : it.id,
                    name      : it.subject,
                    categories: it.categories.collect { it.name }.join(' ،'),
                    sendDate  : format.jalaliDate(date: it.sendDate)
            ]
        }

        render value as JSON
    }

    def delete() {
        def item = NewsLetter.get(params.id)
        item.deleted = true
        render(item.save() ? '1' : '0')
    }

    def saveUserSubscription() {
        def user = User.get(params.userId)
        if (user) {
            user.newsLetterCategories.clear()
            params.keySet().findAll { it.toString().startsWith('category') }.collect {
                it.split('_').last().toString().toLong()
            }.each {
                user.addToNewsLetterCategories(NewsLetterCategory.get(it))
            }
            user.save(flush: true)
        }
        redirect(uri: '/profile')
    }

    def history() {
        [newsLetter: NewsLetter.get(params.id)]
    }

    def historyJsonList() {

        def newsLetter = NewsLetter.get(params.id)

        def value = [:]
        def parameters = [offset: params.skip, max: params.pageSize, sort: params["sort[0][field]"] ?: "id", order: params["sort[0][dir]"] ?: "desc"]

        def list = NewsLetterInstance.findAllByNewsLetter(newsLetter, parameters)
        value.total = NewsLetterInstance.countByNewsLetter(newsLetter)

        value.data = list.collect {
            [
                    id        : it.id,
                    startDate : format.jalaliDate(date: it.startDate, hm: true),
                    finishDate: it.finishDate ? format.jalaliDate(date: it.finishDate, hm: true) : '',
                    status    : message(code: "newsLetterInstance.status.${it.status}")
            ]
        }

        render value as JSON
    }

    def details() {
        [newsLetterInstance: NewsLetterInstance.get(params.id)]
    }

    def detailsJsonList() {

        def newsLetterInstance = NewsLetterInstance.get(params.id)

        def value = [:]
        def parameters = [offset: params.skip, max: params.pageSize, sort: params["sort[0][field]"] ?: "id", order: params["sort[0][dir]"] ?: "desc"]

        def list = NewsLetterLog.findByNewsLetterInstance(newsLetterInstance, parameters)
        value.total = NewsLetterLog.countByNewsLetterInstance(newsLetterInstance)

        value.data = list.collect {
            [
                    id          : it.id,
                    customer    : it.customer?.toString(),
                    email       : it.customer?.email,
                    scheduleDate: it.scheduleDate ? format.jalaliDate(date: it.scheduleDate, hm: true) : '',
                    sendDate    : it.sendDate ? format.jalaliDate(date: it.sendDate, hm: true) : '',
                    status      : message(code: "newsLetterLog.status.${it.status}"),
                    errorMessage: it.errorMessage
            ]
        }

        render value as JSON
    }

    def parseDateTime(String date, String time) {
        def dateParts = date.split("/").collect { it as Integer }
        JalaliCalendar jc = new JalaliCalendar(dateParts[0], dateParts[1], dateParts[2])
        def cal = jc.toJavaUtilGregorianCalendar()
        def timeParts = time.split(":")
        cal.set(Calendar.HOUR_OF_DAY, timeParts[0] as Integer)
        cal.set(Calendar.MINUTE, timeParts[1] as Integer)
        cal.time
    }
}
