package stocks.portfolio.portfolioItems

import stocks.portfolio.PortfolioItem
import stocks.portfolio.basic.BankAccount

class PortfolioBankItem extends PortfolioItem {

    BankAccount bankAccount

    transient Long getPropertyId() {
        bankAccount.id
    }

    transient String getPropertyTitle() {
        bankAccount.bank
    }

    static constraints = {
    }
}
