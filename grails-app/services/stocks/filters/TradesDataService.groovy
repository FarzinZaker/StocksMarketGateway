package stocks.filters

import eu.verdelhan.ta4j.Tick
import eu.verdelhan.ta4j.TimeSeries
import fi.joensuu.joyds1.calendar.JalaliCalendar
import groovy.time.TimeCategory
import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass
import org.joda.time.DateTime
import org.joda.time.Instant
import org.joda.time.Period
import stocks.indicators.IndicatorBase
import stocks.indicators.symbol.movingAverage.SMA
import stocks.tse.Symbol
import stocks.tse.SymbolDailyTrade

class TradesDataService {
    List<SymbolDailyTrade> getPriceSeries(Symbol symbol, Integer daysCount = null, Date maxDate = null) {

        maxDate = maxDate?.clearTime()
        SymbolDailyTrade.createCriteria().list {
            eq('symbol', symbol)
            isNotNull('dailySnapshot')
            if (maxDate) {
                lte('dailySnapshot', maxDate)
            }
            order('dailySnapshot', ORDER_DESCENDING)
            if (daysCount) {
                maxResults(daysCount)
            }
        }.sort { it.dailySnapshot }
    }
}
