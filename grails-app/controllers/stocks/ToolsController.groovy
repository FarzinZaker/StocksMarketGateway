package stocks

import groovy.time.TimeCategory
import stocks.rate.CoinFuture
import stocks.rate.Currency
import stocks.rate.Metal
import stocks.rate.Coin

class ToolsController {

    def calculator() {
        [
                dollarPrice: Currency.findBySymbol('us-dollar')?.price,
                onsPrice   : Metal.findBySymbol('ons')?.price,
                coinPrice  : Coin.findBySymbol('n-coin')?.price,
                contracts  : CoinFuture.findAllByContractCodeLikeAndLastTradingDateGreaterThanEquals('GC%', new Date()).collect { future ->
                    def remainingDays
                    use(TimeCategory) {
                        def duration = future.lastTradingDate - new Date()
                        remainingDays = duration.days
                    }
                    [
                            name           : future.contractDescription,
                            lastTradingDate: format.jalaliDate(date: future.lastTradingDate),
                            remainingDays  : remainingDays,
                            lastTradedPrice: future.lastTradedPrice
                    ]
                }
        ]
    }
}
