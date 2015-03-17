package stocks.analysis

class PortfolioLog {

    BackTest backTest
    Date date
    Integer remainingOutlay

    Integer stockCount = 0
    Double price

    static mapping = {
        table 'ana_back_test_portfolio_log'
        date column: 'dat'
    }

    static constraints = {
    }
}
