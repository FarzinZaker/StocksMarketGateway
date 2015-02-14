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
    List getClosingPriceSeries(Symbol symbol, Integer daysCount = 100, Date maxDate) {
        Date minDate = maxDate
        use(TimeCategory) {
            minDate = minDate - (daysCount - 1).days
        }
        getClosingPriceSeries(symbol, minDate, maxDate)
    }

    List getClosingPriceSeries(Symbol symbol, Date minDate, Date maxDate) {

        minDate = minDate.clearTime()
        maxDate = maxDate.clearTime()
        def itemValues = SymbolDailyTrade.createCriteria().list {
            eq('symbol', symbol)
            isNotNull('dailySnapshot')
            lte('dailySnapshot', maxDate)
            gte('dailySnapshot', minDate)
            projections {
                property('dailySnapshot')
                property('closingPrice')
            }
        }.collect {
            [date: new Date(it[0].getTime()) as Date, value: it[1]]
        }

        def baseValue = SymbolDailyTrade.createCriteria().list {
            eq('symbol', symbol)
            lt('date', minDate)
            order('date', ORDER_DESCENDING)
            maxResults(1)
            projections {
                property('closingPrice')
            }
        }.find()

//        if (!itemValues || itemValues.size() == 0)
//            return []

        def currentDate = minDate
        def lastValue = baseValue ?: 0
        while (currentDate <= maxDate) {
            def item = itemValues.find { it.date == currentDate }
            if (!item)
                itemValues.add([date: currentDate, value: lastValue])
            else
                lastValue = item.value

            use(TimeCategory) {
                currentDate = currentDate + 1.day
            }
        }
        itemValues.sort { it.date }.collect { it.value }
    }

    List getMinPriceSeries(Symbol symbol, Integer daysCount = 100, Date maxDate) {
        Date minDate = maxDate
        use(TimeCategory) {
            minDate = minDate - (daysCount - 1).days
        }
        getMinPriceSeries(symbol, minDate, maxDate)
    }

    List getMinPriceSeries(Symbol symbol, Date minDate, Date maxDate) {

        minDate = minDate.clearTime()
        maxDate = maxDate.clearTime()
        def itemValues = SymbolDailyTrade.createCriteria().list {
            eq('symbol', symbol)
            isNotNull('dailySnapshot')
            lte('dailySnapshot', maxDate)
            gte('dailySnapshot', minDate)
            projections {
                property('dailySnapshot')
                property('minPrice')
            }
        }.collect {
            [date: new Date(it[0].getTime()) as Date, value: it[1]]
        }

        def baseValue = SymbolDailyTrade.createCriteria().list {
            eq('symbol', symbol)
            lt('date', minDate)
            order('date', ORDER_DESCENDING)
            maxResults(1)
            projections {
                property('closingPrice')
            }
        }.find()

//        if (!itemValues || itemValues.size() == 0)
//            return []

        def currentDate = minDate
        def lastValue = baseValue ?: 0
        while (currentDate <= maxDate) {
            def item = itemValues.find { it.date == currentDate }
            if (!item)
                itemValues.add([date: currentDate, value: lastValue])
            else
                lastValue = item.value

            use(TimeCategory) {
                currentDate = currentDate + 1.day
            }
        }
        itemValues.sort { it.date }.collect { it.value }
    }

    List getMaxPriceSeries(Symbol symbol, Integer daysCount = 100, Date maxDate) {
        Date minDate = maxDate
        use(TimeCategory) {
            minDate = minDate - (daysCount - 1).days
        }
        getMinPriceSeries(symbol, minDate, maxDate)
    }

    List getMaxPriceSeries(Symbol symbol, Date minDate, Date maxDate) {

        minDate = minDate.clearTime()
        maxDate = maxDate.clearTime()
        def itemValues = SymbolDailyTrade.createCriteria().list {
            eq('symbol', symbol)
            isNotNull('dailySnapshot')
            lte('dailySnapshot', maxDate)
            gte('dailySnapshot', minDate)
            projections {
                property('dailySnapshot')
                property('maxPrice')
            }
        }.collect {
            [date: new Date(it[0].getTime()) as Date, value: it[1]]
        }

        def baseValue = SymbolDailyTrade.createCriteria().list {
            eq('symbol', symbol)
            lt('date', minDate)
            order('date', ORDER_DESCENDING)
            maxResults(1)
            projections {
                property('closingPrice')
            }
        }.find()

//        if (!itemValues || itemValues.size() == 0)
//            return []

        def currentDate = minDate
        def lastValue = baseValue ?: 0
        while (currentDate <= maxDate) {
            def item = itemValues.find { it.date == currentDate }
            if (!item)
                itemValues.add([date: currentDate, value: lastValue])
            else
                lastValue = item.value

            use(TimeCategory) {
                currentDate = currentDate + 1.day
            }
        }
        itemValues.sort { it.date }.collect { it.value }
    }
}
