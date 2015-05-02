package stocks.portfolio

import fi.joensuu.joyds1.calendar.JalaliCalendar
import stocks.Broker
import stocks.FarsiNormalizationFilter
import stocks.RateHelper
import stocks.User
import stocks.portfolio.basic.BankAccount
import stocks.portfolio.basic.BusinessPartner
import stocks.portfolio.basic.CustomBonds
import stocks.portfolio.basic.CustomSymbol
import stocks.portfolio.basic.CustomSymbolPriority
import stocks.portfolio.basic.ImmovableProperty
import stocks.portfolio.basic.MovableProperty
import stocks.portfolio.portfolioItems.PortfolioBullionItem
import stocks.rate.Coin
import stocks.rate.Currency
import stocks.tse.Symbol
import stocks.util.ClassResolver

class PortfolioPropertyManagementService {

    def springSecurityService
    def priceService

    def findProperty(String clazz, Long id, String query) {
        switch (clazz) {
            case 'portfolioBankItem':
                return findBank(id, query)
            case 'portfolioBondsItem':
                return findBonds(id, query)
            case 'portfolioBrokerItem':
                return findBroker(id, query)
            case 'portfolioBullionItem':
                return findBullion(id, query)
            case 'portfolioBusinessPartnerItem':
                return findBusinessPartner(id, query)
            case 'portfolioCoinItem':
                return findCoin(id, query)
            case 'portfolioCurrencyItem':
                return findCurrency(id, query)
            case 'portfolioCustomBondsItem':
                return findCustomBonds(id, query)
            case 'portfolioCustomSymbolItem':
                return findCustomSymbol(id, query)
            case 'portfolioCustomSymbolPriorityItem':
                return findCustomSymbolPriority(id, query)
            case 'portfolioImmovableItem':
                return findImmovableProperty(id, query)
            case 'portfolioMovableItem':
                return findMovableProperty(id, query)
            case 'portfolioSymbolItem':
                return findSymbol(id, query)
            case 'portfolioSymbolPriorityItem':
                return findSymbolPriority(id, query)
        }
        []
    }

    def findBank(Long id, String query) {
        def banks = BankAccount.findAllByOwnerAndDeleted(springSecurityService.currentUser as User, false)
        banks.sort { -it.lastUpdated.time }.collect {
            [propertyId: it.id, propertyTitle: it.bank]
        }
    }

    def findBonds(Long id, String query) {
        def symbols

        if (id) {
            def currentSymbol = Symbol.get(id)
            if (currentSymbol)
                if (query) {
                    if (query.contains(currentSymbol.persianCode) || query.contains(currentSymbol.persianName) ||
                            query.contains(currentSymbol.code) || query.contains(currentSymbol.name))
                        query = currentSymbol.persianCode
                } else {
                    query = currentSymbol.companySmallCode
                }
        }
        if (query)
            symbols = Symbol.search {
                queryString(FarsiNormalizationFilter.apply('مشارکت') + " *${query}*")
            }.results
        else
            symbols = Symbol.findAllByPersianNameLike("%${FarsiNormalizationFilter.apply('مشارکت')}%")
        symbols.sort { it.persianCode }.collect {
            [propertyId: it.id, propertyTitle: "${it.persianCode} - ${it.persianName}"]
        }
    }

    def findBroker(Long id, String query) {
        def brokers = Broker.findAllByDeleted(false)
        brokers.sort { it.name }.collect {
            [propertyId: it.id, propertyTitle: it.name]
        }
    }

    def findBullion(Long id, String query) {
        PortfolioBullionItem.constraints.carat.inList.collect {
            [propertyId: it, propertyTitle: "شمش طلای ${it} عیار"]
        }
    }

    def findBusinessPartner(Long id, String query) {
        def partners = BusinessPartner.findAllByOwnerAndDeleted(springSecurityService.currentUser as User, false)
        partners.sort { -it.lastUpdated.time }.collect {
            [propertyId: it.id, propertyTitle: it.name]
        }
    }

    def findCoin(Long id, String query) {
        def coins

        if (query)
            coins = Coin.search("*${query}*").results
        else
            coins = Coin.findAll {}
        coins.collect {
            [propertyId: it.id, propertyTitle: it.name]
        }
    }

    def findCurrency(Long id, String query) {
        def currencies

        if (query)
            currencies = Currency.search("*${query}*").results
        else
            currencies = Currency.findAll {}
        currencies.collect {
            [propertyId: it.id, propertyTitle: it.name]
        }.sort { it.propertyTitle }
    }

    def findCustomBonds(Long id, String query) {
        def items = CustomBonds.findAllByOwnerAndDeleted(springSecurityService.currentUser as User, false)
        items.sort { -it.lastUpdated.time }.collect {
            [propertyId: it.id, propertyTitle: it.name]
        }
    }

    def findCustomSymbol(Long id, String query) {
        def items = CustomSymbol.findAllByOwnerAndDeleted(springSecurityService.currentUser as User, false)
        items.sort { -it.lastUpdated.time }.collect {
            [propertyId: it.id, propertyTitle: it.name]
        }
    }

    def findCustomSymbolPriority(Long id, String query) {
        def items = CustomSymbolPriority.findAllByOwnerAndDeleted(springSecurityService.currentUser as User, false)
        items.sort { -it.lastUpdated.time }.collect {
            [propertyId: it.id, propertyTitle: it.name]
        }
    }

    def findImmovableProperty(Long id, String query) {
        def items = ImmovableProperty.findAllByOwnerAndDeleted(springSecurityService.currentUser as User, false)
        items.sort { -it.lastUpdated.time }.collect {
            [propertyId: it.id, propertyTitle: it.name]
        }
    }

    def findMovableProperty(Long id, String query) {
        def items = MovableProperty.findAllByOwnerAndDeleted(springSecurityService.currentUser as User, false)
        items.sort { -it.lastUpdated.time }.collect {
            [propertyId: it.id, propertyTitle: it.name]
        }
    }

    def findSymbol(Long id, String query) {
        def symbols

        if (id) {
            def currentSymbol = Symbol.get(id)
            if (currentSymbol)
                if (query) {
                    if (query.contains(currentSymbol.persianCode) || query.contains(currentSymbol.persianName) ||
                            query.contains(currentSymbol.code) || query.contains(currentSymbol.name))
                        query = currentSymbol.persianCode
                } else {
                    query = currentSymbol.companySmallCode
                }
        }
        if (query)
            symbols = Symbol.search("*${query}*", max: 100).results
        else
            symbols = Symbol.list([max: 20])
        symbols
                .findAll
        { Symbol item ->
            !(0..9).contains(item.persianCode.charAt(item.persianCode.size() - 1)) &&
                    (item.persianCode.charAt(0) != 'ج' || item.persianCode.charAt(1) != ' ') &&
                    (item.persianName.charAt(0) != 'ح' || item.persianName.charAt(1) != ' ') &&
                    ['300', '400', '309', '404'].contains(item.type) &&
                    item.marketCode == 'MCNO'
        }
        .collect {
            [propertyId: it.id, propertyTitle: "${it.persianCode} - ${it.persianName}"]
        }
    }

    def findSymbolPriority(Long id, String query) {
        def symbols

        if (id) {
            def currentSymbol = Symbol.get(id)
            if (currentSymbol)
                if (query) {
                    if (query.contains(currentSymbol.persianCode) || query.contains(currentSymbol.persianName) ||
                            query.contains(currentSymbol.code) || query.contains(currentSymbol.name))
                        query = currentSymbol.persianCode
                } else {
                    query = currentSymbol.companySmallCode
                }
        }
        if (query)
            symbols = Symbol.search([
                    "*${query}*"
            ].join(' '), max: 100).results
        else
            symbols = Symbol.list([max: 20])
        symbols
                .findAll
        { Symbol item ->
            !(0..9).contains(item.persianCode.charAt(item.persianCode.size() - 1)) &&
                    (item.persianCode.charAt(0) != 'ج' || item.persianCode.charAt(1) != ' ') &&
                    (item.persianName.charAt(0) != 'ح' || item.persianName.charAt(1) != ' ') &&
                    ['300', '400', '309', '404'].contains(item.type) &&
                    item.marketCode == 'MCNO'
        }
        .collect {
            [propertyId: it.id, propertyTitle: "${it.persianCode} - ${it.persianName}"]
        }
    }

    //save property
    def saveProperty(String clazz, Long id, Map params) {

        switch (clazz) {
            case 'portfolioBankItem':
                return saveBank(id, params)
            case 'portfolioBusinessPartnerItem':
                return saveBusinessPartner(id, params)
            case 'portfolioCustomBondsItem':
                return saveCustomBonds(id, params)
            case 'portfolioCustomSymbolItem':
                return saveCustomSymbol(id, params)
            case 'portfolioCustomSymbolPriorityItem':
                return saveCustomSymbolPriority(id, params)
            case 'portfolioImmovableItem':
                return saveImmovableProperty(id, params)
            case 'portfolioMovableItem':
                return saveMovableProperty(id, params)
        }
        null
    }

    def saveBank(Long id, Map params) {
        def item
        if (id) {
            item = BankAccount.get(id)
            item.properties = params
        } else
            item = new BankAccount(params)
        item.owner = springSecurityService.currentUser as User
        item.save(flush: true)
    }

    def saveBusinessPartner(Long id, Map params) {
        def item
        if (id) {
            item = BusinessPartner.get(id)
            item.properties = params
        } else
            item = new BusinessPartner(params)
        item.owner = springSecurityService.currentUser as User
        item.save(flush: true)
    }

    def saveCustomBonds(Long id, Map params) {
        def item
        if (id) {
            item = CustomBonds.get(id)
            item.properties = params
        } else
            item = new CustomBonds(params)
        item.owner = springSecurityService.currentUser as User
        item.firstPayDate = parseDate(params.firstPayDateFa as String)
        item.dueDate = parseDate(params.dueDateFa as String)
        item.save(flush: true)
    }

    def saveCustomSymbol(Long id, Map params) {
        def item
        if (id) {
            item = CustomSymbol.get(id)
            item.properties = params
        } else
            item = new CustomSymbol(params)
        item.owner = springSecurityService.currentUser as User
        item.establishDate = parseDate(params.establishDateFa as String)
        item.save(flush: true)
    }

    def saveCustomSymbolPriority(Long id, Map params) {
        def item
        if (id) {
            item = CustomSymbolPriority.get(id)
            item.properties = params
        } else
            item = new CustomSymbolPriority(params)
        item.owner = springSecurityService.currentUser as User
        item.save(flush: true)
    }

    def saveImmovableProperty(Long id, Map params) {
        def item
        if (id) {
            item = ImmovableProperty.get(id)
            item.properties = params
        } else
            item = new ImmovableProperty(params)
        item.owner = springSecurityService.currentUser as User
        item.save(flush: true)
    }

    def saveMovableProperty(Long id, Map params) {
        def item
        if (id) {
            item = MovableProperty.get(id)
            item.properties = params
        } else
            item = new MovableProperty(params)
        item.owner = springSecurityService.currentUser as User
        item.save(flush: true)
    }

    //get property
    def getProperty(String clazz, Long id) {

        switch (clazz) {
            case 'portfolioBankItem':
                return BankAccount.get(id)
            case 'portfolioBusinessPartnerItem':
                return BusinessPartner.get(id)
            case 'portfolioCustomBondsItem':
                return CustomBonds.get(id)
            case 'portfolioCustomSymbolItem':
                return CustomSymbol.get(id)
            case 'portfolioCustomSymbolPriorityItem':
                return CustomSymbolPriority.get(id)
            case 'portfolioImmovableItem':
                return ImmovableProperty.get(id)
            case 'portfolioMovableItem':
                return MovableProperty.get(id)
        }
        null
    }

    //delete property
    def deleteProperty(String clazz, Long id) {

        def item
        switch (clazz) {
            case 'portfolioBankItem':
                item = BankAccount.get(id)
                break
            case 'portfolioBusinessPartnerItem':
                item = BusinessPartner.get(id)
                break
            case 'portfolioCustomBondsItem':
                item = CustomBonds.get(id)
                break
            case 'portfolioCustomSymbolItem':
                item = CustomSymbol.get(id)
                break
            case 'portfolioCustomSymbolPriorityItem':
                item = CustomSymbolPriority.get(id)
                break
            case 'portfolioImmovableItem':
                item = ImmovableProperty.get(id)
                break
            case 'portfolioMovableItem':
                item = MovableProperty.get(id)
                break
        }
        item.deleted = true
        item.save(flush: true)
    }

    //get currentValue

    def getCurrentValueOfProperty(String clazz, Long id) {
        switch (clazz) {
            case 'portfolioBankItem':
                return getCurrentValueOfBank(id)
            case 'portfolioBondsItem':
                return getCurrentValueOfBonds(id)
            case 'portfolioBrokerItem':
                return getCurrentValueOfBroker(id)
            case 'portfolioBullionItem':
                return getCurrentValueOfBullion(id)
            case 'portfolioBusinessPartnerItem':
                return getCurrentValueOfBusinessPartner(id)
            case 'portfolioCoinItem':
                return getCurrentValueOfCoin(id)
            case 'portfolioCurrencyItem':
                return getCurrentValueOfCurrency(id)
            case 'portfolioCustomBondsItem':
                return getCurrentValueOfCustomBonds(id)
            case 'portfolioCustomSymbolItem':
                return getCurrentValueOfCustomSymbol(id)
            case 'portfolioCustomSymbolPriorityItem':
                return getCurrentValueOfCustomSymbolPriority(id)
            case 'portfolioImmovableItem':
                return getCurrentValueOfImmovableProperty(id)
            case 'portfolioMovableItem':
                return getCurrentValueOfMovableProperty(id)
            case 'portfolioSymbolItem':
                return getCurrentValueOfSymbol(id)
            case 'portfolioSymbolPriorityItem':
                return getCurrentValueOfSymbolPriority(id)
        }
        []
    }

    def getCurrentValueOfBank(Long id) {
        null
    }

    def getCurrentValueOfBonds(Long id) {
        priceService.lastPrice(Symbol.get(id))
    }

    def getCurrentValueOfBroker(Long id) {
        null
    }

    def getCurrentValueOfBullion(Long id) {
        null
    }

    def getCurrentValueOfBusinessPartner(Long id) {
        null
    }

    def getCurrentValueOfCoin(Long id) {
        Coin.get(id).price
    }

    def getCurrentValueOfCurrency(Long id) {
        Currency.get(id).price
    }

    def getCurrentValueOfCustomBonds(Long id) {
        CustomBonds.get(id).value
    }

    def getCurrentValueOfCustomSymbol(Long id) {
        null
    }

    def getCurrentValueOfCustomSymbolPriority(Long id) {
        null
    }

    def getCurrentValueOfImmovableProperty(Long id) {
        ImmovableProperty.get(id).price
    }

    def getCurrentValueOfMovableProperty(Long id) {
        MovableProperty.get(id).price
    }

    def getCurrentValueOfSymbol(Long id) {
        priceService.lastPrice(Symbol.get(id))
    }

    def getCurrentValueOfSymbolPriority(Long id) {
        priceService.lastPrice(Symbol.get(id))
    }

    //utils
    private static Date parseDate(String date) {
        if (!date || date.trim() == '' || date.trim() == 'null')
            return null
        def dateParts = date.split("/").collect { it as Integer }
        new JalaliCalendar(dateParts[0], dateParts[1], dateParts[2]).toJavaUtilGregorianCalendar().time
    }
}
