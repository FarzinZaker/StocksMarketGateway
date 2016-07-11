package stocks.portfolio

import com.google.common.base.CaseFormat
import fi.joensuu.joyds1.calendar.JalaliCalendar
import jxl.Sheet
import jxl.Workbook
import org.ccil.cowan.tagsoup.Parser
import stocks.Broker
import stocks.FarsiNormalizationFilter
import stocks.GlobalSetting
import stocks.portfolio.basic.BankAccount
import stocks.portfolio.basic.BusinessPartner
import stocks.portfolio.basic.CustomBonds
import stocks.portfolio.basic.CustomSymbol
import stocks.portfolio.basic.CustomSymbolPriority
import stocks.portfolio.basic.ImmovableProperty
import stocks.portfolio.basic.MovableProperty
import stocks.portfolio.meta.PortfolioAvailItem
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
import java.util.zip.GZIPInputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipFile

class PortfolioActionManagementService {
    def messageSource
    def springSecurityService
    def portfolioReportService
    DateFormat df = new SimpleDateFormat("EEE MMM dd yyyy kk:mm:ss 'GMT'Z '('z')'", Locale.ENGLISH);

    def save(Long portfolioId, Map model, PortfolioAction parentAction = null) {

        def portfolio = Portfolio.get(portfolioId)

        def action
        if (model.id)
            action = PortfolioAction.get(model.id)
        else
            action = new PortfolioAction()


        def portfolioItem = getPortfolioItem(portfolio, model)
        def hasChild = PortfolioAvailItem.countByPortfolioAndItemInList(portfolio, ['portfolioBankItem', 'portfolioBusinessPartnerItem', 'portfolioBrokerItem'])
        def actionDate = parseDate(model.actionDate);
        if (actionDate > new Date())
            return [errors: [allErrors: [messageSource.getMessage('portfolio.actionDate.validation', [].toArray(), '', null)]]]
        if (model.actionType.actionTypeId in ['s', 'w']) {

            def prevAmount = portfolioItem?.id ? (PortfolioAction.createCriteria().list {
                if (model.id) {
                    ne('id', model.id as Long)
                }
                eq('portfolioItem', portfolioItem)
                le('actionDate', actionDate)
            }.sum {
                (it.actionType in ['s', 'w'] ? -1 : 1) * it.shareCount * (model.actionType.actionTypeId == 'w' ? it.sharePrice : 1)
            } ?: 0) : 0
            if (model.actionType.actionTypeId == 's' && prevAmount < (model.shareCount ?: 1)) {
                portfolioItem?.discard()
                return [errors: [allErrors: [messageSource.getMessage('portfolio.sharesCount.validation', [prevAmount].toArray(), '', null)]]]
            }
            //bank, broker and partners map accept negative values
//            if (hasChild && model.actionType.actionTypeId == 'w' && prevAmount < (model.sharePrice ?: 1)) {
//                portfolioItem?.discard()
//                return [errors: [allErrors: [messageSource.getMessage('portfolio.sharesAmount.validation', [prevAmount].toArray(), '', null)]]]
//            }
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
                if (action?.portfolioItem instanceof PortfolioPropertyItem) {
                    if (action.signedCost > 0) {
                        oldPortfolioItem.avgBuyCost = (oldPortfolioItem.avgBuyCost * oldPortfolioItem.shareCount - action.signedCost) / (oldPortfolioItem.shareCount - action.signedShareCount)
                    }
                    oldPortfolioItem.shareCount -= action.signedShareCount
                }
                oldPortfolioItem.cost -= action.signedCost
                oldPortfolioItem.save()
            }
        }
        portfolioItem?.save(flush: true)

        if (action.id)
            PortfolioAction.findAllByParentAction(action).each {
                delete(it?.id)
            }

        // date sample: Mon Dec 01 2014 01:00:00 GMT+0330 (IRST)
        action.actionDate = parentAction ? parentAction.actionDate : parseDate(model.actionDate);
        action.actionType = model.actionType.actionTypeId
        action.shareCount = model.shareCount ?: 1
        action.sharePrice = model.sharePrice
        action.broker = model.broker?.brokerId ? Broker.get(model.broker?.brokerId as Long) : null
        action.discount = model.discount ? model.discount as Float : null
        action.wage = (GlobalSetting.findByName("portfolio.${CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, portfolioItem.class.simpleName.replace('Portfolio', '').replace('Item', ''))}.${model.actionType.actionTypeId == 'b' ? 'buy' : 'sell'}.wage")?.value ?: 0) as Float
        action.actionDescription = "-" // todo
        action.portfolioItem = portfolioItem
        action.portfolio = portfolio
        action.parentAction = parentAction
        action.isInitialDataEntry = model.isInitialDataEntry
        action.transactionSourceType = model.isInitialDataEntry || !['b', 's'].contains(action.actionType) || !model.transactionSourceType ? null : model.transactionSourceType
        action.transactionSourceId = model.isInitialDataEntry || !['b', 's'].contains(action.actionType) || !model.transactionSource ? null : model.transactionSource?.toString()?.toLong()

        action.save(flush: true)

        if (hasChild && !action.isInitialDataEntry && ['b', 's'].contains(action.actionType)) {
            def childActionModel = [:]
            childActionModel.actionDate = model.actionDate
            childActionModel.actionType = [actionTypeId: action.actionType == 'b' ? 'w' : 'd']
            childActionModel.sharePrice = action.shareCount * action.sharePrice
            childActionModel.itemType = [clazz: model.transactionSourceType]
            childActionModel.property = [propertyId: model.transactionSource ? model.transactionSource?.toString()?.toLong() : null]
            def childAction = save(portfolioId, childActionModel, action)
            if (childAction?.errors?.allErrors) {
                if (action?.id)
                    delete(action.id)
                return childAction
            }
        }

        model.childActions?.each { childAction ->
            save(portfolioId, childAction, action)
        }

        if(!parentAction)
            portfolioReportService.invalidateReports(portfolio)
        action
    }

    def delete(Long actionId) {
        def item = PortfolioAction.get(actionId)
        def portfolio = item.portfolio
        if (item) {
            def items = PortfolioAction.findAllByIdNotEqualAndPortfolioItemAndActionDateGreaterThanEquals(item.id, item.portfolioItem, item.actionDate)
            if (item.portfolioItem instanceof PortfolioPropertyItem && (items?.sum { it.signedShareCount } ?: 0) < 0)
                return [error: 'countLessThanZero']
            def childrenResult = [error: false]
            PortfolioAction.findAllByParentAction(item).each { childAction ->
                if (!childrenResult.error)
                    childrenResult = delete(childAction.id)
            }
            if (childrenResult.error)
                return childrenResult

            def portfolioItem = item.portfolioItem
            def signedShareCount = item.signedShareCount
            def signedCost = item.signedCost
            item.delete()
            if (PortfolioAction.findAllByPortfolioItem(portfolioItem)) {
                if (portfolioItem instanceof PortfolioCashItem) {
                    portfolioItem.cost -= signedCost
                } else {
                    if (signedCost > 0) {
                        if ((portfolioItem.shareCount ?: 0) - signedShareCount != 0)
                            portfolioItem.avgBuyCost = ((portfolioItem.avgBuyCost ?: 0) * (portfolioItem.shareCount ?: 0) - signedCost) / (portfolioItem.shareCount ?: 0) - signedShareCount
                        else
                            portfolioItem.avgBuyCost = 0
                    }
                    portfolioItem.shareCount -= signedShareCount
                    portfolioItem.cost -= signedCost
                }
            } else {
                if (!PortfolioAction.findAllByPortfolioItem(portfolioItem))
                    portfolioItem.delete(flush: true)
            }
        }

        portfolioReportService.invalidateReports(portfolio)

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

        portfolioItem.cost += signedCost
        portfolioItem.avgBuyCost = portfolioItem.cost
        portfolioItem.shareCount = portfolioItem.cost != 0 ? 1 : 0

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

        portfolioItem.cost += signedCost
        portfolioItem.avgBuyCost = portfolioItem.cost
        portfolioItem.shareCount = portfolioItem.cost != 0 ? 1 : 0

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

        portfolioItem.cost += signedCost
        portfolioItem.avgBuyCost = portfolioItem.cost
        portfolioItem.shareCount = portfolioItem.cost != 0 ? 1 : 0

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

    List<Map> importPortfolioActions(Long portfolioId, String filePath, Broker broker) {
        def extension = filePath.substring(filePath.lastIndexOf('.') + 1)?.toLowerCase()
        if (extension == 'zip')
            return importPortfolioActionsFromZip(portfolioId, filePath, broker)
        else if (extension == 'xls')
            return importPortfolioActionsFromExcel(portfolioId, filePath, broker)
        return []
    }

    private List<Map> importPortfolioActionsFromZip(Long portfolioId, String filePath, Broker broker) {
        def result = []
        ByteArrayInputStream bis = new ByteArrayInputStream(new File(filePath).readBytes());
        GZIPInputStream gis = new GZIPInputStream(bis);
        BufferedReader br = new BufferedReader(new InputStreamReader(gis, "UTF-16"));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        gis.close();
        bis.close();
        def rows = new XmlSlurper(new Parser()).parseText(sb.toString()).'**'?.findAll { it?.name() == 'tr' };
        rows.remove(0)
        rows.each { row ->
            def cells = row.'**'?.findAll { it?.name() == 'td' }?.collect { it?.text()?.trim() }
            println(cells)

            try {

                def count = cells[13]?.toString()?.toInteger()
                if (count > 0) {
                    def actionType = null
                    if (FarsiNormalizationFilter.apply(cells[7]?.toString()) == FarsiNormalizationFilter.apply('خرید'))
                        actionType = 'b'
                    if (FarsiNormalizationFilter.apply(cells[7]?.toString()) == FarsiNormalizationFilter.apply('فروش'))
                        actionType = 's'

                    def price = cells[12]?.toString()?.toInteger()

                    def date = cells[6]?.toString()

                    def symbolCode = cells[8]?.toString()
                    def symbol = Symbol.findByCode(symbolCode)

                    def model = [
                            id                : '',
                            itemType          : [clazz: specifySymbolItemType(symbol)],
                            property          : [propertyId: symbol?.id, propertyName: symbol?.toString()],
                            actionType        : [actionTypeId: actionType],
                            actionDate        : date,
                            sharePrice        : price,
                            shareCount        : count,
                            isInitialDataEntry: !broker
                    ]
                    if (broker) {
                        model.transactionSourceType = 'portfolioBrokerItem'
                        model.transactionSource = broker?.id
                        model.broker = [brokerId: broker?.id, brokerName: broker?.name]
                    }
                    model.errors = save(portfolioId, model)?.errors?.allErrors
                    result << model
                }
            }
            catch (ignored) {
                throw ignored
            }
        }
        result
    }

    private List<Map> importPortfolioActionsFromExcel(Long portfolioId, String filePath, Broker broker) {
        def result = []
        def file = new File(filePath)
        Workbook book = Workbook.getWorkbook(file)
        Sheet sheet = book.getSheet(0)
        for (def i = 0; i < sheet.rows; i++) {
            try {
                def description = FarsiNormalizationFilter.apply(sheet.getCell(2, i).contents)
                def descriptionParts = description.split(' ').collect { it.trim() }.findAll { it }
                if (!descriptionParts.size())
                    continue
                def actionType = null
                if (descriptionParts.first() == FarsiNormalizationFilter.apply('خرید'))
                    actionType = 'b'
                if (descriptionParts.first() == FarsiNormalizationFilter.apply('فروش'))
                    actionType = 's'
                if (!actionType)
                    continue

                def count = descriptionParts[1]?.replace(',', '')?.toInteger()

                def price = 0
                def correctPosition = false
                for (def j = 0; j < descriptionParts.size(); j++) {
                    if (correctPosition) {
                        price = descriptionParts[j]?.replace(',', '')?.toInteger()
                        break;
                    }
                    if (descriptionParts[j] == '-')
                        correctPosition = true;
                }

                def date = sheet.getCell(1, i).contents?.trim()

                def symbolCode = descriptionParts.last()?.replace('(', '')?.replace(')', '')?.substring(0, 12)
                def symbol = Symbol.findByCode(symbolCode)

                def model = [
                        id                : '',
                        itemType          : [clazz: specifySymbolItemType(symbol)],
                        property          : [propertyId: symbol?.id, propertyName: symbol?.toString()],
                        actionType        : [actionTypeId: actionType],
                        actionDate        : date,
                        sharePrice        : price,
                        shareCount        : count,
                        isInitialDataEntry: !broker
                ]
                if (broker) {
                    model.transactionSourceType = 'portfolioBrokerItem'
                    model.transactionSource = broker?.id
                    model.broker = [brokerId: broker?.id, brokerName: broker?.name]
                }
                model.errors = save(portfolioId, model)?.errors?.allErrors
                result << model
            }
            catch (ignored) {
                throw ignored
            }
        }
        result
    }

    private String specifySymbolItemType(Symbol symbol) {

        if (symbol.marketCode == 'MCNO' && symbol.type == '305')
            return 'portfolioInvestmentFundItem'

        if (symbol.marketCode == 'MCNO' && ((symbol.type == '301' && symbol.boardCode == '9') || symbol.type == '306'))
            return 'portfolioBondsItem'

        if (symbol.marketCode == 'MCNO' && symbol.type == '303' && symbol.boardCode == '4')
            return 'portfolioHousingFacilitiesItem'

        if (symbol.marketCode == 'MCNO' && (symbol.type == '400' || symbol.type == '403' || symbol.type == '404') && symbol.boardCode != '4')
            return 'portfolioSymbolPriorityItem'

        return 'portfolioSymbolItem'
    }
}
