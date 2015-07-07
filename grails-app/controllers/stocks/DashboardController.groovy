package stocks

import com.sun.script.javascript.RhinoScriptEngineFactory
import grails.converters.JSON
import org.codehaus.groovy.grails.io.support.GrailsResourceUtils
import org.ocpsoft.prettytime.PrettyTime
import org.watij.webspec.dsl.WebSpec
import stocks.tse.Index
import stocks.tse.IndexHistory
import stocks.tse.SymbolClientType
import sun.misc.GC

class DashboardController {

    def feedService
    def offlineChartService

    def index() {
    }

    def marketView() {
        def totalIndex = Index.findByInternalCode(32097828799138957)
        def priceIndex = Index.findByInternalCode(8384385859414435)
        def otcTotalIndex = Index.findByInternalCode(43685683301327984)
        def clientTypes = SymbolClientType.createCriteria().list {
            gte('date', new Date().clearTime())
            projections {
                sum('individualBuyVolume')
                sum('individualSellVolume')
                sum('legalBuyVolume')
                sum('legalSellVolume')
            }
        }?.first()
        render([
                totalIndex   : [
                        value        : totalIndex.finalIndexValue,
                        change       : totalIndex.finalIndexValue - (100 - totalIndex.todayIndexChangePercentTowardYesterday) * totalIndex.finalIndexValue / 100,
                        changePercent: totalIndex.todayIndexChangePercentTowardYesterday
                ],
                priceIndex   : [
                        value        : priceIndex.finalIndexValue,
                        change       : priceIndex.finalIndexValue - (100 - priceIndex.todayIndexChangePercentTowardYesterday) * priceIndex.finalIndexValue / 100,
                        changePercent: priceIndex.todayIndexChangePercentTowardYesterday
                ],
                otcTotalIndex: [
                        value: otcTotalIndex.finalIndexValue
                ],
                clientTypes  : [
                        totalIndividualBuyVolume: clientTypes[0],
                        totalIndividualSellVolume: clientTypes[1],
                        totalLegalBuyVolume: clientTypes[2],
                        totalLegalSellVolume: clientTypes[3],
                ]
        ] as JSON)
    }

    def news() {
        def result = feedService.news()
        render([
                data      : result.data.collect {
                    [
                            id        : it.id,
                            title     : it.title,
                            time      : it.date.time,
                            dateString: new PrettyTime(new Locale('fa')).format(it.date),
                            link      : it.link,
                            category  : it.category,
                            source    : message(code: "newsSource.${it.source}")
                    ]
                },
                categories: result.categoryList.collect {
                    [
                            value: it,
                            text : message(code: "newsCategory.${it}")
                    ]
                }
        ] as JSON)
    }
}
