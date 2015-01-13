package stocks

import fi.joensuu.joyds1.calendar.JalaliCalendar
import groovy.time.TimeCategory
import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass
import stocks.tse.SymbolDailyTrade

class SnapshotService {

    def grailsApplication

    def applyPreviousSnapshots(def daysCount = 100) {

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

    }

    def applyDailySnapshot(def maxDate = null) {
        def today = maxDate ?: new Date()
        today.clearTime()
        findDomainClassesBySnapshot('daily').each { domainClass ->
            findLatestEventRecords(domainClass, maxDate).each { record ->
                record.dailySnapshot = today
                println record.save(flush: true)
            }
        }
    }

    def applyWeeklySnapshot(def maxDate = null) {
        def today = maxDate ?: new Date()
        today.clearTime()
        findDomainClassesBySnapshot('weekly').each { domainClass ->
            findLatestEventRecords(domainClass, maxDate).each { record ->
                record.weeklySnapshot = today
                println record.save(flush: true)
            }
        }
    }

    def applyMonthlySnapshot(def maxDate = null) {
        def today = maxDate ?: new Date()
        today.clearTime()
        findDomainClassesBySnapshot('monthly').each { domainClass ->
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
