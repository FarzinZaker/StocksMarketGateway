package stocks.portfolio

import com.google.common.base.CaseFormat
import fi.joensuu.joyds1.calendar.JalaliCalendar
import stocks.Broker
import stocks.GlobalSetting
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
import stocks.portfolio.portfolioItems.PortfolioHousingFacilitiesItem
import stocks.portfolio.portfolioItems.PortfolioImmovableItem
import stocks.portfolio.portfolioItems.PortfolioInvestmentFundItem
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
    def messageSource
    def springSecurityService
    DateFormat df = new SimpleDateFormat("EEE MMM dd yyyy kk:mm:ss 'GMT'Z '('z')'", Locale.ENGLISH);

    def save(Long portfolioId, Map model, PortfolioAction parentAction = null) {

        def portfolio = Portfolio.get(portfolioId)

        def action
        if (model.id)
            action = PortfolioAction.get(model.id)
        else
            action = new PortfolioAction()


        def portfolioItem = getPortfolioItem(portfolio, model)
        if (model.actionType.actionTypeId in ['s', 'w']) {

            def actionDate = df.parse(model.actionDate);
            def prevAmount = PortfolioAction.createCriteria().list {
                eq('portfolioItem', portfolioItem)
                le('actionDate', actionDate)
            }.sum {
                (it.actionType in ['s', 'w'] ? -1 : 1) * it.shareCount
            } ?: 0
            if (prevAmount < (model.shareCount ?: 1)) {
                portfolioItem.discard()
                return [errors: [allErrors: [messageSource.getMessage('portfolio.sharesCount.validation', [prevAmount].toArray(), '', null)]]]

            }
        }

        if (action.id) {
            def oldPortfolioItem = action.portfolioItem
            if (Introspector.decapitalize(action.portfolioItem?.itemType?.split('\\.')?.last()) != model.itemType.clazz
                    || action.portfolioItem?.propertyId != (model.property.propertyId as Long)) {
                action.portfolioItem = null
                action.save()
                if (!PortfolioAction.countByPortfolioItem(oldPortfolioItem)) {
                    oldPortfolioItem.delete()
                } else {
                    if (action.signedCost > 0) {
                        oldPortfolioItem.avgBuyCost = (oldPortfolioItem.avgBuyCost * oldPortfolioItem.shareCount - action.signedCost) / (oldPortfolioItem.shareCount - action.signedShareCount)
                    }
                    oldPortfolioItem.cost -= action.signedCost
                    oldPortfolioItem.shareCount -= action.signedShareCount
                    oldPortfolioItem.save()
                }
            } else {
                if (action.signedCost > 0) {
                    oldPortfolioItem.avgBuyCost = (oldPortfolioItem.avgBuyCost * oldPortfolioItem.shareCount - action.signedCost) / (oldPortfolioItem.shareCount - action.signedShareCount)
                }
                oldPortfolioItem.cost -= action.signedCost
                oldPortfolioItem.shareCount -= action.signedShareCount
                oldPortfolioItem.save()
            }
        }
        portfolioItem.save(flush: true)

        // date sample: Mon Dec 01 2014 01:00:00 GMT+0330 (IRST)
        action.actionDate = parentAction ? parentAction.actionDate : parseDate(model.actionDate);
        action.actionType = model.actionType.actionTypeId
        action.shareCount = model.shareCount ?: 1
        action.sharePrice = model.sharePrice
        action.broker = model.broker?.brokerId ? Broker.get(model.broker?.brokerId as Long) : null
        action.discount = model.discount ? model.discount as Float : null
        action.wage = GlobalSetting.findByName("portfolio.${CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, portfolioItem.class.simpleName.replace('Portfolio', '').replace('Item', ''))}.${model.actionType.actionTypeId == 'b' ? 'buy' : 'sell'}.wage").value as Float
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
        if (item) {
            def items = PortfolioAction.findAllByIdNotEqualAndPortfolioItemAndActionDateGreaterThanEquals(item.id, item.portfolioItem, item.actionDate)
            if ((items?.sum { it.signedShareCount } ?: 0) < 0)
                return [error: 'countLessThanZero']
            PortfolioAction.findAllByParentAction(item).each { childAction ->
                delete(childAction.id)
            }

            def portfolioItem = item.portfolioItem
            def signedShareCount = item.signedShareCount
            def signedCost = item.signedCost
            item.delete()
            if (PortfolioAction.findAllByPortfolioItem(portfolioItem)) {
                if (signedCost > 0) {
                    portfolioItem.avgBuyCost = ((portfolioItem.avgBuyCost ?: 0) * (portfolioItem.shareCount ?: 0) - signedCost) / ((portfolioItem.shareCount ?: 0) - signedShareCount)
                }
                portfolioItem.shareCount -= signedShareCount
                portfolioItem.cost -= signedCost
            } else {
                if (!PortfolioAction.findAllByPortfolioItem(portfolioItem))
                    portfolioItem.delete()
            }
        }
        return [error: false]
    }

    def getPortfolioItem(Portfolio portfolio, Map model) {
        switch (model.itemType.clazz) {
            case 'portfolioBankItem':
                return getBankPortfolioItem(portfolio, model)
            case 'portfolioBondsItem':
                return getBondsPortfolioItem(portfolio, model)
            case 'portfolioInvestmentFundItem':
                return getInvestmentFundPortfolioItem(portfolio, model)
            case 'portfolioHousingFacilitiesItem':
                return getHousingFacilitiesPortfolioItem(portfolio, model)
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

        if (model.actionType.actionTypeId == 'd') {
            portfolioItem.avgBuyCost = (((portfolioItem.avgBuyCost ?: 0) * (portfolioItem.shareCount ?: 0)) + (signedCost)) / (signedShareCount + portfolioItem.shareCount)
        }
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
        if (model.actionType.actionTypeId == 'b') {
            portfolioItem.avgBuyCost = (((portfolioItem.avgBuyCost ?: 0) * (portfolioItem.shareCount ?: 0)) + (signedCost)) / (signedShareCount + portfolioItem.shareCount)
        }

        portfolioItem.shareCount += signedShareCount
        portfolioItem.cost += signedCost

        portfolioItem
    }

    def getInvestmentFundPortfolioItem(Portfolio portfolio, Map model) {
        def signedShareCount = (model.actionType.actionTypeId == 'b') ? model.shareCount : -model.shareCount
        def signedCost = model.sharePrice * signedShareCount

        def symbol = Symbol.get(model.property.propertyId as Long)
        def portfolioItem = PortfolioInvestmentFundItem.findByPortfolioAndFund(portfolio, symbol)
        if (!portfolioItem)
            portfolioItem = new PortfolioInvestmentFundItem(fund: symbol, portfolio: portfolio, shareCount: 0, cost: 0)
        if (model.actionType.actionTypeId == 'b') {
            portfolioItem.avgBuyCost = (((portfolioItem.avgBuyCost ?: 0) * (portfolioItem.shareCount ?: 0)) + (signedCost)) / (signedShareCount + portfolioItem.shareCount)
        }
        portfolioItem.shareCount += signedShareCount
        portfolioItem.cost += signedCost

        portfolioItem
    }

    def getHousingFacilitiesPortfolioItem(Portfolio portfolio, Map model) {
        def signedShareCount = (model.actionType.actionTypeId == 'b') ? model.shareCount : -model.shareCount
        def signedCost = model.sharePrice * signedShareCount

        def symbol = Symbol.get(model.property.propertyId as Long)
        def portfolioItem = PortfolioHousingFacilitiesItem.findByPortfolioAndHousingFacility(portfolio, symbol)
        if (!portfolioItem)
            portfolioItem = new PortfolioHousingFacilitiesItem(housingFacility: symbol, portfolio: portfolio, shareCount: 0, cost: 0)

        if (model.actionType.actionTypeId == 'b') {
            portfolioItem.avgBuyCost = (((portfolioItem.avgBuyCost ?: 0) * (portfolioItem.shareCount ?: 0)) + (signedCost)) / (signedShareCount + portfolioItem.shareCount)
        }
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
        if (model.actionType.actionTypeId == 'd') {
            portfolioItem.avgBuyCost = (((portfolioItem.avgBuyCost ?: 0) * (portfolioItem.shareCount ?: 0)) + (signedCost)) / (signedShareCount + portfolioItem.shareCount)
        }
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
        if (model.actionType.actionTypeId == 'b') {
            portfolioItem.avgBuyCost = (((portfolioItem.avgBuyCost ?: 0) * (portfolioItem.shareCount ?: 0)) + (signedCost)) / (signedShareCount + portfolioItem.shareCount)
        }
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
        if (model.actionType.actionTypeId == 'd') {
            portfolioItem.avgBuyCost = (((portfolioItem.avgBuyCost ?: 0) * (portfolioItem.shareCount ?: 0)) + (signedCost)) / (signedShareCount + portfolioItem.shareCount)
        }
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
        if (model.actionType.actionTypeId == 'b') {
            portfolioItem.avgBuyCost = (((portfolioItem.avgBuyCost ?: 0) * (portfolioItem.shareCount ?: 0)) + (signedCost)) / (signedShareCount + portfolioItem.shareCount)
        }
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
        if (model.actionType.actionTypeId == 'b') {
            portfolioItem.avgBuyCost = (((portfolioItem.avgBuyCost ?: 0) * (portfolioItem.shareCount ?: 0)) + (signedCost)) / (signedShareCount + portfolioItem.shareCount)
        }
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
        if (model.actionType.actionTypeId == 'b') {
            portfolioItem.avgBuyCost = (((portfolioItem.avgBuyCost ?: 0) * (portfolioItem.shareCount ?: 0)) + (signedCost)) / (signedShareCount + portfolioItem.shareCount)
        }
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
        if (model.actionType.actionTypeId == 'b') {
            portfolioItem.avgBuyCost = (((portfolioItem.avgBuyCost ?: 0) * (portfolioItem.shareCount ?: 0)) + (signedCost)) / (signedShareCount + portfolioItem.shareCount)
        }
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
        if (model.actionType.actionTypeId == 'b') {
            portfolioItem.avgBuyCost = (((portfolioItem.avgBuyCost ?: 0) * (portfolioItem.shareCount ?: 0)) + (signedCost)) / (signedShareCount + portfolioItem.shareCount)
        }
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
        if (model.actionType.actionTypeId == 'b') {
            portfolioItem.avgBuyCost = (((portfolioItem.avgBuyCost ?: 0) * (portfolioItem.shareCount ?: 0)) + (signedCost)) / (signedShareCount + portfolioItem.shareCount)
        }
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
        if (model.actionType.actionTypeId == 'b') {
            portfolioItem.avgBuyCost = (((portfolioItem.avgBuyCost ?: 0) * (portfolioItem.shareCount ?: 0)) + (signedCost)) / (signedShareCount + portfolioItem.shareCount)
        }
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
        if (model.actionType.actionTypeId == 'b') {
            portfolioItem.avgBuyCost = (((portfolioItem.avgBuyCost ?: 0) * (portfolioItem.shareCount ?: 0)) + (signedCost)) / (signedShareCount + portfolioItem.shareCount)
        }
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
        if (model.actionType.actionTypeId == 'b') {
            portfolioItem.avgBuyCost = (((portfolioItem.avgBuyCost ?: 0) * (portfolioItem.shareCount ?: 0)) + (signedCost)) / (signedShareCount + portfolioItem.shareCount)
        }
        portfolioItem.shareCount += signedShareCount
        portfolioItem.cost += signedCost

        portfolioItem
    }

    private static Date parseDate(String date) {
        if (!date || date.trim() == '' || date.trim() == 'null')
            return null
        def dateParts = date.split("/").collect { it as Integer }
        new JalaliCalendar(dateParts[0], dateParts[1], dateParts[2]).toJavaUtilGregorianCalendar().time
    }
}
