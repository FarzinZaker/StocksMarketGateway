package stocks

import grails.converters.JSON
import org.apache.lucene.search.BooleanQuery
import stocks.codal.Announcement
import stocks.tse.AdjustmentHelper
import stocks.tse.Symbol
import stocks.tse.SymbolDailyTrade
import stocks.tse.SymbolPriceAdjustment

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
//            maxResults(5)
        }.collect{
            [
                    title: it.title,
                    date: it.publishDate,
                    link: it.detailsUrl,
                    type: message(code:'codal.announcement'),
                    color: 'coral'
            ]
        }

        def adjustments = SymbolPriceAdjustment.createCriteria().list {
            symbol{
                eq('id', params.id as Long)
            }
            order('date', ORDER_DESCENDING)
//            maxResults(5)
        } .collect{
            [
                    title: message(code:'tse.symbolPriceAdjustment.title', args: [it.oldPrice, it.adjustedPrice]),
                    date: it.date,
                    link: null,
                    type: message(code:'tse.symbolPriceAdjustment'),
                    color: 'royalblue'
            ]
        }

        def list = (announcements + adjustments).sort{-it.date.time}
//        if(list.size() > 10)
//            list = list[0..9]
        list.each {
            it.date = format.jalaliDate(date: it.date, hm: 'false')
        }

        render (list as JSON)
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

    def search(){
        def queryStr = params."filter[filters][0][value]"?.toString() ?: ''
        BooleanQuery.setMaxClauseCount(1000000)

        def result = Symbol.search("*${queryStr}* AND (marketCode:MCNO AND (type:300 OR type:303) AND -boardCode:4)").results.unique { a, b -> a?.id <=> b?.id }.collect {
            [
                    name : "${it.persianCode} - ${it.persianName}",
                    value: it.id
            ]
        }
        render([data: result] as JSON)
    }
}
