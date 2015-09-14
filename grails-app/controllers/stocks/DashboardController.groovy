package stocks

import com.sun.script.javascript.RhinoScriptEngineFactory
import grails.converters.JSON
import groovy.time.TimeCategory
import org.codehaus.groovy.grails.io.support.GrailsResourceUtils
import org.ocpsoft.prettytime.PrettyTime
import org.watij.webspec.dsl.WebSpec
import stocks.codal.Announcement
import stocks.commodity.CommodityMarketActivity
import stocks.commodity.CommodityMarketHelper
import stocks.rate.CoinFuture
import stocks.rate.Currency
import stocks.rate.Coin
import stocks.rate.Metal
import stocks.tse.Index
import stocks.tse.IndexHistory
import stocks.tse.MarketActivity
import stocks.tse.MarketValue
import stocks.tse.SymbolClientType
import sun.misc.GC
import stocks.tse.EnergyMarketValue
import stocks.tse.SupervisorMessage
import stocks.rate.Oil

class DashboardController {

    def feedService
    def dashboardService

    def index() {
    }

    def marketView() {
        render(dashboardService.marketView() as JSON)
    }

    def news() {
        render(feedService.news() as JSON)
    }

    def announcements() {
        render(dashboardService.announcements() as JSON)
    }

    def rates() {
        render(dashboardService.rates() as JSON)
    }
}
