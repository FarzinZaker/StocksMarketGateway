package stocks

import stocks.tse.IndexHistory


class IndexesToTimeSeriesDailyJob {

    def cronExpression = "0 0 20 * * ?"

    def indexSeriesService

    def execute() {
//        return

        def list = IndexHistory.createCriteria().list {
            gte('date', new Date().clearTime())
        }
        if (list.size()) {
            indexSeriesService.write(list)
        }
    }
}
