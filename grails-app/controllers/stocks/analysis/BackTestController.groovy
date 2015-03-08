package stocks.analysis

import fi.joensuu.joyds1.calendar.JalaliCalendar
import grails.converters.JSON
import groovy.time.TimeCategory
import org.apache.lucene.search.BooleanQuery
import stocks.User
import stocks.tse.Symbol

class BackTestController {

    def springSecurityService

    def index() {}

    def build() {
        def tradeStrategy = TradeStrategy.get(params.id as Long)

        def startDate = new Date()
        def endDate = new Date()
        use(TimeCategory) {
            startDate = endDate - 1.year
        }
        [tradeStrategy: tradeStrategy, startDate: startDate, endDate: endDate]
    }


    def symbolAutoComplete() {
        def term = params."filter[filters][0][value]"?.toString() ?: ''
        BooleanQuery.setMaxClauseCount(1000000)
        def result = Symbol.search("*${term}*", max: 1000000)
        result = result.results.findAll { Symbol item ->
            !(0..9).contains(item.persianCode.charAt(item.persianCode.size() - 1)) &&
                    (item.persianCode.charAt(0) != 'ج' || item.persianCode.charAt(1) != ' ') &&
                    (item.persianName.charAt(0) != 'ح' || (item.persianName.charAt(1) != ' ' && item.persianName.charAt(1) != '.')) &&
                    ['300', '400', '309', '404'].contains(item.type) &&
                    item.marketCode == 'NO'
        }
        result = result.collect { Symbol symbol ->
            [
                    name : "${symbol.persianCode} - ${symbol.persianName}",
                    value: symbol.id
            ]
        }.sort { it.name }
        render([data: result] as JSON)
    }

    def save() {
        def backTest = new BackTest(params)
        backTest.buyRule = backTest.tradeStrategy.buyRule
        backTest.sellRule = backTest.tradeStrategy.sellRule
        backTest.startDate = parseDate(params.startDateFa as String)
        backTest.endDate = parseDate(params.endDateFa as String)
        use(TimeCategory) {
            backTest.currentDate = backTest.startDate - 1
        }
        backTest.status = BackTestHelper.STATUS_WAITING
        backTest.owner = springSecurityService.currentUser as User
        backTest.save(flush: true)
        redirect(action: 'view', id: backTest.id)
    }

    def view() {
        render "visualizing back test: #${params.id}"
    }

    private static Date parseDate(String date) {
        if (!date || date.trim() == '' || date.trim() == 'null')
            return null
        def dateParts = date.split("/").collect { it as Integer }
        new JalaliCalendar(dateParts[0], dateParts[1], dateParts[2]).toJavaUtilGregorianCalendar().time
    }
}
