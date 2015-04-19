package stocks

import fi.joensuu.joyds1.calendar.JalaliCalendar
import grails.converters.JSON
import groovy.time.TimeCategory
import stocks.rate.CoinFuture
import stocks.rate.Currency
import stocks.rate.Metal
import stocks.rate.Coin
import stocks.rate.event.CoinFutureEvent
import stocks.tools.CorrelationServiceBase

import javax.persistence.OrderBy

class ToolsController {

    def correlationService
    def snapshotService

    def calculator() {
        [
                dollarPrice: Currency.findBySymbol('us-dollar')?.price,
                onsPrice   : Metal.findBySymbol('ons')?.price,
                coinPrice  : Coin.findBySymbol('o-coin')?.price,
                contracts  : CoinFuture.findAllByContractCodeLikeAndLastTradingDateGreaterThanEquals('GC%', new Date()).collect { future ->
                    def remainingDays = 0
                    use(TimeCategory) {
                        def duration = future.lastTradingDate - new Date()
                        remainingDays = duration.days
                        if (duration.hours > 0)
                            remainingDays++

                    }
                    def lastTradedPrice = future.lastTradedPrice
                    if (!lastTradedPrice || lastTradedPrice == 0)
                        lastTradedPrice = CoinFutureEvent.createCriteria().list {
                            data {
                                eq('id', future.id)
                            }
                            gt('lastTradedPrice', 0D)
                            order('creationDate', ORDER_DESCENDING)
                            maxResults(1)
                            projections {
                                property('lastTradedPrice')
                            }
                        }.find()
                    [
                            name           : future.contractDescription,
                            lastTradingDate: format.jalaliDate(date: future.lastTradingDate),
                            remainingDays  : remainingDays,
                            lastTradedPrice: lastTradedPrice
                    ]
                }
        ]
    }

    def calculatorJson() {

        render([
                dollarPrice: Currency.findBySymbol('us-dollar')?.price,
                onsPrice   : Metal.findBySymbol('ons')?.price,
                coinPrice  : Coin.findBySymbol('o-coin')?.price,
                contracts  : CoinFuture.findAllByContractCodeLikeAndLastTradingDateGreaterThanEquals('GC%', new Date()).collect { future ->
                    def remainingDays = 0
                    use(TimeCategory) {
                        def duration = future.lastTradingDate - new Date()
                        remainingDays = duration.days
                        if (duration.hours > 0)
                            remainingDays++

                    }
                    [
                            name           : future.contractDescription,
                            lastTradingDate: format.jalaliDate(date: future.lastTradingDate),
                            remainingDays  : remainingDays,
                            lastTradedPrice: future.lastTradedPrice
                    ]
                }
        ] as JSON)
    }

    def correlation() {
        def groups = grailsApplication.serviceClasses.findAll { service ->
            service.fullName.contains('stocks.tools.correlation')
        }.collect { [text: it.propertyName + ".name", value: it.fullName] }
        [groups: groups]
    }

    def correctSnapshots() {
        snapshotService.applyPreviousSnapshots(params.domain ?: '.')
    }

    def correlationAutoComplete() {
        def term = params."filter[filters][0][value]"?.toString() ?: ''
        def serviceClass = grailsApplication.serviceClasses.find { service ->
            service.fullName == params.group
        }
        def service = grailsApplication.mainContext[serviceClass.propertyName] as CorrelationServiceBase
        def result = service.searchItems(term)
        if (params.role == "target")
            result = [[value: 'all', text: message(code: "${serviceClass.propertyName}.all")]] + result
        render([data: result] as JSON)
    }

    def correlationGrid() {
        def data = correlationService.calculateSeries(params.sourceGroup, params.sourceItem, params.targetGroup, params.targetItem, parseDate(params.startDate), parseDate(params.endDate), params.period)
        render(template: "/tools/correlation/grid", model: [data: data])
    }

    def correlationChart() {
        render(template: "/tools/correlation/chart", model: [params: params])
    }

    def correlationChartData() {
        def serviceClass = grailsApplication.serviceClasses.find { service ->
            service.fullName == params.group
        }
        def service = grailsApplication.mainContext[serviceClass.propertyName] as CorrelationServiceBase
        def data = service.getItemValues(params.item?.toString(), parseDate(params.startDate?.toString()), parseDate(params.endDate?.toString()), params.period?.toString())
        def name = service.getItemName(params.item?.toString())
        render([
                name: name,
                data: data.collect {
                    [it.date.time, it.value]
                }.sort { it[0] }
        ] as JSON)
    }

    private static Date parseDate(String date) {
        if (!date || date.trim() == '' || date.trim() == 'null')
            return null
        def dateParts = date.split("/").collect { it as Integer }
        new JalaliCalendar(dateParts[0], dateParts[1], dateParts[2]).toJavaUtilGregorianCalendar().time
    }

}
