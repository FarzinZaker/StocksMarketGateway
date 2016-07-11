package stocks.portfolio

import com.google.common.cache.CacheBuilder
import com.google.common.cache.CacheLoader
import com.jniwrapper.Bool
import fi.joensuu.joyds1.calendar.JalaliCalendar
import groovy.time.TimeCategory
import stocks.Broker
import stocks.FarsiNormalizationFilter
import stocks.RateHelper
import stocks.User
import stocks.analysis.BackTest
import stocks.portfolio.basic.BankAccount
import stocks.portfolio.basic.BusinessPartner
import stocks.portfolio.basic.CustomBonds
import stocks.portfolio.basic.CustomSymbol
import stocks.portfolio.basic.CustomSymbolPriority
import stocks.portfolio.basic.ImmovableProperty
import stocks.portfolio.basic.MovableProperty
import stocks.portfolio.meta.PortfolioAvailBroker
import stocks.portfolio.portfolioItems.PortfolioBullionItem
import stocks.rate.Coin
import stocks.rate.Currency
import stocks.tse.Symbol
import stocks.util.ClassResolver

import java.util.concurrent.TimeUnit

class PortfolioPropertyManagementService {

    def springSecurityService
    def priceService
    def messageSource
    def coinSeries9Service
    def currencySeries9Service
    def adjustedPriceSeries9Service


    def propertyValuesCache

    public PortfolioPropertyManagementService() {
        propertyValuesCache = CacheBuilder.newBuilder().expireAfterWrite(10, TimeUnit.MINUTES).maximumSize(10000).build(new CacheLoader() {
            @Override
            Object load(Object o) throws Exception {
                def key = o as Map<String, Object>
                cacheValueOfProperty(key.clazz as String, key.id as Long, key.startDate as Date, key.endDate as Date)
            }
        })
    }

    def findProperty(String clazz, Long id, String query, Portfolio portfolio = null, Boolean availOnly = false) {
        switch (clazz) {
            case 'portfolioBankItem':
                return findBank(id, query)
            case 'portfolioInvestmentFundItem':
                return findInvestmentFund(id, query)
            case 'portfolioBondsItem':
                return findBonds(id, query)
            case 'portfolioHousingFacilitiesItem':
                return findHousingFacility(id, query)
            case 'portfolioBrokerItem':
                return findBroker(id, query, portfolio, availOnly)
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
    private def modifiableList = [
            'portfolioBondsItem',
            'portfolioBullionItem',
            'portfolioCoinItem',
            'portfolioCurrencyItem',
            'portfolioSymbolItem',
            'portfolioSymbolPriorityItem',
            'portfolioInvestmentFundItem',
            'portfolioHousingFacilitiesItem'
    ]
    private def defaultItems = ['portfolioSymbolItem', 'portfolioSymbolPriorityItem', 'portfolioBondsItem']

    def portfolioItemTypes() {
        ClassResolver.loadDomainClassListByPackage('stocks.portfolio.portfolioItems').collect {
            [
                    clazz     : it.propertyName,
                    title     : messageSource.getMessage(it.fullName, null, it.fullName, null),
                    modifiable: !modifiableList.contains(it.propertyName),
                    default   : defaultItems.contains(it.propertyName)
            ]
        }.sort { it.title }
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
            symbols = Symbol.search("*${query}* AND marketCode:MCNO AND ((type:301 AND boardCode:9) OR type:306)", max: 20).results
        else
            symbols = Symbol.createCriteria().list {
                eq('marketCode', 'MCNO')
                or {
                    eq('type', '306')
                    and {
                        eq('type', '301')
                        eq('boardCode', '9')
                    }
                }
//                'in'('type', ['400', '403', '404'])
//                notEqual('boardCode', '4')
                order('persianCode')
            }
        symbols.collect {
            [propertyId: it.id, propertyTitle: "${it.persianCode} - ${it.persianName}"]
        }
    }

    def findHousingFacility(Long id, String query) {
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
            symbols = Symbol.search("*${query}* AND marketCode:MCNO AND type:303 AND boardCode:4", max: 20).results
        else
            symbols = Symbol.createCriteria().list {
                eq('marketCode', 'MCNO')
                eq('type', '303')
                eq('boardCode', '4')
                order('persianCode')
            }
        symbols.collect {
            [propertyId: it.id, propertyTitle: "${it.persianCode} - ${it.persianName}"]
        }
    }

    def findInvestmentFund(Long id, String query) {
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
            symbols = Symbol.search("*${query}* AND marketCode:MCNO AND type:305", max: 20).results
        else
            symbols = Symbol.createCriteria().list {
                eq('marketCode', 'MCNO')
                eq('type', '305')
                order('persianCode')
            }
        symbols.collect {
            [propertyId: it.id, propertyTitle: "${it.persianCode} - ${it.persianName}"]
        }
    }

    def findBroker(Long id, String query, Portfolio portfolio, Boolean availOnly) {
        availOnly = true
        def brokers = availOnly ? PortfolioAvailBroker.findAllByPortfolio(portfolio)*.broker : Broker.findAllByDeleted(false)
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
            symbols = Symbol.search("*${query}* AND marketCode:MCNO AND (type:300 OR type:303 OR type:309) AND -boardCode:4", max: 20).results
        else
            symbols = Symbol.createCriteria().list {
                eq('marketCode', 'MCNO')
                'in'('type', ['300', '303', '309'])
                notEqual('boardCode', '4')
                order('persianCode')
            }
        symbols.collect {
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
            symbols = Symbol.search("*${query}* AND marketCode:MCNO AND (type:400 OR type:403 OR type:404) AND -boardCode:4", max: 20).results
        else
            symbols = Symbol.createCriteria().list {
                eq('marketCode', 'MCNO')
                'in'('type', ['400', '403', '404'])
                notEqual('boardCode', '4')
//                projections {
//                    property('id')
//                }
            }
        symbols.collect {
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
            case 'portfolioBrokerItem':
                return Broker.get(id)
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
            case 'portfolioHousingFacilitiesItem':
                return getCurrentValueOfHousingFacilities(id)
            case 'portfolioInvestmentFundItem':
                return getCurrentValueOfInvestmentFund(id)
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

    def getCurrentValueOfHousingFacilities(Long id) {
        priceService.lastPrice(Symbol.get(id))
    }

    def getCurrentValueOfInvestmentFund(Long id) {
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

    //get currentValue

    def getValueOfProperty(String clazz, Long id, Date date, Date startDate, Date endDate) {

        def cache = propertyValuesCache.get([clazz: clazz, id: id, startDate: startDate, endDate: endDate]) as Map<Long, Double>
        cache.findAll { it.key <= date?.time }?.sort { -it.key }?.collect { it.value }?.find()
    }

    //utils
    private static Date parseDate(String date) {
        if (!date || date.trim() == '' || date.trim() == 'null')
            return null
        def dateParts = date.split("/").collect { it as Integer }
        new JalaliCalendar(dateParts[0], dateParts[1], dateParts[2]).toJavaUtilGregorianCalendar().time
    }

    //cache value of property
    Map<Long, Double>  cacheValueOfProperty(String clazz, Long id, Date startDate, Date endDate) {
        use(TimeCategory) {
            endDate = (endDate + 1.day).clearTime() - 1.second
            startDate = startDate.clearTime()
        }
        Map<Long, Double> result = [:]
        switch (clazz) {
            case 'portfolioBankItem':
                result = cacheValueOfBank(id, startDate, endDate)
                break
            case 'portfolioBondsItem':
                result = cacheValueOfBonds(id, startDate, endDate)
                break
            case 'portfolioHousingFacilitiesItem':
                result = cacheValueOfHousingFacilities(id, startDate, endDate)
                break
            case 'portfolioInvestmentFundItem':
                result = cacheValueOfInvestmentFund(id, startDate, endDate)
                break
            case 'portfolioBrokerItem':
                result = cacheValueOfBroker(id, startDate, endDate)
                break
            case 'portfolioBullionItem':
                result = cacheValueOfBullion(id, startDate, endDate)
                break
            case 'portfolioBusinessPartnerItem':
                result = cacheValueOfBusinessPartner(id, startDate, endDate)
                break
            case 'portfolioCoinItem':
                result = cacheValueOfCoin(id, startDate, endDate)
                break
            case 'portfolioCurrencyItem':
                result = cacheValueOfCurrency(id, startDate, endDate)
                break
            case 'portfolioCustomBondsItem':
                result = cacheValueOfCustomBonds(id, startDate, endDate)
                break
            case 'portfolioCustomSymbolItem':
                result = cacheValueOfCustomSymbol(id, startDate, endDate)
                break
            case 'portfolioCustomSymbolPriorityItem':
                result = cacheValueOfCustomSymbolPriority(id, startDate, endDate)
                break
            case 'portfolioImmovableItem':
                result = cacheValueOfImmovableProperty(id, startDate, endDate)
                break
            case 'portfolioMovableItem':
                result = cacheValueOfMovableProperty(id, startDate, endDate)
                break
            case 'portfolioSymbolItem':
                result = cacheValueOfSymbol(id, startDate, endDate)
                break
            case 'portfolioSymbolPriorityItem':
                result = cacheValueOfSymbolPriority(id, startDate, endDate)
                break
        }
        result
    }

    Map<Long, Double> cacheValueOfBank(Long id, Date startDate, Date endDate) {
        [:]
    }

    Map<Long, Double> cacheValueOfBonds(Long id, Date startDate, Date endDate) {
        def result = [:]
        adjustedPriceSeries9Service.closingPriceList(id, startDate, endDate).each {
            result.put(it.date?.time as long, it.value as Double)
        }
        result
    }

    Map<Long, Double> cacheValueOfHousingFacilities(Long id, Date startDate, Date endDate) {
        def result = [:]
        adjustedPriceSeries9Service.closingPriceList(id, startDate, endDate).each {
            result.put(it.date?.time as long, it.value as Double)
        }
        result
    }

    Map<Long, Double> cacheValueOfInvestmentFund(Long id, Date startDate, Date endDate) {
        def result = [:]
        adjustedPriceSeries9Service.closingPriceList(id, startDate, endDate).each {
            result.put(it.date?.time as long, it.value as Double)
        }
        result
    }

    Map<Long, Double> cacheValueOfBroker(Long id, Date startDate, Date endDate) {
        [:]
    }

    Map<Long, Double> cacheValueOfBullion(Long id, Date startDate, Date endDate) {
        [:]
    }

    Map<Long, Double> cacheValueOfBusinessPartner(Long id, Date startDate, Date endDate) {
        [:]
    }

    Map<Long, Double> cacheValueOfCoin(Long id, Date startDate, Date endDate) {
        def result = [:]
        coinSeries9Service.priceList(id, startDate, endDate).each {
            result.put(it.date?.time as long, it.value as Double)
        }
        result
    }

    Map<Long, Double> cacheValueOfCurrency(Long id, Date startDate, Date endDate) {
        def result = [:]
        currencySeries9Service.priceList(id, startDate, endDate).each {
            result.put(it.date?.time as long, it.value as Double)
        }
        result
    }

    Map<Long, Double> cacheValueOfCustomBonds(Long id, Date startDate, Date endDate) {
        def value = CustomBonds.get(id).value as Double
        def result = [:]
        def minDate = startDate
        while (minDate <= endDate) {
            minDate = minDate?.clearTime()
            result.put(minDate?.time, value)
            use(TimeCategory) {
                minDate = minDate + 1.day
            }
        }
        result
    }

    Map<Long, Double> cacheValueOfCustomSymbol(Long id, Date startDate, Date endDate) {
        [:]
    }

    Map<Long, Double> cacheValueOfCustomSymbolPriority(Long id, Date startDate, Date endDate) {
        [:]
    }

    Map<Long, Double> cacheValueOfImmovableProperty(Long id, Date startDate, Date endDate) {
        def value = ImmovableProperty.get(id).price as Double
        def result = [:]
        def minDate = startDate
        while (minDate <= endDate) {
            minDate = minDate?.clearTime()
            result.put(minDate?.time, value)
            use(TimeCategory) {
                minDate = minDate + 1.day
            }
        }
        result
    }

    Map<Long, Double> cacheValueOfMovableProperty(Long id, Date startDate, Date endDate) {
        def value = MovableProperty.get(id).price as Double
        def result = [:]
        def minDate = startDate
        while (minDate <= endDate) {
            minDate = minDate?.clearTime()
            result.put(minDate?.time, value)
            use(TimeCategory) {
                minDate = minDate + 1.day
            }
        }
        result
    }

    Map<Long, Double> cacheValueOfSymbol(Long id, Date startDate, Date endDate) {
        def result = [:]
        adjustedPriceSeries9Service.closingPriceList(id, startDate, endDate).each {
            result.put(it.date?.time as long, it.value as Double)
        }
        result
    }

    Map<Long, Double> cacheValueOfSymbolPriority(Long id, Date startDate, Date endDate) {
        def result = [:]
        adjustedPriceSeries9Service.closingPriceList(id, startDate, endDate).each {
            result.put(it.date?.time as long, it.value as Double)
        }
        result
    }
}
