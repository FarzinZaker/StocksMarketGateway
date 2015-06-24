package stocks

import grails.converters.JSON
import stocks.codal.Announcement
import stocks.tse.AdjustmentHelper
import stocks.tse.Symbol
import stocks.tse.SymbolDailyTrade

class SymbolController {

    def priceService
    def adjustedPriceSeriesService
    def priceSeriesAdjustmentService

    def info() {
        def symbol = Symbol.get(params.id as Long)

        [
                symbol: symbol,
                lastDailyTrade : priceService.lastDailyTrade(symbol)
        ]

    }

    def news(){
        def announcements = Announcement.createCriteria().list {
            symbol{
                eq('id', params.id as Long)
            }
            order('publishDate', ORDER_DESCENDING)
            maxResults(10)
        }.collect{
            [
                    title: it.title,
                    date: format.jalaliDate(date: it.publishDate, hm: true.toString()),
                    type: message(code:'codal.announcement')
            ]
        }
        render template: 'news', model: [news:announcements]
    }

    def clearTimeSeries() {
        adjustedPriceSeriesService.clear(params.id as Long, AdjustmentHelper.TYPES)
        render 'done'
    }

    def loadTimeSeries(){
        adjustedPriceSeriesService.write(SymbolDailyTrade.createCriteria().list {
            symbol{
                eq('id', params.id as Long)
            }
            order('date', ORDER_ASCENDING)
        }, AdjustmentHelper.TYPES)
        render 'done'
    }

    def adjust(){
        priceSeriesAdjustmentService.apply(params.type?.toString(), [params.id])
        render 'done'
    }

    def undoAdjustment(){
        priceSeriesAdjustmentService.undo(params.type?.toString(), [params.id])
        render 'done'
    }
}
