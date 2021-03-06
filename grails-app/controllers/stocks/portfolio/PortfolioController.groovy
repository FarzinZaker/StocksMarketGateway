package stocks.portfolio

import grails.converters.JSON
import groovy.time.TimeCategory
import stocks.Broker
import stocks.DateHelper
import stocks.PortfolioReportHelper
import stocks.User
import stocks.portfolio.portfolioItems.PortfolioBankItem
import stocks.portfolio.portfolioItems.PortfolioBrokerItem
import stocks.portfolio.portfolioItems.PortfolioBusinessPartnerItem
import stocks.portfolio.meta.*
import stocks.util.ClassResolver
import stocks.util.StringHelper

import java.beans.Introspector

class PortfolioController {
    def springSecurityService
    def priceService
    def portfolioPropertyManagementService
    def portfolioReportService

    def index() {
        redirect(action: 'list')
    }

    def build() {
        Portfolio portfolio = null
        def portfolioAvailItems = []
        def portfolioAvailBrokers = []
        if (params.id) {
            portfolio = Portfolio.get(params.id)
            portfolioAvailItems = PortfolioAvailItem.findAllByPortfolio(portfolio)
            portfolioAvailBrokers = PortfolioAvailBroker.findAllByPortfolio(portfolio)
        }
        [portfolioInstance: portfolio, portfolioAvailItems: portfolioAvailItems, portfolioAvailBrokers: portfolioAvailBrokers, items: portfolioPropertyManagementService.portfolioItemTypes()]
    }

    def jsonSearchBroker() {
        render "[${Broker.findAllByNameLike("%${params.term ?: ''}%").collect { "{\"name\":\"${it.name}\", \"id\":\"${it.id}\"}" }.join(',')}]"
    }

    def save() {
        def portfolio
        if (params.id) {
            portfolio = Portfolio.get(params.id)
            portfolio.properties = params
            PortfolioAvailItem.findAllByPortfolio(portfolio).each {
                if (!params.containsKey(it.item) || portfolio.defaultItems)
                    it.delete(flush: true)
            }
            PortfolioAvailBroker.findAllByPortfolio(portfolio).each {
                if (!params.broker?.contains(it.broker.name) || !portfolio.useBroker) {
                    it.delete(flush: true)
                }
            }
        } else {
            def user = springSecurityService.currentUser as User
            if (Portfolio.findByOwnerAndNameAndDeleted(user, params.name, false)) {
                flash.data = params.name
                flash.message = message(code: 'portfolio.name.repetitive')
                redirect(action: 'build')
            }
            portfolio = new Portfolio(params)
            portfolio.owner = user
        }

        if (portfolio.save(flush: true)) {
            if (!portfolio.defaultItems) {
                def items = portfolioPropertyManagementService.portfolioItemTypes()
                items.each {
                    if (params[it.clazz])
                        if (!PortfolioAvailItem.findByItemAndPortfolio(it.clazz, portfolio)) {
                            def availItem = new PortfolioAvailItem(item: it.clazz, portfolio: portfolio)
                            if (!availItem.save(flush: true))
                                println(availItem.errors)
                        }
                }
            }
            if (portfolio.useBroker) {
                if (params.broker) {
                    params.broker.split(',').each {
                        def broker = Broker.findByName(it?.trim())
                        if (!PortfolioAvailBroker.findByBrokerAndPortfolio(broker, portfolio)) {
                            def availBroker = new PortfolioAvailBroker(broker: broker, portfolio: portfolio)
                            if (!availBroker.save(flush: true))
                                println(availBroker.errors)
                        }
                    }
                }
            }

            portfolioReportService.ensureEmptyReports(portfolio)

            redirect(action: 'list')
        } else if (portfolio.save()) {  //retry
            portfolioReportService.ensureEmptyReports(portfolio)
            redirect(action: 'list')
        }
    }

    def list() {
        if (!springSecurityService.loggedIn) {
            redirect(controller: 'help', action: 'portfolio')
        }
    }

    def jsonList() {

        def value = [:]
        def parameters = [offset: params.skip, max: params.pageSize, sort: params["sort[0][field]"] ?: "name", order: params["sort[0][dir]"] ?: "asc"]

        def owner = springSecurityService.currentUser
        def list = Portfolio.findAllByOwnerAndDeleted(owner, false, parameters)
        value.total = Portfolio.countByOwnerAndDeleted(owner, false)

        value.data = list.collect {
            [
                    id  : it.id,
                    name: it.name
            ]
        }

        render value as JSON
    }

    def portfolioView() {
        [portfolio: Portfolio.get(params.id)]
    }

    def jsonPortfolioCashView() {

        def value = [:]
        def parameters = [offset: params.skip, max: params.pageSize, sort: params["sort[0][field]"], order: params["sort[0][dir]"] ?: "asc"]

        def owner = springSecurityService.currentUser
        def portfolio = Portfolio.get(params.id)
        if (portfolio.ownerId != owner.id)
            return render([] as JSON)
        def list = PortfolioCashItem.findAllByPortfolioAndShareCountNotEqual(portfolio, 0, parameters)
        value.total = PortfolioCashItem.countByPortfolioAndShareCountNotEqual(portfolio, 0)

        def totalValue = 0
        def totalCost = 0
        value.data = list.collect {
            def clazz = Introspector.decapitalize(it.itemType.split('\\.').last())
            def shareValue = portfolioPropertyManagementService.getCurrentValueOfProperty(clazz, it.propertyId) ?: it.cost / it.shareCount
            def curVal = it.shareCount * shareValue
            totalCost += it.avgBuyCost * it.shareCount
            totalValue += curVal
            [
                    id               : it.id,
                    clazz            : clazz,
                    clazzTitle       : message(code: it.itemType),
                    symbol           : it.propertyTitle,
                    shareCount       : it.shareCount,
                    cost             : it.avgBuyCost * it.shareCount,
                    avgPrice         : it.avgBuyCost,
                    shareValue       : shareValue,
                    currentValue     : curVal,
                    profitLoss       : curVal - it.avgBuyCost * it.shareCount,
                    profitLossPercent: (curVal - it.avgBuyCost * it.shareCount) * 100 / (it.avgBuyCost * it.shareCount)
            ]
        }
        value.data.each {
            it.totalProfitLossPercent = Math.round((totalValue - totalCost) * 100 / totalCost)
        }
        def model = [:]
        model.gridData = value
        render model as JSON
    }

    def jsonPortfolioPropertyView() {

        def value = [:]
        def parameters = [offset: params.skip, max: params.pageSize, sort: params["sort[0][field]"], order: params["sort[0][dir]"] ?: "asc"]

        def owner = springSecurityService.currentUser
        def portfolio = Portfolio.get(params.id)
        if (portfolio.ownerId != owner.id)
            return render([] as JSON)
        def list = PortfolioPropertyItem.findAllByPortfolioAndShareCountNotEqual(portfolio, 0, parameters)
        value.total = PortfolioPropertyItem.countByPortfolioAndShareCountNotEqual(portfolio, 0)

        def totalValue = 0
        def totalCost = 0
        value.data = list.collect {
            def clazz = Introspector.decapitalize(it.itemType.split('\\.').last())
            def shareValue = portfolioPropertyManagementService.getCurrentValueOfProperty(clazz, it.propertyId) ?: it.cost / it.shareCount
            def curVal = it.shareCount * shareValue
            totalCost += it.avgBuyCost * it.shareCount
            totalValue += curVal
            [
                    id               : it.id,
                    clazz            : clazz,
                    clazzTitle       : message(code: it.itemType),
                    symbol           : it.propertyTitle,
                    shareCount       : it.shareCount,
                    cost             : it.avgBuyCost * it.shareCount,
                    avgPrice         : it.avgBuyCost,
                    shareValue       : shareValue,
                    currentValue     : curVal,
                    profitLoss       : curVal - it.avgBuyCost * it.shareCount,
                    profitLossPercent: (curVal - it.avgBuyCost * it.shareCount) * 100 / (it.avgBuyCost * it.shareCount)
            ]
        }
        value.data.each {
            it.totalProfitLossPercent = Math.round((totalValue - totalCost) * 100 / totalCost)
        }


        def shareChartData = [:]
        shareChartData.categories = value.data.collect { [name: it.clazzTitle, drilldown: it.clazz] }.unique()
        shareChartData.drilldown = shareChartData.categories.collect { category ->
            [
                    id  : category.drilldown,
                    name: category.name,
                    data: value.data.findAll { it.clazz == category.drilldown }.collect { item ->
                        [
                                name : item.symbol,
                                y    : Math.round(item.currentValue / totalValue * 1000) / 10,
                                value: item.currentValue
                        ]
                    }
            ]
        }

        shareChartData.categories.each { category ->
            category.y = shareChartData.drilldown.findAll { it.id == category.drilldown }.collect {
                it.data
            }.first().sum { it.y }
            category.value = shareChartData.drilldown.findAll { it.id == category.drilldown }.collect {
                it.data
            }.first().sum { it.value }
        }

        def model = [:]
        model.gridData = value
        model.shareChartData = shareChartData
        render model as JSON
    }

    def portfolioItemView() {

    }

    def jsonPortfolioItemView() {

        def value = [:]
        def parameters = [offset: params.skip, max: params.pageSize, sort: params["sort[0][field]"] ?: "actionDate", order: params["sort[0][dir]"] ?: "asc"]

        def owner = springSecurityService.currentUser
        def portfolioItem = PortfolioItem.get(params.id)
        if (portfolioItem.portfolio?.ownerId != owner.id)
            return render([] as JSON)
        def list = PortfolioAction.findAllByPortfolioItem(portfolioItem, parameters)
        value.total = PortfolioAction.countByPortfolioItem(portfolioItem)

        value.data = list.collect {
            [
                    id        : it.id,
                    actionType: message(code: "portfolioAction.actionType.${it.actionType}"),
                    actionDate: DateHelper.jalali(it.actionDate, true),
                    sharePrice: it.sharePrice,
                    shareCount: it.shareCount
            ]
        }

        render value as JSON
    }

    def portfolioManage() {
        def portfolio = Portfolio.get(params.id)
        def brokers = []
        if (portfolio.useBroker) {
            def avalBrokers = PortfolioAvailBroker.findAllByPortfolio(portfolio)
            brokers = (avalBrokers?.broker ?: Broker.findAllByDeleted(false)).collect {
                [
                        brokerId  : it.id,
                        brokerName: it.name
                ]
            }
        }
        def availItems = []
        if (portfolio.defaultItems) {
            availItems = portfolioPropertyManagementService.portfolioItemTypes().findAll { it.default }
        } else {
            def avails = PortfolioAvailItem.findAllByPortfolio(portfolio)
            availItems = portfolioPropertyManagementService.portfolioItemTypes().findAll {
                avails.find { av -> it.clazz == av.item }
            }
        }

        [
                portfolio    : portfolio,
                propertyTypes: availItems,
                accountTypes : [
                        PortfolioBankItem.class,
                        PortfolioBusinessPartnerItem.class,
                        PortfolioBrokerItem.class
                ].collect {
                    [clazz: Introspector.decapitalize(it.name.split('\\.').last()), title: message(code: it.name), modifiable: it.name.split('\\.').last() == 'PortfolioBrokerItem']
                }.sort { it.title },
                brokers      : brokers
        ]
    }

    def jsonPortfolioActions() {
        def value = [:]

        def owner = springSecurityService.currentUser
        def _portfolio = Portfolio.get(params.id)
        if (_portfolio.ownerId != owner.id)
            return render([] as JSON)

        def id = params.long("id")
        def list = PortfolioAction.createCriteria().list {
            portfolioItem {
                portfolio {
                    idEq(id)
                }
            }
            firstResult(params.int("skip") ?: 0)
            maxResults(params.int("pageSize") ?: 20)
            order(params["sort[0][field]"] ?: "actionDate", params["sort[0][dir]"] ?: "desc")
        }

        value.total = PortfolioAction.createCriteria().get {
            "portfolioItem" {
                "portfolio" {
                    idEq(id)
                }
            }
            projections {
                count('id')
            }
        }

        value.data = list.collect {
            [
                    id        : it.id,
                    symbol    : "${it.portfolioItem.symbol.persianCode} - ${it.portfolioItem.symbol.persianName}",
                    actionType: message(code: "portfolioAction.actionType.${it.actionType}"),
                    actionDate: DateHelper.jalali(it.actionDate, true),
                    sharePrice: it.sharePrice,
                    shareCount: it.shareCount
            ]
        }

        render value as JSON
    }

    def delete() {

        def portfolio = Portfolio.get(params.id)
        portfolio.deleted = true
        render(portfolio.save() ? '1' : '0')
    }

    def propertyForm() {
        [
                clazz      : params.clazz,
                id         : params.id ?: '',
                portfolioId: params.portfolioId,
                item       : portfolioPropertyManagementService.getProperty(params.clazz as String, params.id == "" ? null : params.id as Long)
        ]
    }

    def saveProperty() {
        if (!params.clazz)
            render 1
        else if (portfolioPropertyManagementService.saveProperty(params.clazz as String, params.id == "" ? null : params.id as Long, params))
            render 1
        else
            redirect(action: 'propertyForm', params: [clazz: params.clazz, id: params.id])
    }

    def deleteProperty() {
        if (portfolioPropertyManagementService.deleteProperty(params.clazz as String, params.id == "" ? null : params.id as Long))
            render 1
    }

    def benefitLoss() {
        [portfolio: Portfolio.get(params.id)]
    }

    def benefitLossJson() {
        render (PortfolioReport.findByPortfolioAndType(Portfolio.get(params.id), PortfolioReportHelper.REPORT_BENEFIT_LOSS)?.data)
    }

    private static void increaseMapItemValue(Map<String, Long> map, String key, Long value) {
        if (!map.containsKey(key))
            map.put(key, 0)
        map[key] += value
    }

}
