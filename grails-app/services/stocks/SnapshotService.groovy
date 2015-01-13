package stocks

import fi.joensuu.joyds1.calendar.JalaliCalendar
import groovy.time.TimeCategory
import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass
import stocks.tse.SymbolDailyTrade

class SnapshotService {

    def grailsApplication

    def applyPreviousSnapshots(def daysCount = 30) {

        def indexer = 0
        def currentDate = new Date()
        while (indexer < daysCount) {
            def cal = Calendar.getInstance()
            cal.setTime(currentDate)
            def jc = new JalaliCalendar(cal as GregorianCalendar)
            if (jc.getDayOfWeek() == 5)
                applyWeeklySnapshot(currentDate)
            if (jc.getDay() == jc.getLastDayOfMonth(jc.getYear(), jc.getMonth()))
                applyMonthlySnapshot(currentDate)
            applyDailySnapshot(currentDate)

            use(TimeCategory) {
                currentDate = currentDate - (++indexer).day
            }
        }

//        def excludeList = ['SymbolDailyTrade']
//
//        def snapshotTypes = ['daily', 'weekly', 'monthly']
//        grailsApplication.getArtefacts("Domain").findAll { DefaultGrailsDomainClass domainClass ->
//            snapshotTypes.any { type ->
//                domainClass.persistantProperties*.name.contains("${type}Snapshot".toString())
//            } && !excludeList.any { domainClass.name.contains(it) }
//        }.collect { it as DefaultGrailsDomainClass }.each { domainClass ->
//            domainClass.clazz.findAll().each { record ->
//                def date = record.creationDate as Date
//                date = date.clearTime()
//                if (!domainClass.clazz."findByDailySnapshotAnd${domainClass.clazz.snapshotGroupProperty.capitalize()}"(date, record."${domainClass.clazz.snapshotGroupProperty}")) {
//                    record."dailySnapshot" = date
//                    println(record.save(flush: true))
//                }
//                def cal = Calendar.getInstance()
//                cal.setTime(date)
//                def jc = new JalaliCalendar(cal as GregorianCalendar)
//                if (jc.getDayOfWeek() == 5)
//                    if (!domainClass.clazz."findByWeeklySnapshotAnd${domainClass.clazz.snapshotGroupProperty.capitalize()}"(date, record."${domainClass.clazz.snapshotGroupProperty}")) {
//                        record."weeklySnapshot" = date
//                        println(record.save(flush: true))
//                    }
//
//                if (jc.getDay() == jc.getLastDayOfMonth(jc.getYear(), jc.getMonth()))
//                    if (!domainClass.clazz."findByMonthlySnapshotAnd${domainClass.clazz.snapshotGroupProperty.capitalize()}"(date, record."${domainClass.clazz.snapshotGroupProperty}")) {
//                        record."monthlySnapshot" = date
//                        println(record.save(flush: true))
//                    }
//            }
//        }

    }

    def applyDailySnapshot(def maxDate = null) {
        def today = maxDate ?: new Date()
        today.clearTime()
        findDomainClassesBySnapshot('daily').findAll{!it.name.contains('SymbolDailyTrade')}.each { domainClass ->
            findLatestEventRecords(domainClass, maxDate).each { record ->
                record.dailySnapshot = today
                println record.save(flush: true)
            }
        }
    }

    def applyWeeklySnapshot(def maxDate = null) {
        def today = maxDate ?: new Date()
        today.clearTime()
        findDomainClassesBySnapshot('weekly').findAll{!it.name.contains('SymbolDailyTrade')}.each { domainClass ->
            findLatestEventRecords(domainClass, maxDate).each { record ->
                record.weeklySnapshot = today
                println record.save(flush: true)
            }
        }
    }

    def applyMonthlySnapshot(def maxDate = null) {
        def today = maxDate ?: new Date()
        today.clearTime()
        findDomainClassesBySnapshot('monthly').findAll{!it.name.contains('SymbolDailyTrade')}.each { domainClass ->
            findLatestEventRecords(domainClass, maxDate).each { record ->
                record.monthlySnapshot = today
                println record.save(flush: true)
            }
        }
    }

    ArrayList<DefaultGrailsDomainClass> findDomainClassesBySnapshot(String type) {
        grailsApplication.getArtefacts("Domain").findAll { DefaultGrailsDomainClass domainClass ->
            domainClass.persistantProperties*.name.contains("${type}Snapshot".toString())
        }.collect { it as DefaultGrailsDomainClass }
    }

    ArrayList findLatestEventRecords(DefaultGrailsDomainClass domainClass, def maxDate = null) {
        domainClass.clazz.findAllByIdInList(domainClass.clazz.createCriteria().list {
            if (maxDate)
                lte('creationDate', maxDate)
            projections {
                if (domainClass.clazz.snapshotGroupProperty)
                    groupProperty(domainClass.clazz.snapshotGroupProperty)
                max('id')
            }
        }.collect { it.last() })
    }
}
