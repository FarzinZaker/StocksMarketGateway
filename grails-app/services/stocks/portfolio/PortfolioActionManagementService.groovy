package stocks.portfolio

import stocks.Broker
import stocks.portfolio.basic.BankAccount
import stocks.portfolio.basic.BusinessPartner
import stocks.portfolio.basic.CustomBonds
import stocks.portfolio.basic.CustomSymbol
import stocks.portfolio.basic.CustomSymbolPriority
import stocks.portfolio.basic.ImmovableProperty
import stocks.portfolio.basic.MovableProperty
import stocks.portfolio.portfolioItems.PortfolioBankItem
import stocks.portfolio.portfolioItems.PortfolioBondsItem
import stocks.portfolio.portfolioItems.PortfolioBrokerItem
import stocks.portfolio.portfolioItems.PortfolioBullionItem
import stocks.portfolio.portfolioItems.PortfolioBusinessPartnerItem
import stocks.portfolio.portfolioItems.PortfolioCoinItem
import stocks.portfolio.portfolioItems.PortfolioCurrencyItem
import stocks.portfolio.portfolioItems.PortfolioCustomBondsItem
import stocks.portfolio.portfolioItems.PortfolioCustomSymbolItem
import stocks.portfolio.portfolioItems.PortfolioCustomSymbolPriorityItem
import stocks.portfolio.portfolioItems.PortfolioImmovableItem
import stocks.portfolio.portfolioItems.PortfolioMovableItem
import stocks.portfolio.portfolioItems.PortfolioSymbolItem
import stocks.portfolio.portfolioItems.PortfolioSymbolPriorityItem
import stocks.rate.Coin
import stocks.rate.Currency
import stocks.tse.Symbol

import java.beans.Introspector
import java.text.DateFormat
import java.text.SimpleDateFormat

class PortfolioActionManagementService {

    def springSecurityService
    DateFormat df = new SimpleDateFormat("EEE MMM dd yyyy kk:mm:ss 'GMT'Z '('z')'", Locale.ENGLISH);

    def save(Long portfolioId, Map model, PortfolioAction parentAction = null) {

        def portfolio = Portfolio.get(portfolioId)

        def action
        if (model.id == 0)
            action = new PortfolioAction()
        else
            action = PortfolioAction.get(model.id)

        def portfolioItem = getPortfolioItem(portfolio, model)
        if (action.id) {
            def oldPortfolioItem = action.portfolioItem
            if (Introspector.decapitalize(action.portfolioItem?.itemType?.split('\\.')?.last()) != model.itemType.clazz
                    || action.portfolioItem?.propertyId != model.property.propertyId) {
                action.portfolioItem = null
                action.save()
                if (!PortfolioAction.countByPortfolioItem(oldPortfolioItem)) {
                    oldPortfolioItem.delete()
                } else {
                    oldPortfolioItem.cost -= action.signedCost
                    oldPortfolioItem.shareCount -= action.signedShareCount
                    oldPortfolioItem.save()
                }
            } else {
                oldPortfolioItem.cost -= action.signedCost
                oldPortfolioItem.shareCount -= action.signedShareCount
                oldPortfolioItem.save()
            }
        }
        portfolioItem.save(flush: true)

        // date sample: Mon Dec 01 2014 01:00:00 GMT+0330 (IRST)
        action.actionDate = parentAction ? parentAction.actionDate : df.parse(model.actionDate);
        action.actionType = model.actionType.actionTypeId
        action.shareCount = model.shareCount ?: 1
        action.sharePrice = model.sharePrice
        action.broker = model.broker?.brokerId ? Broker.get(model.broker?.brokerId as Long) : null
        action.discount = model.discount ? model.discount as Float : null
        action.wage = model.wage ? model.wage as Float : null
        action.actionDescription = "-" // todo
        action.portfolioItem = portfolioItem
        action.portfolio = portfolio
        action.parentAction = parentAction
        action.save(flush: true)

        model.childActions?.each { childAction ->
            save(portfolioId, childAction, action)
        }

        action
    }

    def delete(Long actionId) {
        def item = PortfolioAction.get(actionId)
        PortfolioAction.findAllByParentAction(item).each { childAction ->
            delete(childAction.id)
        }
        def portfolioItem = item.portfolioItem
        def signedShareCount = item.signedShareCount
        def signedCost = item.signedCost
        item.delete()
        if (PortfolioAction.findAllByPortfolioItem(portfolioItem)) {
            portfolioItem.shareCount -= signedShareCount
            portfolioItem.cost -= signedCost
        } else {
            if (!PortfolioAction.findAllByPortfolioItem(portfolioItem))
                portfolioItem.delete()
        }
    }

    def getPortfolioItem(Portfolio portfolio, Map model) {
        switch (model.itemType.clazz) {
            case 'portfolioBankItem':
                return getBankPortfolioItem(portfolio, model)
            case 'portfolioBondsItem':
                return getBondsPortfolioItem(portfolio, model)
            case 'portfolioBullionItem':
                return getBullionPortfolioItem(portfolio, model)
            case 'portfolioBrokerItem':
                return getBrokerPortfolioItem(portfolio, model)
            case 'portfolioBusinessPartnerItem':
                return getBusinessPartnerPortfolioItem(portfolio, model)
            case 'portfolioCoinItem':
                return getCoinPortfolioItem(portfolio, model)
            case 'portfolioCurrencyItem':
                return getCurrencyPortfolioItem(portfolio, model)
            case 'portfolioCustomBondsItem':
                return getCustomBondsPortfolioItem(portfolio, model)
            case 'portfolioCustomSymbolItem':
                return getCustomSymbolPortfolioItem(portfolio, model)
            case 'portfolioCustomSymbolPriorityItem':
                return getCustomSymbolPriorityPortfolioItem(portfolio, model)
            case 'portfolioImmovableItem':
                return getImmovablePropertyPortfolioItem(portfolio, model)
            case 'portfolioMovableItem':
                return getMovablePropertyPortfolioItem(portfolio, model)
            case 'portfolioSymbolItem':
                return getSymbolPortfolioItem(portfolio, model)
            case 'portfolioSymbolPriorityItem':
                return getSymbolPriorityPortfolioItem(portfolio, model)
        }
    }

    def getBankPortfolioItem(Portfolio portfolio, Map model) {
        def signedShareCount = (model.actionType.actionTypeId == 'd') ? 1 : -1
        def signedCost = model.sharePrice * signedShareCount

        def bank = BankAccount.get(model.property.propertyId as Long)
        def portfolioItem = PortfolioBankItem.findByPortfolioAndBankAccount(portfolio, bank)
        if (!portfolioItem)
            portfolioItem = new PortfolioBankItem(bankAccount: bank, portfolio: portfolio, shareCount: 0, cost: 0)

        portfolioItem.shareCount += signedShareCount
        portfolioItem.cost += signedCost

        portfolioItem
    }

    def getBondsPortfolioItem(Portfolio portfolio, Map model) {
        def signedShareCount = (model.actionType.actionTypeId == 'b') ? model.shareCount : -model.shareCount
        def signedCost = model.sharePrice * signedShareCount

        def bonds = Symbol.get(model.property.propertyId as Long)
        def portfolioItem = PortfolioBondsItem.findByPortfolioAndBonds(portfolio, bonds)
        if (!portfolioItem)
            portfolioItem = new PortfolioBondsItem(bonds: bonds, portfolio: portfolio, shareCount: 0, cost: 0)

        portfolioItem.shareCount += signedShareCount
        portfolioItem.cost += signedCost

        portfolioItem
    }

    def getBrokerPortfolioItem(Portfolio portfolio, Map model) {
        def signedShareCount = (model.actionType.actionTypeId == 'd') ? 1 : -1
        def signedCost = model.sharePrice * signedShareCount

        def broker = Broker.get(model.property.propertyId as Long)
        def portfolioItem = PortfolioBrokerItem.findByPortfolioAndBroker(portfolio, broker)
        if (!portfolioItem)
            portfolioItem = new PortfolioBrokerItem(broker: broker, portfolio: portfolio, shareCount: 0, cost: 0)

        portfolioItem.shareCount += signedShareCount
        portfolioItem.cost += signedCost

        portfolioItem
    }

    def getBullionPortfolioItem(Portfolio portfolio, Map model) {
        def signedShareCount = (model.actionType.actionTypeId == 'b') ? model.shareCount : -model.shareCount
        def signedCost = model.sharePrice * signedShareCount

        def carat = model.property.propertyId as Integer
        def portfolioItem = PortfolioBullionItem.findByPortfolioAndCarat(portfolio, carat)
        if (!portfolioItem)
            portfolioItem = new PortfolioBullionItem(carat: carat, portfolio: portfolio, shareCount: 0, cost: 0)

        portfolioItem.shareCount += signedShareCount
        portfolioItem.cost += signedCost

        portfolioItem
    }

    def getBusinessPartnerPortfolioItem(Portfolio portfolio, Map model) {
        def signedShareCount = (model.actionType.actionTypeId == 'd') ? 1 : -1
        def signedCost = model.sharePrice * signedShareCount

        def partner = BusinessPartner.get(model.property.propertyId as Long)
        def portfolioItem = PortfolioBusinessPartnerItem.findByPortfolioAndPartner(portfolio, partner)
        if (!portfolioItem)
            portfolioItem = new PortfolioBusinessPartnerItem(partner: partner, portfolio: portfolio, shareCount: 0, cost: 0)

        portfolioItem.shareCount += signedShareCount
        portfolioItem.cost += signedCost

        portfolioItem
    }

    def getCoinPortfolioItem(Portfolio portfolio, Map model) {
        def signedShareCount = (model.actionType.actionTypeId == 'b') ? model.shareCount : -model.shareCount
        def signedCost = model.sharePrice * signedShareCount

        def coin = Coin.get(model.property.propertyId as Long)
        def portfolioItem = PortfolioCoinItem.findByPortfolioAndCoin(portfolio, coin)
        if (!portfolioItem)
            portfolioItem = new PortfolioCoinItem(coin: coin, portfolio: portfolio, shareCount: 0, cost: 0)

        portfolioItem.shareCount += signedShareCount
        portfolioItem.cost += signedCost

        portfolioItem
    }

    def getCurrencyPortfolioItem(Portfolio portfolio, Map model) {
        def signedShareCount = (model.actionType.actionTypeId == 'b') ? model.shareCount : -model.shareCount
        def signedCost = model.sharePrice * signedShareCount

        def currency = Currency.get(model.property.propertyId as Long)
        def portfolioItem = PortfolioCurrencyItem.findByPortfolioAndCurrency(portfolio, currency)
        if (!portfolioItem)
            portfolioItem = new PortfolioCurrencyItem(currency: currency, portfolio: portfolio, shareCount: 0, cost: 0)

        portfolioItem.shareCount += signedShareCount
        portfolioItem.cost += signedCost

        portfolioItem
    }

    def getCustomBondsPortfolioItem(Portfolio portfolio, Map model) {
        def signedShareCount = (model.actionType.actionTypeId == 'b') ? model.shareCount : -model.shareCount
        def signedCost = model.sharePrice * signedShareCount

        def bonds = CustomBonds.get(model.property.propertyId as Long)
        def portfolioItem = PortfolioCustomBondsItem.findByPortfolioAndCustomBonds(portfolio, bonds)
        if (!portfolioItem)
            portfolioItem = new PortfolioCustomBondsItem(customBonds: bonds, portfolio: portfolio, shareCount: 0, cost: 0)

        portfolioItem.shareCount += signedShareCount
        portfolioItem.cost += signedCost

        portfolioItem
    }

    def getCustomSymbolPortfolioItem(Portfolio portfolio, Map model) {
        def signedShareCount = (model.actionType.actionTypeId == 'b') ? model.shareCount : -model.shareCount
        def signedCost = model.sharePrice * signedShareCount

        def symbol = CustomSymbol.get(model.property.propertyId as Long)
        def portfolioItem = PortfolioCustomSymbolItem.findByPortfolioAndCustomSymbol(portfolio, symbol)
        if (!portfolioItem)
            portfolioItem = new PortfolioCustomSymbolItem(customSymbol: symbol, portfolio: portfolio, shareCount: 0, cost: 0)

        portfolioItem.shareCount += signedShareCount
        portfolioItem.cost += signedCost

        portfolioItem
    }

    def getCustomSymbolPriorityPortfolioItem(Portfolio portfolio, Map model) {
        def signedShareCount = (model.actionType.actionTypeId == 'b') ? model.shareCount : -model.shareCount
        def signedCost = model.sharePrice * signedShareCount

        def symbol = CustomSymbolPriority.get(model.property.propertyId as Long)
        def portfolioItem = PortfolioCustomSymbolPriorityItem.findByPortfolioAndCustomSymbolPriority(portfolio, symbol)
        if (!portfolioItem)
            portfolioItem = new PortfolioCustomSymbolPriorityItem(customSymbolPriority: symbol, portfolio: portfolio, shareCount: 0, cost: 0)

        portfolioItem.shareCount += signedShareCount
        portfolioItem.cost += signedCost

        portfolioItem
    }

    def getImmovablePropertyPortfolioItem(Portfolio portfolio, Map model) {
        def signedShareCount = (model.actionType.actionTypeId == 'b') ? 1 : -1
        def signedCost = model.sharePrice * signedShareCount

        def property = ImmovableProperty.get(model.property.propertyId as Long)
        def portfolioItem = PortfolioImmovableItem.findByPortfolioAndImmovableProperty(portfolio, property)
        if (!portfolioItem)
            portfolioItem = new PortfolioImmovableItem(immovableProperty: property, portfolio: portfolio, shareCount: 0, cost: 0)

        portfolioItem.shareCount += signedShareCount
        portfolioItem.cost += signedCost

        portfolioItem
    }

    def getMovablePropertyPortfolioItem(Portfolio portfolio, Map model) {
        def signedShareCount = (model.actionType.actionTypeId == 'b') ? model.shareCount : -model.shareCount
        def signedCost = model.sharePrice * signedShareCount

        def property = MovableProperty.get(model.property.propertyId as Long)
        def portfolioItem = PortfolioMovableItem.findByPortfolioAndMovableProperty(portfolio, property)
        if (!portfolioItem)
            portfolioItem = new PortfolioMovableItem(movableProperty: property, portfolio: portfolio, shareCount: 0, cost: 0)

        portfolioItem.shareCount += signedShareCount
        portfolioItem.cost += signedCost

        portfolioItem
    }

    def getSymbolPortfolioItem(Portfolio portfolio, Map model) {
        def signedShareCount = (model.actionType.actionTypeId == 'b') ? model.shareCount : -model.shareCount
        def signedCost = model.sharePrice * signedShareCount

        def symbol = Symbol.get(model.property.propertyId as Long)
        def portfolioItem = PortfolioSymbolItem.findByPortfolioAndSymbol(portfolio, symbol)
        if (!portfolioItem)
            portfolioItem = new PortfolioSymbolItem(symbol: symbol, portfolio: portfolio, shareCount: 0, cost: 0)

        portfolioItem.shareCount += signedShareCount
        portfolioItem.cost += signedCost

        portfolioItem
    }

    def getSymbolPriorityPortfolioItem(Portfolio portfolio, Map model) {
        def signedShareCount = (model.actionType.actionTypeId == 'b') ? model.shareCount : -model.shareCount
        def signedCost = model.sharePrice * signedShareCount

        def symbol = Symbol.get(model.property.propertyId as Long)
        def portfolioItem = PortfolioSymbolPriorityItem.findByPortfolioAndSymbolPriority(portfolio, symbol)
        if (!portfolioItem)
            portfolioItem = new PortfolioSymbolPriorityItem(symbolPriority: symbol, portfolio: portfolio, shareCount: 0, cost: 0)

        portfolioItem.shareCount += signedShareCount
        portfolioItem.cost += signedCost

        portfolioItem
    }
}
