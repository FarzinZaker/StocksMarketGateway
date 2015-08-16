package stocks

import stocks.tse.SymbolDailyTrade

class TimeSeriesController {

    def timeSeriesDBService

    def createDB() {
        try {
            timeSeriesDBService.createDB()
            render 'created'
        }
        catch (ex) {
            render ex.message
        }
    }

    def dropDB() {
        try {
            timeSeriesDBService.dropDB()
            render 'dropped'
        }
        catch (ex) {
            render ex.message
        }
    }

    def console() {
        [
                result: params.query ? timeSeriesDBService.query(params.query as String) : []
        ]
    }
}
