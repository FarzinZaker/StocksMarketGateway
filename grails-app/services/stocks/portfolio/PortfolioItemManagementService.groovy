package stocks.portfolio

import stocks.portfolio.portfolioItems.PortfolioCurrencyItem
import stocks.rate.Currency
import stocks.tse.Symbol

import java.text.DateFormat
import java.text.SimpleDateFormat

class PortfolioItemManagementService {

    def springSecurityService
    DateFormat df = new SimpleDateFormat("EEE MMM dd yyyy kk:mm:ss 'GMT'Z '('z')'", Locale.ENGLISH);

    def create(Long portfolioId, Map model) {
        switch (model.itemType.clazz) {
//            case 'portfolioBankItem':
//                return createBank(portfolioId, model)
//            case 'portfolioBondsItem':
//                return createBonds(portfolioId, model)
//            case 'portfolioBullionItem':
//                return createBullion(portfolioId, model)
//            case 'portfolioBusinessPartnerItem':
//                return createBusinessPartner(portfolioId, model)
//            case 'portfolioCoinItem':
//                return createCoin(portfolioId, model)
            case 'portfolioCurrencyItem':
                return createCurrency(portfolioId, model)
//            case 'portfolioCustomBondsItem':
//                return createCustomBonds(portfolioId, model)
//            case 'portfolioCustomSymbolItem':
//                return createCustomSymbol(portfolioId, model)
//            case 'portfolioCustomSymbolPriorityItem':
//                return createCustomSymbolPriority(portfolioId, model)
//            case 'portfolioImmovableItem':
//                return createImmovableProperty(portfolioId, model)
//            case 'portfolioMovableItem':
//                return createMovableProperty(portfolioId, model)
//            case 'portfolioSymbolItem':
//                return createSymbol(portfolioId, model)
//            case 'portfolioSymbolPriorityItem':
//                return createSymbolPriority(portfolioId, model)
        }
    }
    
    def createCurrency(Long portfolioId, Map model){

        def signedShareCount = (model.actionType.actionTypeId == 'b') ? model.shareCount : - model.shareCount
        def signedCost = model.sharePrice * signedShareCount

        def action = new PortfolioAction()

        // date sample: Mon Dec 01 2014 01:00:00 GMT+0330 (IRST)
        action.actionDate = df.parse(model.actionDate);

        action.actionType = model.actionType.actionTypeId

        def portfolio = Portfolio.get(portfolioId)
        def currency = Currency.get(model.property.propertyId)
        def portfolioItem = PortfolioCurrencyItem.findByPortfolioAndCurrency(portfolio, currency)
        if (!portfolioItem)
            portfolioItem = new PortfolioCurrencyItem(currency: currency, portfolio: portfolio, shareCount: 0, cost: 0)

        portfolioItem.shareCount += signedShareCount
        portfolioItem.cost += signedCost
        action.shareCount = model.shareCount
        action.sharePrice = model.sharePrice
        action.actionDescription = "-" // todo

        action.portfolioItem = portfolioItem

        portfolioItem.addToActions(action)
        portfolioItem.save(flush: true)
    }
}
