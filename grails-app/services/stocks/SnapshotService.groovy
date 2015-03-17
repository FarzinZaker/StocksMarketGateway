package stocks

import fi.joensuu.joyds1.calendar.JalaliCalendar
import groovy.time.TimeCategory
import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass
import stocks.tse.SymbolDailyTrade

class SnapshotService {

    def grailsApplication

    def applyPreviousSnapshots(String domain, def daysCount = 100) {

        def indexer = 0
        def currentDate = new Date()
        while (indexer++ < daysCount) {
            def cal = Calendar.getInstance()
            cal.setTime(currentDate)
            def jc = new JalaliCalendar(cal as GregorianCalendar)
            if (jc.getDayOfWeek() == 5)
                applyWeeklySnapshot(domain, currentDate)
            if (jc.getDay() == jc.getLastDayOfMonth(jc.getYear(), jc.getMonth()))
                applyMonthlySnapshot(domain, currentDate)
            applyDailySnapshot(domain, currentDate)

            use(TimeCategory) {
                currentDate = currentDate - 1.day
            }
        }

        println('finished!')
    }

    def applyDailySnapshot(String domain = '.', def maxDate = null) {
        findDomainClassesBySnapshot(domain, 'daily').each { domainClass ->
            findLatestEventRecords(domainClass, maxDate).each { record ->
                def today = record."${domainClass.clazz.snapshotDateProperty}"
                today = today.clearTime()
                record.dailySnapshot = today
                println "daily: ${record.save(flush: true)}"
            }
        }
    }

    def applyWeeklySnapshot(String domain = '.', def maxDate = null) {
        findDomainClassesBySnapshot(domain, 'weekly').each { domainClass ->
            findLatestEventRecords(domainClass, maxDate).each { record ->
                def today = record."${domainClass.clazz.snapshotDateProperty}"
                today = today.clearTime()
                record.weeklySnapshot = today
                println "weekly: ${record.save(flush: true)}"
            }
        }
    }

    def applyMonthlySnapshot(String domain = '.', def maxDate = null) {
        findDomainClassesBySnapshot(domain, 'monthly').each { domainClass ->
            findLatestEventRecords(domainClass, maxDate).each { record ->
                def today = record."${domainClass.clazz.snapshotDateProperty}"
                today = today.clearTime()
                record.monthlySnapshot = today
                println "monthly: ${record.save(flush: true)}"
            }
        }
    }

    ArrayList<DefaultGrailsDomainClass> findDomainClassesBySnapshot(String domain, String type) {
        grailsApplication.getArtefacts("Domain").findAll { DefaultGrailsDomainClass domainClass ->
            domainClass.persistantProperties*.name.contains("${type}Snapshot".toString()) && domainClass.fullName.toLowerCase().contains(domain)
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
