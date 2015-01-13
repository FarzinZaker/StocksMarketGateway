package stocks.portfolio

import grails.converters.JSON
import stocks.DateHelper

class PortfolioController {
    def springSecurityService
    def priceService

    def build() {
        [portfolio: params.id ? Portfolio.get(params.id) : null]
    }

    def save() {
        def portfolio
        if (params.id) {
            portfolio = Portfolio.get(params.id)
            portfolio.properties = params
        } else {
            portfolio = new Portfolio(params)
            portfolio.owner = springSecurityService.currentUser
            portfolio.creationDate = new Date()
        }
        if (portfolio.save())
            redirect(action: 'list')
        else if (portfolio.save())  //retry
            redirect(action: 'list')
    }

    def list() {

    }

    def jsonList() {

        def value = [:]
        def parameters = [offset: params.skip, max: params.pageSize, sort: params["sort[0][field]"] ?: "name", order: params["sort[0][dir]"] ?: "asc"]

        def owner = springSecurityService.currentUser
        def list = Portfolio.findAllByOwnerAndDeleted(owner, false, parameters)
        value.total = Portfolio.countByOwnerAndDeleted(owner, false)

        value.data = list.collect {
            [
                    id: it.id,
                    name: it.name
            ]
        }

        render value as JSON
    }

    def portfolioView() {

    }

    def jsonPortfolioView() {

        def value = [:]
        def parameters = [offset: params.skip, max: params.pageSize, sort: params["sort[0][field]"] ?: "symbol", order: params["sort[0][dir]"] ?: "asc"]

        def owner = springSecurityService.currentUser
        def portfolio = Portfolio.get(params.id)
        if (portfolio.ownerId != owner.id)
            return render ([] as JSON)
        def list = PortfolioItem.findAllByPortfolioAndShareCountNotEqual(portfolio, 0, parameters)
        value.total = PortfolioItem.countByPortfolioAndShareCountNotEqual(portfolio, 0)

        def totalValue = 0

        value.data = list.collect {
            def shareValue = priceService.lastPrice(it.symbol)
            totalValue += it.shareCount * shareValue
            [
                    id: it.id,
                    symbol: "${it.symbol.persianCode} - ${it.symbol.persianName}",
                    shareCount: it.shareCount,
                    cost: it.cost,
                    avgPrice: String.format("%.2f", it.cost / it.shareCount),
                    shareValue: shareValue,
                    currentValue: it.shareCount * shareValue
            ]
        }

        def shareChartData = []
        for (item in value.data) {
            shareChartData << [item.symbol, Math.round(item.currentValue / totalValue * 1000) / 10]
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
            return render ([] as JSON)
        def list = PortfolioAction.findAllByPortfolioItem(portfolioItem, parameters)
        value.total = PortfolioAction.countByPortfolioItem(portfolioItem)

        value.data = list.collect {
            [
                    id: it.id,
                    actionType: message(code: "portfolioAction.actionType.${it.actionType}"),
                    actionDate: DateHelper.jalali(it.actionDate, true),
                    sharePrice: it.sharePrice,
                    shareCount: it.shareCount
            ]
        }

        render value as JSON
    }

    def portfolioManage() {

    }

    def jsonPortfolioActions() {
        def value = [:]

        def owner = springSecurityService.currentUser
        def _portfolio = Portfolio.get(params.id)
        if (_portfolio.ownerId != owner.id)
            return render ([] as JSON)

        def id = params.long("id")
        def list = PortfolioAction.createCriteria().list {
            portfolioItem {
                portfolio {
                    idEq(id)
                }
            }
            firstResult(params.int("skip")?: 0)
            maxResults(params.int("pageSize")?: 20)
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
                    id: it.id,
                    symbol: "${it.portfolioItem.symbol.persianCode} - ${it.portfolioItem.symbol.persianName}",
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
}
