package stocks

import stocks.messaging.NewsLetterInstance
import stocks.messaging.NewsLetterLog

class NewsLetterService {

    def mailService

    def createLogs() {
        def newsLetterInstance = NewsLetterInstance.createCriteria().list {
            lte('startDate', new Date())
            eq('status', 'scheduled')
            order('startDate', ORDER_ASCENDING)
            maxResults(1)
        }?.find()
        if (newsLetterInstance) {
            if (newsLetterInstance?.status == 'scheduled') {

                newsLetterInstance.logs?.clear()
                User.createCriteria().list {
                    newsLetterCategories {
                        'in'('id', newsLetterInstance.newsLetter.categories.collect { it.id } ?: [0.toLong()])
                    }
                }.unique { it.id }.each { customer ->
                    def log = new NewsLetterLog()
                    log.status = 'scheduled'
                    log.scheduleDate = newsLetterInstance.startDate
                    log.newsLetterInstance = newsLetterInstance
                    log.customer = customer
                    log.save()

                    newsLetterInstance.addToLogs(log)
                }
                newsLetterInstance.status = 'started'
                newsLetterInstance.save(flush: true)
            }
        }
    }

    def executeLogs() {
        def newsLetterLogs = NewsLetterLog.createCriteria().list {
            eq('status', 'scheduled')
            order('scheduleDate', ORDER_ASCENDING)
            maxResults(10)
        }

        newsLetterLogs.each { newsLetterLog ->
            newsLetterLog.sendDate = new Date()
            try {
                mailService.sendMail {
                    to newsLetterLog.customer.email
                    subject newsLetterLog.newsLetterInstance.newsLetter.subject
                    html newsLetterLog.newsLetterInstance.newsLetter.body
                }
                newsLetterLog.status = 'sent'
                newsLetterLog.errorMessage = ''
                newsLetterLog.stackTrace = ''
            }
            catch (exception) {
                newsLetterLog.status = 'error'
                newsLetterLog.errorMessage = exception.message
                newsLetterLog.stackTrace = exception.stackTrace
            }
            newsLetterLog.save(flush: true)
        }
    }

    def updateNewsLetters() {
        NewsLetterInstance.executeUpdate("update NewsLetterInstance i set i.status = 'finished', i.finishDate = :finishDate where i.status = 'started' and not exists (from NewsLetterLog l where l.status <> 'scheduled' and l.newsLetterInstance = i)", [finishDate: new Date()])
    }
}
