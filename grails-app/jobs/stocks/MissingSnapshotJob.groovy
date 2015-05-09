package stocks

import fi.joensuu.joyds1.calendar.JalaliCalendar
import grails.converters.JSON
import groovy.time.TimeCategory
import stocks.tse.SymbolDailyTrade


class MissingSnapshotJob {

    def lowLevelDataService

    static startDelay = 60000
    static timeout = 5000l
    static concurrent = false

    def snapshotService

//    def execute() {
//
//        def currentDate = getLastState()
//        def cal = Calendar.getInstance()
//        cal.setTime(currentDate)
//        def domain = '.'
//        def jc = new JalaliCalendar(cal as GregorianCalendar)
//        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY)
//            snapshotService.applyWeeklySnapshot(domain, currentDate)
//        if (jc.getDay() == jc.getLastDayOfMonth(jc.getYear(), jc.getMonth()))
//            snapshotService.applyMonthlySnapshot(domain, currentDate)
//        snapshotService.applyDailySnapshot(domain, currentDate)
//
//        use(TimeCategory) {
//            currentDate = currentDate - 1.day
//        }
//        logState(currentDate)
//    }
//
//    def logState(Date currentDate) {
//        def data = [currentDate: currentDate.time]
//        def serviceName = 'missing-snapshot'
//        DataServiceState.executeUpdate("update DataServiceState s set s.isLastState = false where s.serviceName = :serviceName", [serviceName: serviceName])
//
//        DataServiceState state = new DataServiceState()
//        state.serviceName = serviceName
//        state.data = data as JSON
//        state.save(flush: true)
//    }
//
//    Date getLastState() {
//        def serviceName = 'missing-snapshot'
//        def data = DataServiceState.findByServiceNameAndIsLastState(serviceName, true)?.data
//        if (data) {
//            def json = JSON.parse(data)
//            return json?.currentDate ? new Date(json?.currentDate as Long) : new Date()
//        } else
//            return new Date()
//    }

    def execute() {
        def result = lowLevelDataService.executeFunction('DT_SEL_MISSING_SNAPSHOT', [:])
        def maxDate = date
        use(TimeCategory) {
            maxDate = date + 1.day
        }
        if (result?.size() && result[0].symbolId) {
            def date = new Date(result[0].dat.time as Long)
            def dailyTrade = SymbolDailyTrade.createCriteria().list {
                symbol {
                    idEq(result[0].symbolId as Long)
                }
                lte('date', maxDate)
                order('date', ORDER_DESCENDING)
                maxResults(1)
            }.find()
            if (dailyTrade) {
                dailyTrade.dailySnapshot = date


                def cal = Calendar.getInstance()
                cal.setTime(date)
                def jc = new JalaliCalendar(cal as GregorianCalendar)
                if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY)
                    dailyTrade.weeklySnapshot = date
                if (jc.getDay() == jc.getLastDayOfMonth(jc.getYear(), jc.getMonth()))
                    dailyTrade.monthlySnapshot = date
                println "Missing Snapshot: " + dailyTrade.save(flush: true)
            }
        }
    }
}
