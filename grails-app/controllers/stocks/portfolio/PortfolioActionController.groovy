package stocks.portfolio

import grails.converters.JSON
import stocks.tse.Symbol

class PortfolioActionController {
    def springSecurityService

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
                    symbol: [
                        symbolId: it.portfolioItem.symbol.id,
                        symbolTitle: "${it.portfolioItem.symbol.persianCode} - ${it.portfolioItem.symbol.persianName}"
                    ],
                    actionType: [
                        actionTypeId: it.actionType,
                        actionTypeTitle: message(code: "portfolioAction.actionType.${it.actionType}")
                    ],
                    actionDate: stocks.DateHelper.jalali(it.actionDate, true),
                    sharePrice: it.sharePrice,
                    shareCount: it.shareCount
            ]
        }

        render value as JSON

    }

    def portfolioActionUpdate() {
        def id = 0
        JSON.parse(params.models).each {
            def action = PortfolioAction.get(it.id)
            //item.actionDate = it.actionDate
            action.actionType = it.actionType.actionTypeId
            if (action.portfolioItem.symbol.id != it.symbol.symbolId) {
                def symbol = Symbol.get(it.symbol.symbolId)
                Portfolio portfolio = action.portfolioItem.portfolio
                def oldPortfolioItem = action.portfolioItem
                oldPortfolioItem.removeFromActions(action)
                def newPortfolioItem = PortfolioItem.findByPortfolioAndSymbol(portfolio, symbol)
                if (!newPortfolioItem)
                    newPortfolioItem = new PortfolioItem(symbol: symbol, portfolio: portfolio)
                newPortfolioItem.shareCount += it.shareCount
                newPortfolioItem.cost += it.shareCount * it.sharePrice
                if (!oldPortfolioItem.actions)
                    oldPortfolioItem.delete()
                action.portfolioItem = newPortfolioItem
            } else {
                action.portfolioItem.shareCount += (it.shareCount - action.shareCount)
                action.portfolioItem.cost += (it.shareCount * it.sharePrice - action.shareCount * action.sharePrice)
            }
            action.shareCount = it.shareCount
            action.sharePrice = it.sharePrice
            action.portfolioItem.save(flush: true)
            id = action.id
        }
        render id
    }

    def portfolioActionDelete() {
        JSON.parse(params.models).each {
            def item = PortfolioAction.get(it.id)
            item.delete(flush: true)
        }
        render 0
    }

    def portfolioActionCreate() {

    }

    def symbols() {
        def symbols

        String query = params["filter[filters][0][value]"]
        if (params.id) {
            def currentSymbol = Symbol.get(params.long("id"))
            if (query) {
                if (query.contains(currentSymbol.persianCode) || query.contains(currentSymbol.persianName) ||
                        query.contains(currentSymbol.code) || query.contains(currentSymbol.name))
                    query = currentSymbol.persianCode
            } else {
                query = currentSymbol.companySmallCode
            }
        }
        if (query)
            symbols = Symbol.search("*${query}*", max: 20).results
        else
            symbols = Symbol.list([max: 20])
        def jsonSymbols = symbols.collect {
            [symbolId: it.id, symbolTitle: "${it.persianCode} - ${it.persianName}"]
        } as JSON
        render jsonSymbols
    }
}
