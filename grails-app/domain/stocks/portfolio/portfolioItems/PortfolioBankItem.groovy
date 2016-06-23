package stocks.portfolio.portfolioItems

import stocks.portfolio.PortfolioCashItem
import stocks.portfolio.PortfolioItem
import stocks.portfolio.basic.BankAccount

class PortfolioBankItem extends PortfolioCashItem {

    BankAccount bankAccount

    transient Long getPropertyId() {
        bankAccount.id
    }

    transient String getPropertyTitle() {
        bankAccount.bank
    }

    static constraints = {
    }

    def beforeInsert() {
        avgBuyCost = cost
        shareCount = shareCount != 0 ? 1 : 0
    }

    def beforeUpdate() {
        avgBuyCost = cost
        shareCount = shareCount != 0 ? 1 : 0
    }
}
