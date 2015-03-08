package stocks.analysis

import groovy.time.TimeCategory
import stocks.User
import stocks.alerting.Rule
import stocks.tse.Symbol

import javax.persistence.OrderBy
import javax.persistence.criteria.Order

class BackTestService {

    def springSecurityService
    def priceService

    def startBackTest(BackTest backTest){
        backTest.status = BackTestHelper.STATUS_IN_PROGRESS
        backTest.save(flush: true)
        while(backTest.status != BackTestHelper.STATUS_FINISHED){
            stepForwardBackTest(backTest)
        }
    }

    void stepForwardBackTest(BackTest backTest){
        use(TimeCategory) {
            backTest.currentDate + 1.day
        }

        if(backTest.currentDate >= backTest.endDate) {
            backTest.status = BackTestHelper.STATUS_FINISHED
        }
        else {
            def price = priceService.lastPrice(backTest?.symbol, backTest?.startDate)
            def portfolioLog = getLastPortfolioLog(backTest, price)
            if (canBuy(backTest, portfolioLog, price) && shouldBuy(backTest, portfolioLog))
                buy(backTest, portfolioLog)
            else if (canSell(backTest, portfolioLog, price) && shouldSell(backTest, portfolioLog))
                sell(backTest, portfolioLog)
        }

        backTest.save()
        backTest
    }

    Boolean canBuy(BackTest backTest, PortfolioLog portfolioLog, Double price){
        portfolioLog.remainingOutlay >= price
    }

    Boolean shouldBuy(BackTest backTest, PortfolioLog portfolioLog){
        true
    }

    void buy(BackTest backTest, PortfolioLog portfolioLog){

    }

    Boolean canSell(BackTest backTest, PortfolioLog portfolioLog, Double price){
        portfolioLog.stockCount > 0
    }

    Boolean shouldSell(BackTest backTest, PortfolioLog portfolioLog){
        true
    }

    void sell(BackTest backTest, PortfolioLog portfolioLog){

    }

    PortfolioLog getLastPortfolioLog(BackTest backTest, Double price){
        def portfolioLog = PortfolioLog.createCriteria().list {
            eq('backTest', backTest)
            order('id', ORDER_DESCENDING)
            maxResults(1)
        }?.find()
        if(!portfolioLog){
            portfolioLog = new PortfolioLog()
            portfolioLog.backTest = backTest
            use(TimeCategory) {
                portfolioLog.date = backTest.startDate - 1.day
            }
            portfolioLog.price = price
            portfolioLog.remainingOutlay = backTest.outlay
            portfolioLog.stockCount = 0
            portfolioLog.save(flush: true)
        }
        portfolioLog
    }
}
