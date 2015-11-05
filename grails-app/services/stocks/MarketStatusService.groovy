package stocks

import groovy.time.TimeCategory

class MarketStatusService {

    public static String MARKET_STOCK = 'stock'
    public static String MARKET_OTC = 'otc'
    public static String MARKET_ENERGY = 'energy'
    public static String MARKET_COMMODITY = 'commodity'
    public static String MARKET_FUTURE = 'future'

    public static OPEN_TIMES = [
            stock    : [
                    '7': [open: '8:30', close: '12:30'],
                    '1': [open: '8:30', close: '12:30'],
                    '2': [open: '8:30', close: '12:30'],
                    '3': [open: '8:30', close: '12:30'],
                    '4': [open: '8:30', close: '12:30']],
            otc      : [
                    '7': [open: '8:30', close: '12:30'],
                    '1': [open: '8:30', close: '12:30'],
                    '2': [open: '8:30', close: '12:30'],
                    '3': [open: '8:30', close: '12:30'],
                    '4': [open: '8:30', close: '12:30']],
            energy   : [
                    '7': [open: '10:00', close: '17:00'],
                    '1': [open: '10:00', close: '17:00'],
                    '2': [open: '10:00', close: '17:00'],
                    '3': [open: '10:00', close: '17:00'],
                    '4': [open: '10:00', close: '17:00']],
            commodity: [
                    '7': [open: '10:00', close: '17:30'],
                    '1': [open: '10:00', close: '17:30'],
                    '2': [open: '10:00', close: '17:30'],
                    '3': [open: '10:00', close: '17:30'],
                    '4': [open: '10:00', close: '17:30']],
            future   : [
                    '7': [open: '10:00', close: '18:00'],
                    '1': [open: '10:00', close: '18:00'],
                    '2': [open: '10:00', close: '18:00'],
                    '3': [open: '10:00', close: '18:00'],
                    '4': [open: '10:00', close: '18:00'],
                    '5': [open: '10:00', close: '16:00']]
    ]

    public static String STATUS_OPEN = 'open'
    public static String STATUS_CLOSE = 'close'

    public String status(String market, Date date = new Date()) {
        def marketOpenTimes = OPEN_TIMES."${market}"
        if (!marketOpenTimes)
            return STATUS_CLOSE
        def calendar = Calendar.instance
        calendar.setTime(date)
        def dayTimes = marketOpenTimes."${calendar.get(Calendar.DAY_OF_WEEK)}"
        if (!dayTimes)
            return STATUS_CLOSE

        def currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        def currentMinute = calendar.get(Calendar.MINUTE)

        def openHour = dayTimes.open.split(':').first() as Integer
        def openMinute = dayTimes.open.split(':').last() as Integer

        if (currentHour < openHour || (currentHour == openHour && currentMinute < openMinute))
            return STATUS_CLOSE

        def closeHour = dayTimes.close.split(':').first() as Integer
        def closeMinute = dayTimes.close.split(':').last() as Integer

        if (currentHour > closeHour || (currentHour == closeHour && currentMinute > closeMinute))
            return STATUS_CLOSE

        STATUS_OPEN
    }

    public Boolean isOpen(String market) {
        status(market) == STATUS_OPEN
    }

    public Boolean isClose(String market) {
        status(market) == STATUS_CLOSE
    }

    public String statusWithMargin(String market, Date date = new Date()) {

        def marketOpenTimes = OPEN_TIMES."${market}"
        if (!marketOpenTimes)
            return STATUS_CLOSE

        def openDate = date
        use(TimeCategory) {
            openDate = date.clone() + 30.minutes
        }
        def openCalendar = Calendar.instance
        openCalendar.setTime(openDate)

        def closeDate = date
        use(TimeCategory) {
            closeDate = date.clone() - 30.minutes
        }
        def closeCalendar = Calendar.instance
        closeCalendar.setTime(closeDate)

        def dayTimes = marketOpenTimes."${openCalendar.get(Calendar.DAY_OF_WEEK)}"
        if (!dayTimes)
            return STATUS_CLOSE

        def currentOpenHour = openCalendar.get(Calendar.HOUR_OF_DAY)
        def currentOpenMinute = openCalendar.get(Calendar.MINUTE)

        def currentCloseHour = closeCalendar.get(Calendar.HOUR_OF_DAY)
        def currentCloseMinute = closeCalendar.get(Calendar.MINUTE)

        def openHour = dayTimes.open.split(':').first() as Integer
        def openMinute = dayTimes.open.split(':').last() as Integer

        if (currentOpenHour < openHour || (currentOpenHour == openHour && currentOpenMinute < openMinute))
            return STATUS_CLOSE

        def closeHour = dayTimes.close.split(':').first() as Integer
        def closeMinute = dayTimes.close.split(':').last() as Integer

        if (currentCloseHour > closeHour || (currentCloseHour == closeHour && currentCloseMinute > closeMinute))
            return STATUS_CLOSE

        STATUS_OPEN
    }

    public Boolean isOpenWithMargin(String market) {
        statusWithMargin(market) == STATUS_OPEN
    }

    public Boolean isCloseWithMargin(String market) {
        statusWithMargin(market) == STATUS_CLOSE
    }

    public Date correctMarketLastDataUpdateTime(String market, Date date) {
        def calendar = Calendar.instance
        calendar.setTime(date)

        def marketOpenTimes = OPEN_TIMES."${market}"
        def dayTimes = marketOpenTimes."${calendar.get(Calendar.DAY_OF_WEEK)}"
        if (!dayTimes) {
            use(TimeCategory) {
                date = date.clone() - (calendar.get(Calendar.DAY_OF_WEEK) - marketOpenTimes.keySet().last().toInteger()).days
                calendar.setTime(date)
            }
            calendar.set(calendar.HOUR_OF_DAY, 23)
            dayTimes = marketOpenTimes."${calendar.get(Calendar.DAY_OF_WEEK)}"
        }

        def currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        def currentMinute = calendar.get(Calendar.MINUTE)


        def closeHour = dayTimes.close.split(':').first() as Integer
        def closeMinute = dayTimes.close.split(':').last() as Integer

        if (currentHour > closeHour || (currentHour == closeHour && currentMinute > closeMinute)) {
            calendar.set(Calendar.HOUR_OF_DAY, closeHour)
            calendar.set(Calendar.MINUTE, closeMinute)
        } else {
            def openHour = dayTimes.open.split(':').first() as Integer
            def openMinute = dayTimes.open.split(':').last() as Integer

            if (currentHour < openHour || (currentHour == openHour && currentMinute < openMinute)) {
                calendar.set(Calendar.HOUR_OF_DAY, openHour)
                calendar.set(Calendar.MINUTE, openMinute)
            }
        }

        calendar.time
    }
}
