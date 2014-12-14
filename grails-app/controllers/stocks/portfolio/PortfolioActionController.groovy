package stocks.portfolio

import grails.converters.JSON
import stocks.tse.Symbol

import java.text.DateFormat
import java.text.SimpleDateFormat

class PortfolioActionController {
    def springSecurityService

    DateFormat df = new SimpleDateFormat("EEE MMM dd yyyy kk:mm:ss 'GMT'Z '('z')'", Locale.ENGLISH);

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
            gridJson it
        }

        render value as JSON

    }

    def gridJson = {
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
                actionDate: df.format(it.actionDate),
                sharePrice: it.sharePrice,
                shareCount: it.shareCount
        ]
    }

    def portfolioActionCreate() {
        def result = []
        JSON.parse(params.models).each {

            def signedShareCount = (it.actionType.actionTypeId == 'b') ? it.shareCount : - it.shareCount
            def signedCost = it.sharePrice * signedShareCount

            def action = new PortfolioAction()

            // date sample: Mon Dec 01 2014 01:00:00 GMT+0330 (IRST)
            action.actionDate = df.parse(it.actionDate);

            action.actionType = it.actionType.actionTypeId

            def portfolio = Portfolio.get(params.long("id"))
            def symbol = Symbol.get(it.symbol.symbolId)
            def portfolioItem = PortfolioItem.findByPortfolioAndSymbol(portfolio, symbol)
            if (!portfolioItem)
                portfolioItem = new PortfolioItem(symbol: symbol, portfolio: portfolio, shareCount: 0, cost: 0)

            portfolioItem.shareCount += signedShareCount
            portfolioItem.cost += signedCost
            action.shareCount = it.shareCount
            action.sharePrice = it.sharePrice
            action.actionDescription = "-" // todo

            action.portfolioItem = portfolioItem

            portfolioItem.addToActions(action)
            portfolioItem.save(flush: true)

            result << gridJson(action)
        }
        render ([data: result] as JSON)
    }

    def portfolioActionUpdate() {
        def result = []
        JSON.parse(params.models).each {

            def signedShareCount = (it.actionType.actionTypeId == 'b') ? it.shareCount : - it.shareCount
            def signedCost = it.sharePrice * signedShareCount

            def action = PortfolioAction.get(it.id)
            // date sample: Mon Dec 01 2014 01:00:00 GMT+0330 (IRST)

            if (action.portfolioItem.symbol.id != it.symbol.symbolId) {
                def symbol = Symbol.get(it.symbol.symbolId)
                Portfolio portfolio = action.portfolioItem.portfolio
                def oldPortfolioItem = action.portfolioItem
                oldPortfolioItem.removeFromActions(action)
                def newPortfolioItem = PortfolioItem.findByPortfolioAndSymbol(portfolio, symbol)
                if (!newPortfolioItem)
                    newPortfolioItem = new PortfolioItem(symbol: symbol, portfolio: portfolio, shareCount: 0, cost: 0)
                newPortfolioItem.shareCount += signedShareCount
                newPortfolioItem.cost += signedCost
                if (!oldPortfolioItem.actions)
                    oldPortfolioItem.delete()
                else {
                    oldPortfolioItem.cost -= action.signedCost
                    oldPortfolioItem.shareCount -= action.signedShareCount
                    oldPortfolioItem.save()
                }
                action.portfolioItem = newPortfolioItem
            } else {
                action.portfolioItem.shareCount += (signedShareCount - action.signedShareCount)
                action.portfolioItem.cost += (signedCost - action.signedCost)
            }

            action.actionDate = df.parse(it.actionDate)
            action.actionType = it.actionType.actionTypeId
            action.shareCount = it.shareCount
            action.sharePrice = it.sharePrice

            action.portfolioItem.save(flush: true)
            result << gridJson(action)
        }
        render ([data: result] as JSON)
    }

    def portfolioActionDelete() {
        JSON.parse(params.models).each {
            def item = PortfolioAction.get(it.id)
            def portfolioItem = item.portfolioItem
            portfolioItem.removeFromActions(item)
            if (portfolioItem.actions) {
                portfolioItem.shareCount -= item.signedShareCount
                portfolioItem.cost -= item.signedCost
            }
            item.delete()
            if (!portfolioItem.actions)
                portfolioItem.delete()

        }
        render 0
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
//                { Symbol item ->
//                    !(0..9).contains(item.persianCode.charAt(item.persianCode.size() - 1)) &&
//                            (item.persianCode.charAt(0) != 'ج' || item.persianCode.charAt(1) != ' ') &&
//                            ['300', '400', '309', '404'].contains(item.type) &&
//                            item.marketCode == 'NO'
//                }
    }
}
