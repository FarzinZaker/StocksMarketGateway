package stocks

import fi.joensuu.joyds1.calendar.JalaliCalendar
import groovy.time.TimeCategory
import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass
import stocks.tse.SymbolDailyTrade

class SnapshotService {

    def grailsApplication

    def applyPreviousSnapshots(String domain, def type = ['daily', 'weekly', 'monthly'], def daysCount = 10) {

        def counter = 0
        def indexer = 0
        def currentDate = new Date()
        while (indexer++ < daysCount) {
            def cal = Calendar.getInstance()
            cal.setTime(currentDate)
            def jc = new JalaliCalendar(cal as GregorianCalendar)
            if (type.contains('weekly') && cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY)
                applyWeeklySnapshot(domain, currentDate)
            if (type.contains('monthly') && jc.getDay() == jc.getLastDayOfMonth(jc.getYear(), jc.getMonth()))
                applyMonthlySnapshot(domain, currentDate)
            if (type.contains('daily'))
                applyDailySnapshot(domain, currentDate)

            use(TimeCategory) {
                currentDate = currentDate - 1.day
            }
        }

        println('finished!')
        println(counter)
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
        def idList = domainClass.clazz.createCriteria().list {
            if (maxDate)
                if (domainClass.clazz.snapshotDateProperty)
                    lte(domainClass.clazz.snapshotDateProperty, maxDate)
                else
                    lte('creationDate', maxDate)
            projections {
                if (domainClass.clazz.snapshotGroupProperty)
                    groupProperty(domainClass.clazz.snapshotGroupProperty)
                max('id')
            }
        }.collect { it.last() }
        if (idList.size() <= 1000)
            return domainClass.clazz.findAllByIdInList(idList)
        else {
            def result = []
            def index = 0
            while (index * 1000 < idList.size()) {
                def slittedIdList = idList[((index++) * 1000)..((index * 1000 >= idList.size() ? idList.size() : index * 1000) - 1)]
                result.addAll domainClass.clazz.findAllByIdInList(slittedIdList)
            }
            return result
        }
    }
}
