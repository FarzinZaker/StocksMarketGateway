package stocks

import stocks.rate.event.CoinFutureEvent


class FutureToTimeSeriesDailyJob {

    def cronExpression = "0 0 20 * * ?"

    def futureSeriesService

    def execute() {
//        return

        def list = CoinFutureEvent.createCriteria().list {
            gte('lastTradingDate', new Date().clearTime())
        }
        if (list.size()) {
            futureSeriesService.write(list)
        }
    }
}
