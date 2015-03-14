package stocks.analysis

class PortfolioLog {

    BackTest backTest
    Date date
    Integer remainingOutlay

    Integer stockCount = 0
    Double price

    static mapping = {
        table 'analysis_back_test_portfolio_log'
    }

    static constraints = {
    }
}
